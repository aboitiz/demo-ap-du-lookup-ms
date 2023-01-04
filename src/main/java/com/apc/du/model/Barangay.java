package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "barangay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Barangay extends AuditableEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "code", length = 255)
    private String code;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City city;

    @ManyToOne
    @JoinColumn(name = "distributionUtility_id")
    public DistributionUtility distributionUtility;
}
