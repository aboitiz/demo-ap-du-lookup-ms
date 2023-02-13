package com.apc.du.repository;

import com.apc.du.commons.dto.APIResponseDTO;
import com.apc.du.commons.dto.LocationsDTO;
import com.apc.du.model.Barangay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     *
     * @param distributionUtility
     * @param province
     * @param city
     * @param postalCode
     * @param barangay
     * @return List<LocationsDTO>
     */
    @Query(value = "SELECT NEW com.apc.du.commons.dto.LocationsDTO(p.id, p.code, p.description, c.id, c.code, c.description, pc.id, pc.code, pc.description, b.id, b.code, b.description, du.id, du.code, du.description)" +
            " FROM Barangay AS b" +
            " JOIN City AS c ON b.city.id = c.id" +
            " JOIN Province AS p ON c.province.id = p.id" +
            " JOIN DistributionUtility AS du ON b.distributionUtility.id = du.id" +
            " JOIN PostalCode AS pc ON c.postalCode.id = pc.id" +
            " WHERE REPLACE(LOWER(du.code), ' ', '') LIKE %:distributionUtility%" +
            " AND REPLACE(LOWER(p.description), ' ', '') LIKE %:province%" +
            " AND REPLACE(LOWER(c.description), ' ', '') LIKE %:city%" +
            " AND REPLACE(LOWER(pc.code), ' ', '') LIKE %:postalCode%" +
            " AND REPLACE(LOWER(b.description), ' ', '') LIKE %:barangay%",
            nativeQuery = false)
    List<LocationsDTO> getAllDULocations(@Param("distributionUtility") String distributionUtility,
                                         @Param("province") String province,
                                         @Param("city") String city,
                                         @Param("postalCode") String postalCode,
                                         @Param("barangay") String barangay);


/*
 ----- getAllDULocations() Equivalent query on postgres -----
 select
        p.id, p.code, p.description,
        c.id, c.code, c.description,
        pc.id, pc.code, pc.description,
        b.id, b.code, b. description,
        du.id, du.code, du.description
    from barangay b join city c on b.city_id = c.id
        join province p on c.province_id = p.id
        join distribution_utility du on b.distribution_utility_id = du.id
        join postal_code pc on c.postal_code_id = pc.id
    where replace(LOWER(du.code), ' ', '') like '%veco%'
        and replace(LOWER(p.description), ' ', '') like '%%'
        and replace(LOWER(c.description), ' ', '') like '%%'
        and replace(LOWER(pc.code), ' ', '') like '%%'
        and replace(LOWER(b.description), ' ', '') like '%%'
 */
}
