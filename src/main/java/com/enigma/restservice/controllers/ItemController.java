package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.models.item.ItemModel;
import com.enigma.restservice.models.PageableList;
import com.enigma.restservice.models.ResponseMessage;
import com.enigma.restservice.services.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RequestMapping("/items")
@RestController
@Validated
public class ItemController {

    @Autowired
    private Service<Item> service;

    @PostMapping
    public ResponseMessage<ItemModel> add(@RequestBody ItemModel model) {
        Item entity = service.save(new Item(model.getName()));
        ModelMapper modelMapper = new ModelMapper();
        ItemModel newModel = modelMapper.map(entity, ItemModel.class);
        return ResponseMessage.success(newModel);
    }

    @PutMapping("/{id}")
    public ResponseMessage<ItemModel> edit(@PathVariable Integer id, @RequestBody @Valid ItemModel model) {
        ModelMapper modelMapper = new ModelMapper();
        model.setId(id);
        Item entity = service.findById(id);
        modelMapper.map(model, entity);
        entity = service.save(entity);
        ItemModel newModel = modelMapper.map(entity, ItemModel.class);
        return ResponseMessage.success(newModel);
    }

    @GetMapping("/{id}")
    public ResponseMessage<ItemModel> findById(@PathVariable Integer id) {
        Item entity = service.findById(id);

        ModelMapper modelMapper = new ModelMapper();
        ItemModel model = modelMapper.map(entity, ItemModel.class);
        return ResponseMessage.success(model);


    }

    @GetMapping()
    public ResponseMessage<PageableList<ItemModel>> findAll(@RequestParam(required = false) String name,
                                                            @RequestParam(defaultValue = "ASC") String sort,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }

        Item item = new Item(name);
        Sort.Direction direction = Sort.Direction
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Item> pageItems = service.findAll(item, page, size, direction);
        List<Item> items = pageItems.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type targetListType = new TypeToken<List<ItemModel>>() {
        }.getType();
        List<ItemModel> models = modelMapper.map(items, targetListType);
        PageableList<ItemModel> data =
                new PageableList<>(
                        models, pageItems.getTotalElements(), pageItems.getSize(), pageItems.getNumber()
                );

        return ResponseMessage.success(data);
    }


    @DeleteMapping("/{id}")
    public ResponseMessage<ItemModel> removeById(@PathVariable Integer id) {
        Item entity = service.removeById(id);

        ModelMapper modelMapper = new ModelMapper();
        ItemModel model = modelMapper.map(entity, ItemModel.class);
        return ResponseMessage.success(model);
    }


}
