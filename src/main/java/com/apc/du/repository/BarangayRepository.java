package com.apc.du.repository;

import com.apc.du.commons.dto.DUDTO;
import com.apc.du.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BarangayRepository extends JpaRepository<Barangay, Long> {
    @Query(value = "select new com.apc.du.commons.dto.DUDTO(b.barangayCode, p.description, c.description, b.description, b.du)" +
            " from Barangay b" +
            " join City c on b.cityCode = c.cityCode" +
            " join Province p on c.provinceCode = p.provinceCode" +
            " where LOWER(b.barangayCode) = LOWER(:barangayCode)", nativeQuery = false)
    public List<DUDTO> getDUByBarangayCode(String barangayCode);

    @Query(value = "select new com.apc.du.commons.dto.DUDTO(b.barangayCode, p.description, c.description, b.description, b.du)" +
            " from Barangay b" +
            " join City c on b.cityCode = c.cityCode" +
            " join Province p on c.provinceCode = p.provinceCode" +
            " where (LOWER(b.description) LIKE CONCAT('%',LOWER(:barangay),'%'))" +
            " and (LOWER(c.description) LIKE CONCAT('%',LOWER(:city),'%'))" +
            " and (LOWER(p.description) LIKE CONCAT('%',LOWER(:province),'%'))", nativeQuery = false)
    public List<DUDTO> getDUByProvinceCityBarangay(String province,String city, String barangay);

}
