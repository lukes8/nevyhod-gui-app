package com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring;


import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.security.Permission;
import com.lukepeace.projects.nevyhodcore.service.OrderService;
import com.lukepeace.projects.nevyhodcore.util.OrderFilter;
import com.lukepeace.projects.nevyhodcore.vo.OrderVO;
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
    @GetMapping() @PreAuthorize(Permission.ACCESS_ORDER_DATA)
    public Page<OrderVO> getAll(Pageable pageable,
                                @RequestParam(required = false, defaultValue = "123") Long id,
                                @RequestParam(required = false, defaultValue = "123") Integer status,
                                @RequestParam(required = false, defaultValue = "2023-03-02T19:00:00Z")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdDate
                                ) throws GeneralException {
        OrderFilter filter = new OrderFilter(pageable);
        filter.setId(id);
        filter.setStatus(status);
        filter.setCreatedDate(createdDate);
        Page<OrderVO> lst = itemService.findAll(filter);
        return lst;
    }
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public OrderVO create(@RequestBody OrderVO objectVO) throws GeneralException {

        return itemService.create(objectVO, objectVO.getId());
    }

}
