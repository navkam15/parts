package com.partshop.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parts")
public class Part {

    @Id
    @Column(name = "part_id", nullable = false)
    private int partId;

    @Column(name = "part_name", length = 50)
    private String partName;

    @Column(name = "part_desc", length = 200)
    private String partDesc;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private User updatedBy;

    @ManyToOne
    @JoinColumn(name = "part_type_code", referencedColumnName = "part_type_code", nullable = false)
    private PartType partType;


    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    private int quantity;


}
