package com.apc.du.model.base;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Base entity.
 */
@Data
@MappedSuperclass
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The Id.
     */
    @Id
    @Column( updatable = false, nullable = false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    /**
//     * The Creator id.
//     */
//    @Column( updatable = false )
//    protected String createdBy = "";

    /**
     * The Created time.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column( updatable = false )
    protected Date createdAt;


}
