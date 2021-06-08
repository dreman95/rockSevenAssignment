package com.andre.rockSevenAssignment.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.andre.rockSevenAssignment.model.AverageSightings;
import com.andre.rockSevenAssignment.model.BoatSatistic;
import com.andre.rockSevenAssignment.model.BoatTeam;
import com.andre.rockSevenAssignment.model.Positions;
import com.andre.rockSevenAssignment.model.Sightings;
import com.andre.rockSevenAssignment.repository.BoatSatisticRepository;
import com.andre.rockSevenAssignment.repository.BoatTeamRepository;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatService {
    @Autowired
	private BoatTeamRepository boatTeamRepository;

	@Autowired
	private BoatSatisticRepository boatSatisticRepository;
	
	/**
	Gets all documents from boatSatistic collection and
	calculates the average number of sightings per boat
	for each day. 
	@return ArrayList<AverageSightings> AverageSightings
	*/
    public ArrayList<AverageSightings> calculateAverageSightings() {
		ArrayList<AverageSightings> boatSightings = new ArrayList();

		int day = 1;
		final int END_OF_RACE = 39;
		List<BoatSatistic> boats = boatSatisticRepository.findAll();
		ArrayList<Integer> numOfSightingsOnDay = new ArrayList();

		for (BoatSatistic boat : boats) {
            
			while (day != END_OF_RACE) {
                AverageSightings averageSightings = new AverageSightings();
                averageSightings.setName(boat.getTeamName());

				for (Sightings sighting : boat.getSightings()) {
					if (sighting.getDayOfRace() == day) {
						numOfSightingsOnDay.add(sighting.getNumOfBoatsSeen());
					}
				}
			
                averageSightings.setDay(day);
                averageSightings.setAverageNoSightings(calculateAverage(numOfSightingsOnDay));
                boatSightings.add(averageSightings);
				numOfSightingsOnDay.clear();
				day++;
			}
			// Reset to day one for next boat
			day = 1;
		}
        return boatSightings;
	}

	/**
	Calculates the average of an array of numbers
	@param ArrayList<Integer> numbers
	@return int average
	*/
	public int calculateAverage(ArrayList<Integer> numbers) {

		int sum = 0;
		for (int num : numbers) {
			sum += num;
		}
		if(!numbers.isEmpty()) {
			try{
				return sum / numbers.size();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
		
	}

	/**
	Calculates the number of other boats in range of each for boat
	for a given time. 

	Strips the data down from the boatTeam collection 
	and imports the data into the boatSatistic collection for futher processing.
	*/
	public void calculateVesselSightings() {
		int i = 0;
		int NumOfBoatsInRange = 0;
	
		for (BoatTeam team : boatTeamRepository.findAll()) {
		
			for (Positions pos : team.getPositions()) {
				String teamPosTimestamp = pos.getGpsAt();

				// Find all teams that have the same timestamp as this boat position
				List<BoatTeam> teamsPosSameTimeStamp = boatTeamRepository.findBytimeStamp(teamPosTimestamp);
				DateTime dateTime = DateTime.parse(teamPosTimestamp);
				int raceDay = calculateRaceDay(dateTime.get(DateTimeFieldType.dayOfYear()));

				for (BoatTeam teamWithSameTimePos : teamsPosSameTimeStamp) {
					if (!teamWithSameTimePos.getName().equals(team.getName())) {
						// Look for position that contains the same timestamp
						for (Positions OtherTeamPositions : teamWithSameTimePos.getPositions()) {

							if (OtherTeamPositions.getGpsAt().equals(teamPosTimestamp)) {
								// Calculate the distance between the two ships at position x
								double distance = calculateBoatDistance(pos.getLatitude(), pos.getLongitude(),
										OtherTeamPositions.getLatitude(), OtherTeamPositions.getLongitude());
								if (inRange(distance)) {
									NumOfBoatsInRange += 1;
								}

								break;
							}

						}

					}
				}

				// Store Data for sightings analysis
				if (NumOfBoatsInRange != 0) {
					BoatSatistic boat = boatSatisticRepository.findByName(team.getName());
					if (boat == null) {
						boatSatisticRepository.save(new BoatSatistic(team.getName(),
								new Sightings(raceDay, teamPosTimestamp, NumOfBoatsInRange)));
					} else {
						boat.setSightings(new Sightings(raceDay, teamPosTimestamp, NumOfBoatsInRange));
						boatSatisticRepository.save(boat);
					}
				}
				NumOfBoatsInRange = 0;
				i++;
			}
			i = 0;

		}
	}

   /**
	Calculates the distance between two boats in miles.
	@param Double latitude1
	@param Double longitude1
	@param Double latitude2
	@param Double longitude2
	@return double - The distance in miles
	*/
   public double calculateBoatDistance(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {
		
		double dLat = Math.toRadians(latitude2 - latitude1);
		double dLng = Math.toRadians(longitude2 - longitude1);

		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);

		double a = Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2));

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double earthRadius = 3958.75;
		double dist = earthRadius * c;

		return dist;
	}

    /**
	Outputs true if boat can be seen from a distance
	else false. Assumption is boat disapears from eye level
	when the distance exceeds 15 miles.
	@param double distance
	@return boolean - true if in range, else false
	*/
   public boolean inRange(double dist) {
	   final int EYE_RANGE = 15;
		if (dist <= EYE_RANGE) {
			return true;
		}
		return false;
	}

	 /**
	Calculates the day of the race based of the
	the day of year from the timestamp of the data.
	@param int dayOfYear
	@return int - the day of the race
	*/
	public int calculateRaceDay(int dayOfYear) {
		final int START_OF_RACE_DAY = 323;
		final int END_OF_RACE_DAY = 360;

		if (dayOfYear == START_OF_RACE_DAY) {
			// Return day 1 of the race
			return 1;
		} else if (dayOfYear == END_OF_RACE_DAY) {
			// Day 38 is last day of the race
			return 38;
		} else {
			return dayOfYear - START_OF_RACE_DAY + 1;
		}
	}

	 /**
	Parses the data from the positions.json file
	and imports the data into a mongoDB collection
	called boatTeam.
	*/
	public void storeData() {
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(
					"//C:/Users/Andre/Documents/rock7Assignment/rockSevenAssignment/src/main/java/com/andre/rockSevenAssignment/positions.json"));
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray teams = (JSONArray) jsonObject.get("teams");

			for (int i = 0; i < teams.size(); i++) {
				JSONObject teamObj = (JSONObject) teams.get(i);
				JSONArray positionsArray = (JSONArray) teamObj.get("positions");
				ArrayList<Positions> positions = new ArrayList<>();

				for (int j = 0; j < positionsArray.size(); j++) {

					JSONObject posObj = (JSONObject) positionsArray.get(j);
					Positions teamPositions = new Positions();

					teamPositions.setAlert((boolean) posObj.get("alert"));
					teamPositions.setAltitude((Long) posObj.get("altitude"));
					teamPositions.setType(posObj.get("type").toString());
					teamPositions.setDtfkm((Double) posObj.get("dtfKm"));
					teamPositions.setId((Long) posObj.get("id"));
					teamPositions.setGpsAt(posObj.get("gpsAt").toString());
					teamPositions.setSogKnots((Double) posObj.get("sogKnots"));
					teamPositions.setBattery((Long) posObj.get("battery"));
					teamPositions.setCog((Long) posObj.get("cog"));
					teamPositions.setDtfNm((Double) posObj.get("dtfNm"));
					teamPositions.setTxAt(posObj.get("txAt").toString());
					teamPositions.setLongitude((Double) posObj.get("longitude"));
					teamPositions.setLatitude((Double) posObj.get("latitude"));
					teamPositions.setGpsAtMillis((Long) posObj.get("gpsAtMillis"));
					teamPositions.setSogKmph((Double) posObj.get("sogKmph"));

					positions.add(teamPositions);
				}

				Long marker = (Long) teamObj.get("marker");
				String name = teamObj.get("name").toString();
				Long serial = (Long) teamObj.get("serial");

				boatTeamRepository.save(new BoatTeam(marker, name, serial, positions));
			}
			System.out.println("=========================================================");
			System.out.println("=======================FINISHED=========================");
			System.out.println("=========================================================");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
