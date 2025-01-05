package com.rentalparking.parking.Security.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("userRegister")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSecuritiy {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
}
