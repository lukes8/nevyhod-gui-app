package com.lukepeace.projects.nevyhodgui.web.controller.rest.firebase;


import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.service.firebase.ItemService;
import com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController("ItemsController4Firebase")
@RequestMapping(("/rest/api/firebase/items"))
@Slf4j
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @GetMapping("get-items-and-print-at-console")
    public void getItems4Console() throws ExecutionException, InterruptedException {
        itemService.getAllItems(items -> {
            log.info("current items: {}", items);
        });
    }
    @GetMapping()
    public List<ItemVO> getItems() throws ExecutionException, InterruptedException, GeneralException {
        List<ItemVO> all = itemService.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public ItemVO getById(@PathVariable Long id) throws ExecutionException, InterruptedException, GeneralException {
        ItemVO obj = itemService.findById(id);
        return obj;
    }

    @PutMapping("insert-items")
    public void insertItems() throws ExecutionException, InterruptedException {
        itemService.insertItems();
    }

    @PostMapping("create")
    public ItemVO create(@RequestBody ItemVO item) throws ExecutionException, InterruptedException, GeneralException {
        return itemService.create(item);
    }

}
