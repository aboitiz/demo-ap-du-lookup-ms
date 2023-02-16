package com.apc.du.repository;

import com.apc.du.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findById(String id);
    Province findByCode(String code);
    long countByCode(String code);
}
