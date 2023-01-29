package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        User user=userRepository3.findById(userId).get();
        ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).get();
      //  if(user==null || parkingLot==null)
         //   throw new Exception("Cannot make reservation");
        Spot a=null;
        List<Spot>spots=parkingLot.getSpotList();
        int n=0;
        for(Spot spot:spots){
            if(spot.getOccupied()) {
                if (spot.getSpotType().equals(SpotType.FOUR_WHEELER))
                    n = 4;
                else if (spot.getSpotType().equals(SpotType.TWO_WHEELER))
                    n = 2;
                else if (spot.getSpotType().equals(SpotType.OTHERS))
                    n = Integer.MAX_VALUE;

                if (n >= numberOfWheels) {
                    if (a == null || a.getPricePerHour() > spot.getPricePerHour()) {
                        a = spot;
                    }
                }
            }

        }
        //if(a==null)
           // throw new Exception("Cannot make reservation");
        Reservation reservation=new Reservation();
        reservation.setSpot(a);
        reservation.setUser(user);
        reservation.setNumberOfHours(timeInHours);
        reservationRepository3.save(reservation);
        return reservation;



    }
}
