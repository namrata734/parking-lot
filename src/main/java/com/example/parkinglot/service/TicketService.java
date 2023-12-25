package com.example.parkinglot.service;

import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.entity.Type;
import com.example.parkinglot.localDB.HeavyVehicle;
import com.example.parkinglot.localDB.Moped;
import com.example.parkinglot.request.CreateTicketReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    public final HeavyVehicle heavyVehicle;
    public final Moped moped;
    public Ticket generateTicket(CreateTicketReq createTicketReq) throws Exception {
        int slot;
        if(createTicketReq.getVehicleType().equals(Type.HEAVY_VEHICLE)){
            slot = heavyVehicle.getSlot();
        }else{
            slot = moped.getSlot();
        }
        Ticket ticket = Ticket.builder()
                .dateTime(LocalDateTime.now())
                .slotNumber(slot)
                .id(String.valueOf(UUID.randomUUID()))
                .vehicleType(createTicketReq.getVehicleType())
                .build();
        if(createTicketReq.getVehicleType().equals(Type.HEAVY_VEHICLE)){
            heavyVehicle.insertIntoHeavyVehicleMap(ticket);
        }else{
            moped.insertIntoMopedMap(ticket);
        }
        return ticket;
    }

    public void onExit(int slot, String vehicleType){
        if(vehicleType.equals(Type.HEAVY_VEHICLE)){
            heavyVehicle.removeFromHeavyVehicleMap(slot);
        }else{
            moped.removeFromMopedMap(slot);
        }
    }
}
