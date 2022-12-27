package com.apc.du.repository;

import com.apc.du.commons.dto.DistributionUtilityDTO;
import com.apc.du.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BarangayRepository extends JpaRepository<Barangay, Long> {
    @Query(value = "SELECT NEW com.apc.du.commons.dto.DistributionUtilityDTO(du.id, du.code, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " WHERE LOWER(b.code) = LOWER(:code)", nativeQuery = false)
    public List<DistributionUtilityDTO> getDUByBarangayCode(String code);

    @Query(value = "SELECT NEW com.apc.du.commons.dto.DistributionUtilityDTO(du.id, du.code, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " WHERE (LOWER(b.description) = LOWER(:barangay))" +
            " AND (LOWER(c.description) = LOWER(:city))" +
            " AND (LOWER(p.description) = LOWER(:province))", nativeQuery = false)
    public List<DistributionUtilityDTO> getDUByProvinceCityBarangay(String province, String city, String barangay);
}
