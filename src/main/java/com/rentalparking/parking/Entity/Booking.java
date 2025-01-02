package com.rentalparking.parking.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Booking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    private String _id;
    private String spaceId;
    private String userId;
    private String userName;
    private String address;
    private String ownerName;
    private String owner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String totalCost;
    private String status;
}
