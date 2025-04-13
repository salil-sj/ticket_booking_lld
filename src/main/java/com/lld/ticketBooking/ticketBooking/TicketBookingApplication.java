package com.lld.ticketBooking.ticketBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);

		System.out.println("Hello world");

		List<List<Integer>> listOfLists = Arrays.asList(
				Arrays.asList(1, 2),
				Arrays.asList(3, 4),
				Arrays.asList(5)
		);

		List<Integer> result = listOfLists.stream().flatMap(x->x.stream()).collect(Collectors.toList());
		System.out.println(result);

	}

}
