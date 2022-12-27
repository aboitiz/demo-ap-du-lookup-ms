package com.apc.du.repository;

import com.apc.du.commons.dto.DUDTO;
import com.apc.du.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BarangayRepository extends JpaRepository<Barangay, Long> {
    @Query(value = "SELECT NEW com.apc.du.commons.dto.DUDTO(b.code, p.description, c.description, b.description, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " WHERE LOWER(b.code) = LOWER(:code)", nativeQuery = false)
    public List<DUDTO> getDUByBarangayCode(String code);

    @Query(value = "SELECT NEW com.apc.du.commons.dto.DUDTO(b.code, p.description, c.description, b.description, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " WHERE (LOWER(b.description) LIKE CONCAT('%',LOWER(:barangay),'%'))" +
            " AND (LOWER(c.description) LIKE CONCAT('%',LOWER(:city),'%'))" +
            " AND (LOWER(p.description) LIKE CONCAT('%',LOWER(:province),'%'))", nativeQuery = false)
    public List<DUDTO> getDUByProvinceCityBarangay(String province, String city, String barangay);
}
