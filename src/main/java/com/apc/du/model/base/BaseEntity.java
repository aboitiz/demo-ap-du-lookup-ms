package com.apc.du.model.base;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * The type Base entity.
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The Id.
     */
    @Id
    @Column( updatable = false, nullable = false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * The Created time.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column( updatable = false )
    protected Date createdAt;

    public <T> void updateEntity(T updateEntity) throws IllegalAccessException {
        Field fields[] = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.get(updateEntity) != null) {
                f.set(this, f.get(updateEntity));
            }
        }
    }
}
