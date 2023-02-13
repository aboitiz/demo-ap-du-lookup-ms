package com.apc.du.commons.dto;


import com.apc.du.commons.dto.locations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationsDTO {

    private Province province;
    private City city;
    private PostalCode postalCode;
    private Barangay barangay;
    private DistributionUtility distributionUtility;

    public LocationsDTO(Long provId, String provCode, String provDesc, Long cId, String cCode, String cDesc, Long pcId, String pcCode, String pcDesc, Long bId, String bCode, String bDesc, Long duId, String duCode, String duDesc) {
        this.province = new Province(provId, provCode, provDesc);
        this.city = new City(cId, cCode, cDesc);
        this.postalCode = new PostalCode(pcId, pcCode, pcDesc);
        this.barangay = new Barangay(bId, bCode, bDesc);
        this.distributionUtility = new DistributionUtility(duId, duCode, duDesc);
    }
}





