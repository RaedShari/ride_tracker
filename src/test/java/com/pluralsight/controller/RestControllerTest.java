package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;
// @Path("/test/java/com/pluralsight/controller")
public class RestControllerTest {


	RestTemplate restTemplate = new RestTemplate();


	public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
		return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
	}

	
	@Test(timeout=3000)
	public void testGetRides() {

		List<Ride> rides = this.exchangeAsList("http://localhost:8080/ride_tracker/rides", new ParameterizedTypeReference<List<Ride>>() {});


		// ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
		// 		"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
		// 		null, new ParameterizedTypeReference<List<Ride>>() {
		// 		});
		// List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}

	@Test(timeout=3000)
	public void testCreateRide() {
		Ride ride = new Ride();
		ride.setName("Bike");
		ride.setDuration(120);

		restTemplate.put("http://localhost:8080/ride_tracker/rides",ride);

	}
}
