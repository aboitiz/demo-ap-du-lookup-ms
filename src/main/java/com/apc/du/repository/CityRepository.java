package com.apc.du.repository;

import com.apc.du.model.City;
import com.apc.du.model.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByCode(String code);
    long countByCode(String code);
}
