package com.example.parkinglot.localDB;


import com.example.parkinglot.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class HeavyVehicle {
    HashMap<Integer, Ticket> heavyVehicleMap = new HashMap<>();
    private int key = 1;

    public void insertIntoHeavyVehicleMap(Ticket ticket) throws Exception {
       int slot = MapLoader.insertIntoMap(ticket, heavyVehicleMap, key);
       key = slot;
    }

    public void removeFromHeavyVehicleMap(int key){
        this.key = key;
       MapLoader.removeFromMap(key, heavyVehicleMap);
    }
    public Ticket getHeavyVehicleTicketDetails(int key){
       return MapLoader.getTicketDetails(key, heavyVehicleMap);
    }
    public int getSlot(){
        return key;
    }
}
