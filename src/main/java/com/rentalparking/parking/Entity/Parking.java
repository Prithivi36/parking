package com.rentalparking.parking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Parking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    private String _id;
    private String userId;
    private String address;
    private String ownerName;
    private GeoJsonPoint location;
    private List<String> vehicleAllowed;
    private boolean available;
    private Double totalRevenue;
    private double pricePerHour;
}
