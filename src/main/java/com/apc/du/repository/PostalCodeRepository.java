package com.apc.du.repository;

import com.apc.du.model.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {
    PostalCode findByCode(String code);
    long countByCode(String code);
}
