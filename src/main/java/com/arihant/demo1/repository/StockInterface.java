package com.arihant.demo1.repository;

import com.arihant.demo1.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockInterface extends JpaRepository<Stock, Integer> {
    List<Stock> findByNameContaining(String name);

    List<Stock> findById(long id);
}
