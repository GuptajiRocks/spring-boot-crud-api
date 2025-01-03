package com.arihant.demo1.repository;

import com.arihant.demo1.model.Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface StockInterface extends JpaRepository<Stock, Integer> {
    List<Stock> findByNameContaining(String name);

    List<Stock> findById(long id);

    @Modifying
    @Transactional // Important for data consistency
    @Query("DELETE FROM Stock s WHERE s.name = :name")
    void deleteStockByName(String name);
}
