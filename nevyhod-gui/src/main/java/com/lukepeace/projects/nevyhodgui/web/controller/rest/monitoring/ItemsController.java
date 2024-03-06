package com.lukepeace.projects.nevyhodgui.web.controller.rest.monitoring;


import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.service.ItemService;
import com.lukepeace.projects.nevyhodcore.util.ItemFilter;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
    public Page<ItemVO> getAll(Pageable pageable,
                                    @RequestParam(required = false) Long id,
                                    @RequestParam(required = false) Integer status) throws GeneralException {
        ItemFilter itemFilter = new ItemFilter(pageable);
        itemFilter.setId(id);
        itemFilter.setStatus(status);

        Page<ItemVO> lst = itemService.findAll(itemFilter);
        return lst;
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> export() throws IOException, GeneralException {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=green.json");
        InputStream inputStream = itemService.exportJson();
        return new ResponseEntity<>(new InputStreamResource(inputStream), responseHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/import", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> importJson(@RequestParam("file") MultipartFile file) throws IOException {
        Long id = itemService.importJson(file.getInputStream());

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ItemVO create(@RequestBody ItemVO item) throws GeneralException {
        return itemService.create(item, item.getId());
    }
    @PutMapping(value = "/delete/{id}")
    public String create(@PathVariable Long id) throws GeneralException {
        itemService.delete(id);
        return "success";
    }

}
