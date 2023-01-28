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
   // @Autowired
    ParkingLotRepository parkingLotRepository1=new ParkingLotRepository() {
       @Override
       public List<ParkingLot> findAll() {
           return null;
       }

       @Override
       public List<ParkingLot> findAll(Sort sort) {
           return null;
       }

       @Override
       public List<ParkingLot> findAllById(Iterable<Integer> iterable) {
           return null;
       }

       @Override
       public <S extends ParkingLot> List<S> saveAll(Iterable<S> iterable) {
           return null;
       }

       @Override
       public void flush() {

       }

       @Override
       public <S extends ParkingLot> S saveAndFlush(S s) {
           return null;
       }

       @Override
       public void deleteInBatch(Iterable<ParkingLot> iterable) {

       }

       @Override
       public void deleteAllInBatch() {

       }

       @Override
       public ParkingLot getOne(Integer integer) {
           return null;
       }

       @Override
       public <S extends ParkingLot> List<S> findAll(Example<S> example) {
           return null;
       }

       @Override
       public <S extends ParkingLot> List<S> findAll(Example<S> example, Sort sort) {
           return null;
       }

       @Override
       public Page<ParkingLot> findAll(Pageable pageable) {
           return null;
       }

       @Override
       public <S extends ParkingLot> S save(S s) {
           return null;
       }

       @Override
       public Optional<ParkingLot> findById(Integer integer) {
           return Optional.empty();
       }

       @Override
       public boolean existsById(Integer integer) {
           return false;
       }

       @Override
       public long count() {
           return 0;
       }

       @Override
       public void deleteById(Integer integer) {

       }

       @Override
       public void delete(ParkingLot parkingLot) {

       }

       @Override
       public void deleteAll(Iterable<? extends ParkingLot> iterable) {

       }

       @Override
       public void deleteAll() {

       }

       @Override
       public <S extends ParkingLot> Optional<S> findOne(Example<S> example) {
           return Optional.empty();
       }

       @Override
       public <S extends ParkingLot> Page<S> findAll(Example<S> example, Pageable pageable) {
           return null;
       }

       @Override
       public <S extends ParkingLot> long count(Example<S> example) {
           return 0;
       }

       @Override
       public <S extends ParkingLot> boolean exists(Example<S> example) {
           return false;
       }
   };
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        System.out.println(parkingLot.getName());

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
        if(numberOfWheels==2)
            spot.setSpotType(SpotType.TWO_WHEELER);
        else if(numberOfWheels==4)
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
        Spot spot=null;
              if(spotRepository1.findById(spotId).isPresent()) {
                  spot=spotRepository1.findById(spotId).get();

                  spot.setPricePerHour(pricePerHour);
                  ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
                  spotRepository1.save(spot);
                  return spot;
              }
        return spot;


    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

        parkingLotRepository1.deleteById(parkingLotId);


    }
}
