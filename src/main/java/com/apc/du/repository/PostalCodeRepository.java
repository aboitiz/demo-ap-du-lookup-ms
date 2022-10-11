package com.apc.du.repository;

import com.apc.du.model.PostalCode;
import com.apc.du.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {

}
