package com.tyss.dashboard.branch.mng.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.dashboard.branch.mng.entities.BranchEntity;
import com.tyss.dashboard.branch.mng.repositories.BranchRepository;

@Service
public class BranchServicesImpl implements BranchServices {

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public ResponseEntity<BranchEntity> addBranch(BranchEntity branchEntity) {

		if (mongoTemplate
				.find(new Query().addCriteria(Criteria.where("branchName").is(branchEntity.getBranchName()).and("city")
						.is(branchEntity.getCity()).and("type").is(branchEntity.getType())), BranchEntity.class)
				.size() == 0) {
			branchEntity.setId(UUID.randomUUID().toString());
			branchEntity.setBranchName(branchEntity.getBranchName());
			branchEntity.setBranchManager(branchEntity.getBranchManager().toLowerCase());
			branchRepository.save(branchEntity);

			return ResponseEntity.status(HttpStatus.OK).body(branchEntity);

		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@Override
	public ResponseEntity<String> deleteBranch(String branchID) {

		BranchEntity branchEntity = null;

		branchEntity = branchRepository.findById(branchID).get();
		if (branchEntity != null) {

			branchRepository.delete(branchEntity);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Branch deleted!!");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch not found!!");

	}

	@Override
	public ResponseEntity<BranchEntity> viewBranch(String branchName) {
		
		BranchEntity branchEntity = branchRepository.findByBranchName(branchName);
		if (branchEntity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(branchEntity);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(branchEntity);

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
