package com.enigma.restservice.controllers;
import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.entities.TypeTransaction;
import com.enigma.restservice.models.PageableList;
import com.enigma.restservice.models.ResponseMessage;
import com.enigma.restservice.models.stock.StockModel;
import com.enigma.restservice.models.stock.StockRequestModel;
import com.enigma.restservice.models.transactions.TransactionModel;
import com.enigma.restservice.models.transactions.TransactionSummary;
import com.enigma.restservice.services.Service;
import com.enigma.restservice.services.TransactionSummaryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.time.Month;
import java.time.Year;
import java.util.List;


@RequestMapping("/transactions")
@RestController
@Validated
public class TransactionController {

    @Autowired
    private Service<Transaction> service;

    @Autowired
    private TransactionSummaryService transactionSummaryService;

    @PostMapping
    public ResponseMessage<TransactionModel> add(@RequestBody TransactionModel model) {

        Transaction entity = service.save(new Transaction(model.getAmount(), model.getDescription(), TypeTransaction.fromValue(model.getTypeTransaction())));
        ModelMapper modelMapper = new ModelMapper();
        TransactionModel newModel = modelMapper.map(entity, TransactionModel.class);
        return ResponseMessage.success(newModel);
    }

    @PutMapping("/{id}")
    public ResponseMessage<TransactionModel> edit(@PathVariable Integer id, @RequestBody @Valid TransactionModel model) {
        ModelMapper modelMapper = new ModelMapper();
        Transaction entity = service.findById(id);
        entity.setAmount(model.getAmount());
        entity.setTypeTransaction(TypeTransaction.fromValue(model.getTypeTransaction()));
        entity = service.save(entity);
        TransactionModel newModel = modelMapper.map(entity, TransactionModel.class);
        return ResponseMessage.success(newModel);
    }

    @GetMapping()
    public ResponseMessage<PageableList<TransactionModel>> findAll(@RequestParam(required = false) TypeTransaction typeTransaction, Double amount, String description,
                                                                   @RequestParam(defaultValue = "ASC") String sort,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }

        Transaction transaction = new Transaction(amount, description, typeTransaction);
        Sort.Direction direction = Sort.Direction
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Transaction> pageTransactions = service.findAll(transaction, page, size, direction);
        List<Transaction> transactions = pageTransactions.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type targetListType = new TypeToken<List<TransactionModel>>() {
        }.getType();
        List<TransactionModel> models = modelMapper.map(transactions, targetListType);
        PageableList<TransactionModel> data =
                new PageableList<>(
                        models, pageTransactions.getTotalElements(), pageTransactions.getSize(), pageTransactions.getNumber()
                );

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<TransactionModel> findById(@PathVariable Integer id) {
        Transaction entity = service.findById(id);

        ModelMapper modelMapper = new ModelMapper();
        TransactionModel model = modelMapper.map(entity, TransactionModel.class);
        return ResponseMessage.success(model);

    }

    @DeleteMapping("/{id}")
    public ResponseMessage<TransactionModel> removeById(@PathVariable Integer id) {
        Transaction entity = service.removeById(id);
        ModelMapper modelMapper = new ModelMapper();
        TransactionModel model = modelMapper.map(entity, TransactionModel.class);
        return ResponseMessage.success(model);
    }


    @GetMapping("/summary")
    public ResponseMessage<List<TransactionSummary>> summary(@RequestParam(required = false) Integer year,
                                                                  @RequestParam(required = false) Integer month,
                                                                  @RequestParam(required = false) Integer date){
        List<TransactionSummary> entities = transactionSummaryService.transactionSummary(
                year != null ? Year.of(year) : Year.now(),
                month != null ? Month.of(month) : null,
                date != null ? date : null);

        return ResponseMessage.success(entities);
    }

}
