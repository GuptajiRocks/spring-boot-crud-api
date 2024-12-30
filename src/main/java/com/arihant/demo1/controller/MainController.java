package com.arihant.demo1.controller;

import com.arihant.demo1.model.Stock;
import com.arihant.demo1.repository.StockInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private StockInterface stockInterface;

    @PostMapping("/add")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        Stock addedStock = stockInterface.save(stock);
        return ResponseEntity.ok(addedStock);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stock>> getAllStocks(@RequestParam(value = "name", required = false) String name) {
        if (name.equals("jesus@12")) {
            List<Stock> stocks = stockInterface.findAll();
            return ResponseEntity.ok(stocks);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<List<Stock>> deleteStock(@RequestParam(value = "name") String name) {
        List<Stock> deletedStock = stockInterface.deleteStockByName(name);
        List<Stock> newList = stockInterface.findAll();
        return ResponseEntity.ok(newList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Stock>> getStocksByName(@RequestParam(value = "name", required = true) String name) {
        List<Stock> stocks = stockInterface.findByNameContaining(name);
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Stock>> getStockById(@PathVariable(value = "id") Long id) {
        List<Stock> stock = stockInterface.findById(id);
        return ResponseEntity.ok(stock);
    }
}
