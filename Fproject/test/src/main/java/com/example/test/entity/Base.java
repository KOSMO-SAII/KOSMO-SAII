package com.example.test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@SuperBuilder
public class Base extends BaseTime{
    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;

    @LastModifiedBy
    private String modifiedBy;


}
