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
      /*  User user=userRepository3.findById(userId).get();
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
                    if (a == null || a.getPricePerHour() >= spot.getPricePerHour()) {
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
        return reservation;*/
        try {

            if (!userRepository3.findById(userId).isPresent() || !parkingLotRepository3.findById(parkingLotId).isPresent()) {
                throw new Exception("Cannot make reservation");
            }

            User user = userRepository3.findById(userId).get();
            ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();
            Spot spotChosen=null;
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
                        if (spotChosen == null || spotChosen.getPricePerHour() >= spot.getPricePerHour()) {
                            spotChosen= spot;
                        }
                    }
                }

            }
            if(spotChosen==null)
             throw new Exception("Cannot make reservation");


            assert spotChosen != null;
            spotChosen.setOccupied(true);

            Reservation reservation = new Reservation();
            reservation.setNumberOfHours(timeInHours);
            reservation.setSpot(spotChosen);
            reservation.setUser(user);

            //Bidirectional
            spotChosen.getReservationList().add(reservation);
            user.getReservationList().add(reservation);

            userRepository3.save(user);
            spotRepository3.save(spotChosen);

            return reservation;
        }
        catch (Exception e){
            return null;
        }





    }
}
