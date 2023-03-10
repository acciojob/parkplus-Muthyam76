package com.driver.services.impl;


import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
   @Autowired
   ParkingLotRepository parkingLotRepository1;


    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        parkingLotRepository1.save(parkingLot);
        return parkingLot;



    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Spot spot=new Spot();
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        spot.setPricePerHour(pricePerHour);
        spot.setParkingLot(parkingLot);
        spot.setOccupied(false);
        if(numberOfWheels<=2)
            spot.setSpotType(SpotType.TWO_WHEELER);
        else if(numberOfWheels<=4)
            spot.setSpotType(SpotType.FOUR_WHEELER);
        else
            spot.setSpotType(SpotType.OTHERS);
        parkingLot.getSpotList().add(spot);
        parkingLotRepository1.save(parkingLot);

        return spot;



    }

    @Override
    public void deleteSpot(int spotId) {

        spotRepository1.deleteById(spotId);


    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
     /*   ParkingLot parkingLot=null;
        Spot spot=null;
        if(parkingLotRepository1.findById(parkingLotId).isPresent()) {
            parkingLot = parkingLotRepository1.findById(parkingLotId).get();
            if (spotRepository1.findById(spotId).isPresent()) {
                spot = spotRepository1.findById(spotId).get();
                spot.setParkingLot(parkingLot);
                spot.setPricePerHour(pricePerHour);
                spotRepository1.save(spot);
            }
        }
       return spot;*/
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        List<Spot> spots=parkingLot.getSpotList();
        Spot updateSpot=null;

        for(Spot spot:spots){
            if(spot.getId()==spotId){
                updateSpot=spot;
            }
        }
        updateSpot.setPricePerHour(pricePerHour);
        updateSpot.setParkingLot(parkingLot);

        spotRepository1.save(updateSpot);

        return updateSpot;



    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

        parkingLotRepository1.deleteById(parkingLotId);


    }
}
