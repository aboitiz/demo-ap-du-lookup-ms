package com.apc.du.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The type Auditable entity.
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class AuditableEntity extends BaseEntity {
    /**
     * The Modification date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date modifiedAt;
}
