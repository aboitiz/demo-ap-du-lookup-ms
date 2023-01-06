package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "distribution_utility")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistributionUtility extends AuditableEntity {
    @Column(name = "code", length = 255)
    private String code;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @OneToMany(mappedBy = "distributionUtility", fetch = FetchType.LAZY)
    private Set<Barangay> barangays = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DistributionUtility that)) return false;
        if (!super.equals(o)) return false;
        return getCode().equals(that.getCode()) && getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getDescription());
    }
}
