package com.org.smartgrouping;

import com.org.smartgrouping.model.Team;
import com.org.smartgrouping.model.User;
import com.org.smartgrouping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class SmartgroupingApplication {
	/*@Autowired
	private UserRepository userRepository;*/
	public static void main(String[] args) {
		SpringApplication.run(SmartgroupingApplication.class, args);
	}

	/*@Override
	public void run(String[] args) throws ParseException {
		// Create a couple of Book and Publisher
	*//*	SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );  // United States style of format.
		java.util.Date myDate = format.parse( "10/10/2009" );

		java.sql.Date sqlDate = new java.sql.Date( myDate.getTime() );*//*
		userRepository.save(new User("chanaka","colombo","chanaka@gmial.com", 0770256762,null, true, new Team("team", null, true)));
	}*/
}
