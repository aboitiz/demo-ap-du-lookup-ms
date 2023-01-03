package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "distribution_utility")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistributionUtility extends AuditableEntity {
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

    @OneToMany(mappedBy = "distributionUtility", fetch = FetchType.LAZY)
    public Set<Barangay> barangays = new HashSet<>();
}