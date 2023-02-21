package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "barangay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barangay extends AuditableEntity {
    @Column(name = "code", length = 255)
    public String code;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String description;

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City city;

    @ManyToOne
    @JoinColumn(name = "distributionUtility_id")
    public DistributionUtility distributionUtility;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barangay barangay)) return false;
        if (!super.equals(o)) return false;
        return getCode().equals(barangay.getCode()) && getDescription().equals(barangay.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getDescription());
    }
}
