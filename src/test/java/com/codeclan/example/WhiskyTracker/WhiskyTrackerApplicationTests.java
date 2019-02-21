package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;


	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskyOfYear(){
		List<Whisky> found = whiskyRepository.findWhiskyByYear(2018); // using a derived query
		assertEquals(2, found.size());
	}

	@Test
	public void canGetDistilleryByRegion() {
		List<Distillery> found = distilleryRepository.findDistilleryByRegion("Speyside");
		assertEquals(2, found.size());
	}

	@Test
	public void canGetWhiskiesByDistilleryAndAge() {
		List<Whisky> found = whiskyRepository.findWhiskyByAgeAndDistilleryId(15, 1L);
		assertEquals(1, found.size());
	}

	@Test
	public void findWhiskiesByASpecificDistilleryAndYear() {
		List<Whisky> found = whiskyRepository.findWhiskiesFromADistilleryOfASpecificAge("Glendronach", 12);
		assertEquals("The Glendronach Original", found.get(0).getName());
		assertEquals(1, found.size());
	}

	@Test
	public void canGetWhiskiesByRegion() {
		List<Whisky> found = whiskyRepository.findWhiskiesByRegion("Highland");
		assertEquals(2, found.size());
	}

	@Test
	public void canGetDistilleriesWith12YearOldWhisky() {
		List<Distillery> results = distilleryRepository.findDistilleryByWhiskyAge(12);
		assertEquals(2, results.size());
	}
}
