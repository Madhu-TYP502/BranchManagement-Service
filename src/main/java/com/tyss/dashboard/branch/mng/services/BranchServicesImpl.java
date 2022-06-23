package com.tyss.dashboard.branch.mng.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.dashboard.branch.mng.entities.BranchEntity;
import com.tyss.dashboard.branch.mng.model.BranchDto;
import com.tyss.dashboard.branch.mng.repositories.BranchRepository;


@Service
public class BranchServicesImpl implements BranchServices {

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public ResponseEntity<String> addBranch(BranchEntity branchEntity) {

		if (mongoTemplate
				.find(new Query().addCriteria(Criteria.where("branchName").is(branchEntity.getBranchName()).and("city")
						.is(branchEntity.getCity()).and("type").is(branchEntity.getType())), BranchEntity.class)
				.size() == 0) {
			branchEntity.setId(branchEntity.hashCode());
			branchEntity.setBranchName(branchEntity.getBranchName().toLowerCase());
			branchEntity.setBranchManager(branchEntity.getBranchManager().toLowerCase());
			branchRepository.save(branchEntity);

			return ResponseEntity.status(HttpStatus.CREATED).body("Branch added successfully!!");

		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Branch already exists!!");
	}

	@Override
	public ResponseEntity<String> deleteBranch(BranchDto branchDto) {
		BranchEntity branchEntity = null;
		try {
			branchEntity = mongoTemplate
					.find(new Query().addCriteria(Criteria.where("branchName").is(branchDto.getBranchName()).and("city")
							.is(branchDto.getCity()).and("type").is(branchDto.getType())), BranchEntity.class)
					.get(0);

			branchRepository.delete(branchEntity);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Branch deleted!!");
		} catch (IndexOutOfBoundsException ie) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch not found!!");

		}
	}

	@Override
	public ResponseEntity<BranchEntity> viewBranch(String branchName, String city, String type) {
		BranchEntity branchEntity = null;
		try {
			branchEntity = mongoTemplate.find(
					new Query().addCriteria(
							Criteria.where("branchName").is(branchName).and("city").is(city).and("type").is(type)),
					BranchEntity.class).get(0);

			return ResponseEntity.status(HttpStatus.FOUND).body(branchEntity);
		} catch (IndexOutOfBoundsException ie) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(branchEntity);

		}
	}

	@Override
	public ResponseEntity<List<BranchEntity>> viewAllBranches() {

		return ResponseEntity.status(HttpStatus.OK).body(branchRepository.findAll());
	}

	@Override
	public ResponseEntity<List<BranchEntity>> viewBranchesByCity(String city) {

		return ResponseEntity.status(HttpStatus.OK).body(branchRepository.findAllByCity(city));
	}

	

}
