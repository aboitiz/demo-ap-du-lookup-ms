package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends AuditableEntity {
    @Column(name = "code", length = 255)
    public String code;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String description;

    @ManyToOne
    @JoinColumn(name = "province_id")
    public Province province;

    @ManyToOne
    @JoinColumn(name = "postalCode_id")
    public PostalCode postalCode;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    public List<Barangay> barangays = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;
        if (!super.equals(o)) return false;
        return getCode().equals(city.getCode()) && getDescription().equals(city.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getDescription());
    }
}
