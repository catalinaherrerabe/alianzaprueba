package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private Integer id;
    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
    private Date dateAdd;

}
