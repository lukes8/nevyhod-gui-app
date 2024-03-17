package com.lukepeace.projects.nevyhodgui.web.controller.rest.firebase;


import com.lukepeace.projects.nevyhodcore.service.firebase.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController("ItemsController4Firebase")
@RequestMapping(("/rest/api/firebase/items"))
@Slf4j
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @GetMapping("items")
    public void getItems() throws ExecutionException, InterruptedException {
        itemService.getAllItems(items -> {
            log.info("current items: {}", items);
        });
    }

    @PutMapping("insert-items")
    public void insertItems() throws ExecutionException, InterruptedException {
        itemService.insertItems();
    }

}
