package com.andre.rockSevenAssignment;

// import com.andre.rockSevenAssignment.service.BoatService;
// import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RockSevenAssignmentApplication implements CommandLineRunner{

	// @Autowired
	// BoatService boatService;

	public static void main(String[] args) {
		SpringApplication.run(RockSevenAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		storeData was only run once and used to import the data
		from the positions.json file into a mongo database.
		*/
		//boatService.storeData();

		/*
		calculateVesselSightings was only run once and used to strip the
		data down to the import information and load it into a seperate
		collection in the mongo db for futher processing.
		*/
		//boatService.calculateVesselSightings(); 
	}
}
