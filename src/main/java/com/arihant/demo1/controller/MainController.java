package com.arihant.demo1.controller;

import com.arihant.demo1.model.Stock;
import com.arihant.demo1.repository.StockInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Stock>> getAllStocks(@RequestParam(value = "name", required = true) String name) {
        if (name.equals("jesus@12")) {
            List<Stock> stocks = stockInterface.findAll();
            return ResponseEntity.ok(stocks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Stock>> getStocksByName(@RequestParam("name") String name) {
        List<Stock> stocks = stockInterface.findByNameContaining(name);
        return ResponseEntity.ok(stocks);
    }
}
