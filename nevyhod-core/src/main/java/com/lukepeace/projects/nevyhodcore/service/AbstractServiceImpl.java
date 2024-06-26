package com.lukepeace.projects.nevyhodcore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.exceptions.GeneralExceptionCodes;
import com.lukepeace.projects.common.exceptions.NevyhodExceptionCodes;
import com.lukepeace.projects.common.util.PagingSortingFilter;
import com.lukepeace.projects.common.util.ValidationUtilHelper;
import com.lukepeace.projects.nevyhodcore.criteria.ICriteria;
import com.lukepeace.projects.nevyhodcore.entity.Order;
import com.lukepeace.projects.nevyhodcore.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractServiceImpl<
        E,
        VO,
        R extends JpaRepository & QuerydslPredicateExecutor,
        ID,
        F extends PagingSortingFilter,
        CR extends ICriteria>
    implements IService<VO, ID, F> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private ValidationUtilHelper<E> validationUtilHelper;
    @Autowired private ObjectMapper objectMapper;
    private final int PARAM_TYPE_IDX_ENTITY = 0;
    private final int PARAM_TYPE_IDX_VO = 1;
    private final int PARAM_TYPE_IDX_REPO = 2;
    private final int PARAM_TYPE_IDX_ID = 3;
    private final int PARAM_TYPE_IDX_FILTER = 4;
    private final int PARAM_TYPE_IDX_CRITERIA = 5;

    public Page<VO> findAll(F filter) {

        R repo = getRepository();
        if (filter != null) {

            CR criteria = getCriteria();
            Page<E> all = repo.findAll(criteria.where(filter), filter.getPageable());

            return new PageImpl<>(map2VO(all.getContent()), filter.getPageable(), all.getTotalElements());
        } else {
            List<E> all = repo.findAll();
            Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            List<VO> list = map2VO(all);
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);

            return new PageImpl(list, pageable, list.size());
        }

    }

    public VO create(VO objectVO, ID id) throws GeneralException {
        E entity = map2Entity(objectVO);
        validationUtilHelper.validate(entity);
        validateBeforeCreate(id);
        R repo = getRepository();
        E save = (E)repo.save(entity);
        return map2VO(save);
    }

    public void delete(ID id) throws GeneralException {
        validateBeforeDelete(id);
        R repo = getRepository();
        repo.deleteById(id);
    }
    public void validate(ID id) throws GeneralException {
        R repo = getRepository();
        if (!repo.existsById(id)) {
            throw validationUtilHelper.buildGeneralException(populateExceptionCode4NotFound());
        }
    }
    private GeneralExceptionCodes populateExceptionCode4NotFound() {
        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[PARAM_TYPE_IDX_ENTITY];
        log.debug("populateExceptionCode: " + t != null ? t.getTypeName() : "nothing");
        if (User.class.getTypeName().equalsIgnoreCase(t.getTypeName())) {
            return NevyhodExceptionCodes.USER_NOT_FOUND;
        }
        else if (Order.class.getTypeName().equalsIgnoreCase(t.getTypeName())) {
            return NevyhodExceptionCodes.ORDER_NOT_FOUND;
        }
        return NevyhodExceptionCodes.ITEM_NOT_FOUND;
    }

    public void validateBeforeCreate(ID id) throws GeneralException {
        R repo = getRepository();
        if (repo.existsById(id)) {
            throw validationUtilHelper.buildGeneralException(NevyhodExceptionCodes.ITEM_ALREADY_EXISTS);
        }
    }

    public void validateBeforeDelete(ID id) throws GeneralException {
        R repo = getRepository();
        if (!repo.existsById(id)) {
            throw validationUtilHelper.buildGeneralException(NevyhodExceptionCodes.ITEM_NOT_FOUND);
        }
    }


    protected <S, VO> List<VO> map2VO(List<S> source, Class<VO> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    protected <S, VO> List<VO> map2VO(List<S> source) {
        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return (List<VO>) source
                .stream()
                .map(element -> modelMapper.map(element, t))
                .collect(Collectors.toList());
    }

    protected E map2Entity(VO source) {
        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[PARAM_TYPE_IDX_ENTITY];
        return modelMapper.map(source, t);
    }

    protected VO map2VO(E source) {
        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[PARAM_TYPE_IDX_VO];
        return modelMapper.map(source, t);
    }
    protected void validateExistence() throws GeneralException {
        String[] beanNames = appContext.getBeanNamesForType(ResolvableType.forType(new ParameterizedTypeReference<R>() {
        }));
        R repo = (R) appContext.getBean(beanNames[0]);
        if (repo.count() == 0) {
            throw validationUtilHelper.buildGeneralException(NevyhodExceptionCodes.ITEM_NOT_FOUND);
        }

    }

    protected R getRepository() {
        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[PARAM_TYPE_IDX_REPO];
        R obj = (R) appContext.getBean((Class)t);
        return obj;
    }

    private CR getCriteria() {
        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[PARAM_TYPE_IDX_CRITERIA];
        CR obj = (CR) appContext.getBean((Class)t);
        return obj;
    }

    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}
