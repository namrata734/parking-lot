package com.example.parkinglot.utility;

import com.example.parkinglot.entity.Extention;
import com.example.parkinglot.entity.Type;
import com.example.parkinglot.implementation.HeavyVehicle;
import com.example.parkinglot.implementation.Moped;
import com.example.parkinglot.localDB.Pass;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceCalculator {

    public final HeavyVehicle heavyVehicle;
    public final Moped moped;
    public final Pass pass;

    public int calculatePrice(int timeInMiliSecond, String vehicleType, int vehicleNumber){

        int milisecond = 3600000;;
        Extention extention = pass.getPass(vehicleNumber);

        if(extention.isPassFound()){
            return 0;
        }

        if(extention.getMilisecond() != 0){
            timeInMiliSecond = extention.getMilisecond();
        }

        if(System.currentTimeMillis()-timeInMiliSecond> 24*milisecond){
            if(vehicleType.equals(Type.HEAVY_VEHICLE)){
                return heavyVehicle.moreThanTwentyFourGrs();
            }else{
                return moped.moreThanTwentyFourGrs();
            }
        }else if(System.currentTimeMillis()-timeInMiliSecond> 12*milisecond){
            if(vehicleType.equals(Type.HEAVY_VEHICLE)){
                return heavyVehicle.moreThanTwelveHrs();
            }else{
                return moped.moreThanTwelveHrs();
            }
        }else if(System.currentTimeMillis()-timeInMiliSecond> 2*milisecond){
            if(vehicleType.equals(Type.HEAVY_VEHICLE)){
                return heavyVehicle.moreThanTwoHrs();
            }else{
                return moped.moreThanTwoHrs();
            }
        }else{
            if(vehicleType.equals(Type.HEAVY_VEHICLE)){
                return heavyVehicle.lessThanTwoHrs();
            }else{
                return moped.lessThanTwoHrs();
            }
        }
    }
}
