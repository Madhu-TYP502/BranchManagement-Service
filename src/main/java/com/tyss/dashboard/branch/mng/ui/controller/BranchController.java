package com.tyss.dashboard.branch.mng.ui.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.dashboard.branch.mng.entities.BranchEntity;
import com.tyss.dashboard.branch.mng.model.BranchDto;
import com.tyss.dashboard.branch.mng.services.BranchServicesImpl;

@RestController
@RequestMapping("branch/")
public class BranchController {
	
	@Autowired
	BranchServicesImpl branchServicesImpl;

	ModelMapper mapper;

	{
		mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@PostMapping("add")
	public ResponseEntity<BranchEntity> createBatch(@RequestBody BranchDto branchDto) {
		return branchServicesImpl.addBranch(mapper.map(branchDto, BranchEntity.class));
	}

	@GetMapping("get")
	public ResponseEntity<BranchEntity> viewBranch(@RequestParam String branchName, String city, String type) {

		return branchServicesImpl.viewBranch(branchName);
	}

	@GetMapping("get/all")
	public ResponseEntity<List<BranchEntity>> viewAllBranch() {

		return branchServicesImpl.viewAllBranches();
	}

	@GetMapping("get/all/city")
	public ResponseEntity<List<BranchEntity>> viewAllBranchByCity(@RequestParam String city) {

		return branchServicesImpl.viewBranchesByCity(city);
	}

	@DeleteMapping("delete")
	public ResponseEntity<String> deleteBranch(@RequestParam String branchID) {

		return branchServicesImpl.deleteBranch(branchID);
	}
}
