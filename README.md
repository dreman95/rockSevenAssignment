## Rock Seven Assignment.

* Table of average boat sightings can be found in the boatSightingsTable.html file.
* An assumption was made that a boat can no longer be seen after a distance of 15 miles.

# Steps taken to produce table

* Imported the data from the postions.json file into a Mongo database. see 'storeData' function.
* Processed that data from the database and transformed it for easier processing which was then stored in a separate collection for futher processing. See 'calculateVesselSightings' function.
* Retrived the transformed data from the new collection and used that data to calculate the average sightings per boat. see 'calculateAverageSightings' function
* after the all calculations were made i used thymeleaf to present the data in a HTML document which can be found in the root of this repo(boatSightingsTable.html)