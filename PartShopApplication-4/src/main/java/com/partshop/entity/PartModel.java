package com.partshop.entity;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PartModel {

    private int partId;
    private String partName;
    private String partDesc;
    private String partTypeCode;
    private int quantity;


}
