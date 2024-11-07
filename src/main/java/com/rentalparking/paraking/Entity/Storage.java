package com.rentalparking.paraking.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    @Id
    private String _id;
    private String userId;
    private String address;
    private String ownerName;
    private GeoJsonPoint location;
    private boolean available;
    private String size;
    private double pricePerHour;
}
