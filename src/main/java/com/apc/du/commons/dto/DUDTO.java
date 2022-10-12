package com.apc.du.commons.dto;

import com.apc.du.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DUDTO {

    private String barangayCode;
    private String province;
    private String city;
    private String barangay;
    private String du;

}
