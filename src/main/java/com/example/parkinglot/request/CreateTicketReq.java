package com.example.parkinglot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketReq {
    private String vehicleType;
    private int vehicleNo;
}
