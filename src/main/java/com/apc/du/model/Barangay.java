package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "barangay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Barangay extends AuditableEntity {

    @Column(name = "code", length = 255)
    private String code;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(length = 50)
    private String du;

    @Column(name = "city_code", length = 255)
    private String cityCode;
}
