package com.arihant.demo1.repository;

import com.arihant.demo1.model.MAC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MacInterface extends JpaRepository<MAC, Integer> {

    boolean existsBymacadd(String macadd);
}
