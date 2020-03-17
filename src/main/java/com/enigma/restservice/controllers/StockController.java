package com.enigma.restservice.controllers;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.models.*;
import com.enigma.restservice.models.stock.StockModel;
import com.enigma.restservice.models.stock.StockRequestModel;
import com.enigma.restservice.models.stock.StockSummary;
import com.enigma.restservice.services.Service;
import com.enigma.restservice.services.StockSummaryService;
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


@RequestMapping("/stocks")
@RestController
@Validated
public class StockController {

    @Autowired
    private Service<Stock> stockService;

    @Autowired
    StockSummaryService stockSummaryService;

    @Autowired
    private Service<Item> itemService;

    @Autowired
    private Service<Unit> unitService;


    @PostMapping
    public ResponseMessage<StockModel> add(@RequestBody StockRequestModel model) {

        Item item = itemService.findById(model.getItemId());
        Unit unit = unitService.findById(model.getUnitId());

        Stock entity = new Stock(item, model.getQuantity(), unit);
        stockService.save(entity);

        ModelMapper modelMapper = new ModelMapper();
        StockModel newModel = modelMapper.map(entity, StockModel.class);

        return ResponseMessage.success(newModel);
    }


    @GetMapping()
    public ResponseMessage<PageableList<StockModel>> findAll(@RequestParam(required = false) Integer quantity, Item item, Unit unit,
                                                             @RequestParam(defaultValue = "ASC") String sort,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }

        Stock stock = new Stock(item, quantity, unit);
        Sort.Direction direction = Sort.Direction
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Stock> pageStocks = stockService.findAll(stock, page, size, direction);
        List<Stock> stocks = pageStocks.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type targetListType = new TypeToken<List<StockModel>>() {
        }.getType();
        List<StockModel> models = modelMapper.map(stocks, targetListType);
        PageableList<StockModel> data =
                new PageableList<>(
                        models, pageStocks.getTotalElements(), pageStocks.getSize(), pageStocks.getNumber()
                );

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<StockModel> edit(@PathVariable Integer id, @RequestBody @Valid StockRequestModel model) {
        ModelMapper modelMapper = new ModelMapper();
        Stock entity = stockService.findById(id);
        entity.setItem(itemService.findById(model.getItemId()));
        entity.setUnit(unitService.findById(model.getUnitId()));
        entity.setQuantity(model.getQuantity());
        entity = stockService.save(entity);
        StockModel newModel = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.success(newModel);
    }

    @GetMapping("/{id}")
    public ResponseMessage<StockModel> findById(@PathVariable Integer id) {
        Stock entity = stockService.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        StockModel model = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.success(model);

    }

    @DeleteMapping("/{id}")
    public ResponseMessage<StockModel> removeById(@PathVariable Integer id) {
        Stock entity = stockService.removeById(id);
        ModelMapper modelMapper = new ModelMapper();
        StockModel model = modelMapper.map(entity, StockModel.class);
        return ResponseMessage.success(model);
    }

    @GetMapping("/summary")
    public ResponseMessage<List<StockSummary>> stockSummary() {
        List<StockSummary> entities = stockSummaryService.stockSummary();
        return ResponseMessage.success(entities);
    }


}
