package com.apc.du.repository;

import com.apc.du.commons.dto.DUDTO;
import com.apc.du.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BarangayRepository extends JpaRepository<Barangay, Long> {
    @Query(value = "select new com.apc.du.commons.dto.DUDTO(b.barangayCode, b.description, b.du)" +
            " from Barangay b" +
            " where LOWER(b.barangayCode) = LOWER(:barangayCode)", nativeQuery = false)
    public List<DUDTO> getDUByBarangayCode(String barangayCode);

    @Query(value = "select new com.apc.du.commons.dto.DUDTO(b.barangayCode, b.description, b.du)" +
            " from Barangay b" +
            " where LOWER(b.description) = LOWER(:cityBarangay)", nativeQuery = false)
    public List<DUDTO> getDUByCityBarangay(String cityBarangay);

}
