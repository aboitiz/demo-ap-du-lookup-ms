package com.apc.template.model;

import com.apc.template.model.base.AuditableEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends AuditableEntity {

    @Type(type = "yes_no")
    @Column(columnDefinition = "boolean default false")
    private boolean inactive;

    @Column(length = 50)
    private String username;

    @Column(length = 100)
    private String address1;

    @Column(length = 100)
    private String address2;


}
