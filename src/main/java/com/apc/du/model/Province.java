package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province extends AuditableEntity {
    @Column(name = "code", length = 255)
    public String code;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    public String description;

    @OneToMany(mappedBy = "province")
    public Set<City> cities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Province province)) return false;
        if (!super.equals(o)) return false;
        return getCode().equals(province.getCode()) && getDescription().equals(province.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getDescription());
    }
}
