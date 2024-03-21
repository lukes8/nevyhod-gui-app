package com.lukepeace.projects.nevyhodgui.web.controller.rest.firebase;


import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.annotations.CustomItemVoParam;
import com.lukepeace.projects.nevyhodcore.openapi.TagName;
import com.lukepeace.projects.nevyhodcore.service.firebase.ItemService;
import com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController("ItemsController4Firebase")
@RequestMapping(("/rest/api/firebase/items"))
@Slf4j
@Tag(name = TagName.ITEMS_CONTROLLER_FIREBASE)
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @GetMapping("get-items-and-print-at-console")
    public void getItems4Console() throws ExecutionException, InterruptedException {
        itemService.getAllItems(items -> {
            log.info("current items: {}", items);
        });
    }

    @Operation(summary = "Get all items")
    @ApiResponses(value = {
            @ApiResponse(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "List<ItemVO>"))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid name supplied"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @GetMapping()
    public List<ItemVO> getItems() throws ExecutionException, InterruptedException, GeneralException {
        List<ItemVO> all = itemService.findAll();
        return all;
    }

    //    @Operation(summary = "Get item by id", tags = {"tag1"})
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
    public ItemVO create(@RequestBody @CustomItemVoParam ItemVO item) throws ExecutionException, InterruptedException, GeneralException {
        return itemService.create(item);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws GeneralException, ExecutionException, InterruptedException {
        itemService.delete(id);
    }


}
