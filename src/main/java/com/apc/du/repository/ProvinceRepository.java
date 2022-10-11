package com.apc.du.repository;

import com.apc.du.model.City;
import com.apc.du.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProvinceRepository extends JpaRepository<Province, Long> {

}
