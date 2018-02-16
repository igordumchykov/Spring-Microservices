package com.jdum.booking.common.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author idumchykov
 * @since 10/4/17
 */
@Data
@MappedSuperclass
public class BaseEntity {

    @Transient
    private String DEFAULT_USER_NAME = "admin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "CREATED_BY")
    protected String createdBy;

    @Column(name = "UPDATED_BY")
    protected String updatedBy;

    @Column(name = "CREATED")
    protected Timestamp createdTime;

    @Column(name = "UPDATED")
    protected Timestamp updatedTime;

    @Column(name = "ENABLED")
    protected int enabled = 1;

    @PrePersist
    protected void onCreate() {
        createdTime = new Timestamp(System.currentTimeMillis());
        updatedTime = new Timestamp(System.currentTimeMillis());

        if (createdBy == null)
            createdBy = DEFAULT_USER_NAME;

        if (updatedBy == null)
            updatedBy = DEFAULT_USER_NAME;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = new Timestamp(System.currentTimeMillis());

        if (updatedBy == null)
            updatedBy = DEFAULT_USER_NAME;
    }
}
