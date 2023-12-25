package com.example.parkinglot.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {

    private String id;
    private LocalDateTime dateTime;
    private String vehicleType;
    private int slotNumber;
}



