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
}
