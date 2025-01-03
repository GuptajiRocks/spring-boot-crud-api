package com.arihant.demo1.controller;

import com.arihant.demo1.model.MAC;
import com.arihant.demo1.model.Stock;
import com.arihant.demo1.repository.MacInterface;
import com.arihant.demo1.repository.StockInterface;
import com.arihant.demo1.service.MacAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private StockInterface stockInterface;

    @Autowired
    private MacAddressService macAddressService;

    @Autowired
    private MacInterface macInterface;

    @PostMapping("/add")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        Stock addedStock = stockInterface.save(stock);
        return ResponseEntity.ok(addedStock);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stock>> getAllStocks() {
        if (macInterface.existsBymacadd(macAddressService.getMacAddress())) {
            List<Stock> allStocks = stockInterface.findAll();
            return ResponseEntity.ok(allStocks);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Stock>> deleteStock(@RequestParam(value = "name") String name) {
        stockInterface.deleteStockByName(name);
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

    @GetMapping("/sorted")
    public ResponseEntity<List<Stock>> getStocksByNameAsc(@RequestHeader(value = "name") String name) {
        if (name.equals("waah")) {
            List<Stock> stocks = stockInterface.findAll();
            stocks.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
            return ResponseEntity.ok(stocks);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/mac")
    public ResponseEntity<String> getMac() {
        String macAdd = macAddressService.getMacAddress();
        return ResponseEntity.ok(macAdd);
    }

    @PostMapping("/addValidmac")
    public ResponseEntity<MAC> addValidMac() {
        String toBeaddedMac = macAddressService.getMacAddress();

        if (macInterface.existsBymacadd(toBeaddedMac)) {
            return ResponseEntity.badRequest().body(new MAC("MAC Address Already exists in the DB"));
        }
        MAC mac = new MAC(toBeaddedMac);
        macInterface.save(mac);
        return ResponseEntity.ok(mac);
    }

    @GetMapping("/approvedMac")
    public ResponseEntity<List<MAC>> getApprovedMac() {
        List<MAC> mac = macInterface.findAll();
        return ResponseEntity.ok(mac);
    }
}
