package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City extends AuditableEntity {
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
    @JoinColumn(name = "province_id")
    public Province province;

    @ManyToOne
    @JoinColumn(name = "postalCode_id")
    public PostalCode postalCode;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    public List<Barangay> barangays = new ArrayList<>();
}
