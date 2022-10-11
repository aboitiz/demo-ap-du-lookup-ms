package com.apc.du.model;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Province extends AuditableEntity {

    @Column(length = 255)
    private String provinceCode;

    @Column
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

}
