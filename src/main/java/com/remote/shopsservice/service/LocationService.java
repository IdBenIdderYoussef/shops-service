package com.remote.shopsservice.service;

import com.remote.shopsservice.model.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    public double distanceTo(Location location1, Location location2){
        final int R = 6371; // Radius of the earth

        double latitudeDistance = Math.toRadians(location2.getLatitude() - location1.getLatitude());
        double longitudeDistance = Math.toRadians(location2.getLongitude() - location1.getLongitude());
        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(Math.toRadians(location2.getLatitude()))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c ; // in Km

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

}
