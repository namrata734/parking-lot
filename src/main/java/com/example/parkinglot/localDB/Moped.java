package com.example.parkinglot.localDB;

import com.example.parkinglot.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Moped {

    HashMap<Integer, Ticket> mopedMap = null;
    private int key = 1;

    public void insertIntoMopedMap(Ticket ticket) throws Exception {
       int slot = MapLoader.insertIntoMap(ticket, mopedMap, key);
       key = slot;
    }

    public void removeFromMopedMap(int key){
        this.key = key;
       MapLoader.removeFromMap(key, mopedMap);
    }

    public Ticket getMopedTicketDetails(int key){
        return MapLoader.getTicketDetails(key, mopedMap);
    }

    public int getSlot(){
        return key;
    }
}
