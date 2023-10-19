package com.wendellemos.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude
public class CarPostDTO {

    private String model;
    private String brand;
    private Double price;
    private String description;
    private String engineVersion;
    private String city;
    private LocalDateTime createdDate;
    private Long ownerId;
    private String ownerName;
    private String ownerType;
    private String contact;
}
