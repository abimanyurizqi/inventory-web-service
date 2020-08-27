package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.models.PageableList;
import com.enigma.restservice.models.ResponseMessage;
import com.enigma.restservice.models.item.ItemModel;
import com.enigma.restservice.models.unit.UnitModel;
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

@RequestMapping("/units")
@RestController
@Validated
public class UnitController {

    @Autowired
    private Service<Unit, Integer> service;

    @PostMapping
    public ResponseMessage<UnitModel> add(@RequestBody @Valid UnitModel model) {
        Unit entity = service.save(new Unit(model.getName(), model.getDescription()));
        ModelMapper modelMapper = new ModelMapper();
        UnitModel newModel = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(newModel);
    }

    @PutMapping("/{id}")
    public ResponseMessage<UnitModel> edit(@PathVariable Integer id, @RequestBody @Valid UnitModel model) {
        ModelMapper modelMapper = new ModelMapper();
        model.setId(id);
        Unit entity = service.findById(id);
        modelMapper.map(model, entity);
        entity = service.save(entity);
        UnitModel newModel = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(newModel);
    }

    @GetMapping("/{id}")
    public ResponseMessage<UnitModel> findById(@PathVariable Integer id) {
        Unit entity = service.findById(id);

        ModelMapper modelMapper = new ModelMapper();
        UnitModel model = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(model);
    }

    @GetMapping
    public ResponseMessage<PageableList<UnitModel>> findAll(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String description,
                                                            @RequestParam(defaultValue = "ASC") String sort,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }

        Unit unit = new Unit(name, description);
        Sort.Direction direction = Sort.Direction
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Unit> pageUnits = service.findAll(unit, page, size, direction);
        List<Unit> units = pageUnits.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type targetListType = new TypeToken<List<UnitModel>>() {
        }.getType();
        List<UnitModel> models = modelMapper.map(units, targetListType);
        PageableList<UnitModel> data =
                new PageableList<>(
                        models, pageUnits.getTotalElements(), pageUnits.getSize(), pageUnits.getNumber()
                );

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<UnitModel> removeById(@PathVariable Integer id) {
        Unit entity = service.removeById(id);

        ModelMapper modelMapper = new ModelMapper();
        UnitModel model = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(model);
    }

}
