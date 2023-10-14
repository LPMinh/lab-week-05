package com.minh.labweek05;

import com.minh.labweek05.model.Address;
import com.minh.labweek05.model.Candidate;
import com.minh.labweek05.repositories.AddressRepository;
import com.minh.labweek05.repositories.CandidateRepository;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class LabWeek05Application {

	public static void main(String[] args) {
		SpringApplication.run(LabWeek05Application.class, args);
	}
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Bean
	CommandLineRunner initData (){
		return agrs ->{
			Random rnd=new Random();
			for (int i= 1;i< 1000;i++){
//				Address add= new Address(rnd.nextInt(1,1000)+"","Quang Trung","Da Nang",rnd.nextInt(70000,80000)+"", CountryCode.VN);
				Address add= new Address("Da nang",CountryCode.VN,rnd.nextInt(70000,80000),"Quang Trung","da nang");
				addressRepository.save(add);
				Candidate can=new Candidate();
				can.setFullName("Nguyen Van "+i);
				can.setAddress(add);
				can.setId(i);
				can.setDob(LocalDate.of(1998,rnd.nextInt(1,13),rnd.nextInt(1,29)));
				can.setPhone(rnd.nextLong(11111111L,99999999L)+"");
				can.setEmail("nguyenvan"+i+
						"@gmail.com");
				candidateRepository.save(can);
				System.out.println("Candidate "+i+" saved");
			}
		};

	}
}
