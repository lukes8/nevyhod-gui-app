package com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring;


import com.lukepeace.projects.common.annotations.CustomPageableAsQueryParam;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.security.Permission;
import com.lukepeace.projects.nevyhodcore.service.OrderService;
import com.lukepeace.projects.nevyhodcore.util.OrderFilter;
import com.lukepeace.projects.nevyhodcore.vo.OrderVO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(("/rest/api/orders"))
//@PreAuthorize("isFullyAuthenticated()")
public class OrderController {

    @Autowired
    private OrderService itemService;
    @GetMapping() @PreAuthorize(Permission.ACCESS_ORDER_DATA) @CustomPageableAsQueryParam
    public Page<OrderVO> getAll(@Parameter(hidden = true) Pageable pageable,
                                @RequestParam(required = false) Long id,
                                @RequestParam(required = false) Integer status,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdDate
                                ) {
        OrderFilter filter = new OrderFilter(pageable);
        filter.setId(id);
        filter.setStatus(status);
        filter.setCreatedDate(createdDate);
        Page<OrderVO> lst = itemService.findAll(filter);
        return lst;
    }
    @PreAuthorize(Permission.CREATE_ORDER_DATA)
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public OrderVO create(@RequestBody OrderVO objectVO) throws GeneralException {

        return itemService.create(objectVO, objectVO.getId());
    }

}
