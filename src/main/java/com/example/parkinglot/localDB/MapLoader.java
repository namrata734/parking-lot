package com.example.parkinglot.localDB;

import com.example.parkinglot.entity.Ticket;

import java.util.HashMap;

public class MapLoader {
    public static int insertIntoMap(Ticket ticket, HashMap<Integer, Ticket> vehicleMap, int key) throws Exception {

        if(vehicleMap == null) {
            vehicleMap = new HashMap<>();
        }
        if(vehicleMap.size() ==10){
            throw new Exception("slots is full");
        }
        vehicleMap.put(key, ticket);
        while(vehicleMap.containsKey(key)){
            key++;
        }
        return key;
    }

    public static void removeFromMap(int key, HashMap<Integer, Ticket> vehicleMap){
        if(vehicleMap.containsKey(key)){
            vehicleMap.remove(key);
        }
    }
    public static Ticket getTicketDetails(int key, HashMap<Integer, Ticket> vehicleMap){
        if (vehicleMap != null && vehicleMap.containsKey(key)){
            return vehicleMap.get(key);
        }
        return null;
    }

}
