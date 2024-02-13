package com.lukepeace.projects.nevyhodgui.rest.monitoring;


import com.lukepeace.projects.nevyhodcore.service.ItemService;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/rest/api/items"))
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/green")
    public String green() {

        return "green";

    }

    @GetMapping()
    public List<ItemVO> getAllItems() {
        List<ItemVO> lst = itemService.findAll();
        return lst;
    }
}
