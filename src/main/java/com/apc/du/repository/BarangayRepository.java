package com.apc.du.repository;

import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BarangayRepository extends JpaRepository<Barangay, Long> {
    @Query(value = "SELECT NEW com.apc.du.commons.dto.APIResponseDTO(p.description, c.description, pc.code, b.description, du.id, du.code, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " JOIN PostalCode AS pc ON c.postalCode.id = pc.id" +
            " WHERE (LOWER(b.description) = LOWER(:barangay))" +
            " AND (LOWER(c.description) = LOWER(:city))" +
            " AND (LOWER(p.description) = LOWER(:province))" +
            " AND (LOWER(pc.code) = LOWER(:postalCode))", nativeQuery = false)
    public List<APIResponseDTO> getDUByProvinceCityBarangayPostalCode(String province, String city, String barangay, String postalCode);
}
