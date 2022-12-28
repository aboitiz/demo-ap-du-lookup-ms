package com.apc.du.repository;

import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BarangayRepository extends JpaRepository<Barangay, Long> {
    @Query(value = "SELECT NEW com.apc.du.commons.dto.APIResponseDTO(p.description, c.description, b.description, du.id, du.code, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " WHERE LOWER(b.code) = LOWER(:code)", nativeQuery = false)
    public List<APIResponseDTO> getDUByBarangayCode(String code);

    @Query(value = "SELECT DISTINCT ON(du.id, du.code, du.description) p.description AS provinceDescription, c.description AS cityDescription, b.description AS barangayDescription, du.id, du.code, du.description" +
            " FROM barangay AS b" +
            " LEFT JOIN city AS c ON b.city_id = c.id" +
            " LEFT JOIN postal_code AS pc ON c.postal_code_id = pc.id" +
            " LEFT JOIN province AS p ON c.province_id = p.id" +
            " LEFT JOIN distribution_utility AS du ON b.distribution_utility_id = du.id" +
            " WHERE pc.code LIKE CONCAT('%', :code, '%')", nativeQuery = true)
    public List<Map> getDUByPostalCode(String code);

    @Query(value = "SELECT NEW com.apc.du.commons.dto.APIResponseDTO(p.description, c.description, b.description, du.id, du.code, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " WHERE (LOWER(b.description) = LOWER(:barangay))" +
            " AND (LOWER(c.description) = LOWER(:city))" +
            " AND (LOWER(p.description) = LOWER(:province))", nativeQuery = false)
    public List<APIResponseDTO> getDUByProvinceCityBarangay(String province, String city, String barangay);
}
