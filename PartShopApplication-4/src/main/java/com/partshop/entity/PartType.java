package com.partshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "part_type")
public class PartType {

    @Id
    @Column(name = "part_type_code", nullable = false, length = 10)
    private String partTypeCode;

    @Column(name = "part_type_desc", length = 200)
    private String partTypeDesc;
}
