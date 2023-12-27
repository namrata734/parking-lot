package com.example.parkinglot.localDB;

import com.example.parkinglot.entity.Extention;
import com.example.parkinglot.utility.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Pass {

    Map<Integer, Integer> passMap = new HashMap<>();
    int milisecond = 24*3600*1000;
    public final PriceCalculator priceCalculator;

    public int addPass(int vehicleNumber, int days, String vehicleType){
        int price =  priceCalculator.calculatePrice((int)System.currentTimeMillis()-days*milisecond, vehicleType,vehicleNumber);
        passMap.put(vehicleNumber, (int) System.currentTimeMillis()+ days*milisecond);
        return price;
    }

    public Extention getPass(int vehicleNumber){
        if(passMap.containsKey(vehicleNumber)){
            if(passMap.get(vehicleNumber)>(int)System.currentTimeMillis()){
                return new Extention(true, 0);
            }else{
                return new Extention(false, passMap.get(vehicleNumber));
            }
        }
        return new Extention(false, 0);
    }
}
