package com.apc.du.repository;

import com.apc.du.model.Barangay;
import com.apc.du.model.City;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<City, Long> {

}
