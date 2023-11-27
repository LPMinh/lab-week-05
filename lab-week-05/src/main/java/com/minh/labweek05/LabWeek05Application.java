
		package com.minh.labweek05;

import com.minh.labweek05.enums.SkillLevel;
import com.minh.labweek05.enums.SkillType;
import com.minh.labweek05.model.*;
import com.minh.labweek05.repositories.*;
import com.neovisionaries.i18n.CountryCode;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private JobSkillRepository jobSkillRepository;
	@Autowired
	private JobRepository jobRepository;

//	@Bean
//	CommandLineRunner initData() {
//		Faker faker = new Faker();
//		return args -> {
//			Random rnd = new Random();
//			// Create 500 candidates
//			for (int i = 1; i <= 500; i++) {
//				Account account = new Account(0, faker.internet().emailAddress(), faker.internet().password(), faker.options().option("candidate"));
//				Address address = new Address(faker.address().city(), i, faker.address().zipCode(), faker.address().streetAddress());
//				Candidate candidate = new Candidate(faker.phoneNumber().cellPhone(), LocalDate.now(), faker.internet().emailAddress(), faker.name().fullName(), account, address, new HashSet<>(), new ArrayList<>());
//				addressRepository.save(address);
//				for (int j = 1; j < 5; j++) {
//					String randomSkill = faker.options().option("MASTER", "BEGINER", "ADVANCED", "PROFESSIONAL", "INTERMEDIATE");
//					String randomSkillType = faker.options().option("SOFT_SKILL", "UNSPECIFIC", "TECHNICAL_SKILL");
//					CandidateSkill candidateSkill = new CandidateSkill(SkillLevel.valueOf(randomSkill),
//							new Skill(SkillType.valueOf(randomSkillType), faker.job().title(), faker.job().keySkills(), new ArrayList<JobSkill>()),
//							candidate, faker.job().field());
//					candidate.getCandidateSkills().add(candidateSkill);
//				}
//				for (int k = 1; k < 5; k++) {
//					Experience experience = new Experience(LocalDate.now(), candidate, LocalDate.now(), faker.company().name(), faker.job().position());
//					candidate.getExperience().add(experience);
//				}
//				candidateRepository.save(candidate);
//			}
//			//create 500 companies and 500 jobs for each company
//			for (int i = 1; i <= 500; i++) {
//				Address address = new Address(faker.address().city(), i, faker.address().zipCode(), faker.address().streetAddress());
//				Account account = new Account(0, faker.internet().emailAddress(), faker.internet().password(), faker.options().option("company"));
//				Company company = new Company(
//						faker.company().name(),
//						faker.company().profession(),
//						address,
//						faker.phoneNumber().cellPhone(),
//						faker.company().url(),
//						faker.internet().emailAddress(),
//						new ArrayList<Job>(),
//						account);
//
//				addressRepository.save(address);
//				String randomSkill = faker.options().option("MASTER", "BEGINER", "ADVANCED", "PROFESSIONAL", "INTERMEDIATE");
//				String randomSkillType = faker.options().option("SOFT_SKILL", "UNSPECIFIC", "TECHNICAL_SKILL");
//				Skill skill = new Skill(SkillType.valueOf(randomSkillType), faker.job().title(), faker.job().keySkills(), new ArrayList<JobSkill>());
//				skillRepository.save(skill);
//				Job job = new Job(company, faker.job().title(), faker.job().position(), new ArrayList<JobSkill>());
//				job.getJobSkills().add(new JobSkill(SkillLevel.valueOf(randomSkill), skill, job));
//				jobRepository.save(job);
//				company.getJobs().add(job);
//				companyRepository.save(company);
//			}
//		};
//	}
}




