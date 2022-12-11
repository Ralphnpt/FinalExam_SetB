package com.example.ralph300341112.respository;

import com.example.ralph300341112.entities.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesmanRepository extends JpaRepository<Salesman,Long> {
    List<Salesman> findSalesmanById(long kw);
}
