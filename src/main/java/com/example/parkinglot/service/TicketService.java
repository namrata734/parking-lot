package com.example.parkinglot.service;

import com.example.parkinglot.entity.Ticket;
import com.example.parkinglot.entity.Type;
import com.example.parkinglot.localDB.HeavyVehicle;
import com.example.parkinglot.localDB.Moped;
import com.example.parkinglot.localDB.Pass;
import com.example.parkinglot.request.CreateTicketReq;
import com.example.parkinglot.utility.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    public final HeavyVehicle heavyVehicle;
    public final Moped moped;
    public  final PriceCalculator priceCalculator;
    public final Pass pass;

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
                .vehicleNumber(createTicketReq.getVehicleNo())
                .build();

        if(createTicketReq.getVehicleType().equals(Type.HEAVY_VEHICLE)){
            heavyVehicle.insertIntoHeavyVehicleMap(ticket);
        }else{
            moped.insertIntoMopedMap(ticket);
        }

        return ticket;
    }

    public int onExit(int slot, String vehicleType){

        int price =0;

        if(vehicleType.equals(Type.HEAVY_VEHICLE)){
            Ticket ticket = heavyVehicle.getHeavyVehicleTicketDetails(slot);
            int timeInMiliSecond = (int) (ticket.getDateTime()).atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
            price = priceCalculator.calculatePrice(timeInMiliSecond, ticket.getVehicleType(), ticket.getVehicleNumber());
            heavyVehicle.removeFromHeavyVehicleMap(slot);
        }else{
            Ticket ticket = moped.getMopedTicketDetails(slot);
            int timeInMiliSecond = (int) (ticket.getDateTime()).atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
            price = priceCalculator.calculatePrice(timeInMiliSecond, ticket.getVehicleType(), ticket.getVehicleNumber());
            moped.removeFromMopedMap(slot);
        }

        return price;
    }

    public int addPass(int vehicleNo, int days, String vehicleType){
       return pass.addPass(vehicleNo, days, vehicleType);
    }
}
