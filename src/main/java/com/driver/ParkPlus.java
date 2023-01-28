package com.driver;


import com.driver.services.impl.ParkingLotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.Id;

@EnableSwagger2
@SpringBootApplication

public class ParkPlus {



	public static void main(String[] args) {
		SpringApplication.run(ParkPlus.class, args);
		//ParkingLotServiceImpl parkingLotService=new ParkingLotServiceImpl();
		//System.out.println(parkingLotService.addParkingLot("a1","abd").getName());


	}

}
