package com.tyss.dashboard.branch.mng.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tyss.dashboard.branch.mng.entities.BranchEntity;
import com.tyss.dashboard.branch.mng.model.BranchDto;


public interface BranchServices {

	public ResponseEntity<String> addBranch(BranchEntity branchEntity);

	public ResponseEntity<String> deleteBranch(BranchDto branchDto);

	public ResponseEntity<BranchEntity> viewBranch(String branchName, String city, String type);

	public ResponseEntity<List<BranchEntity>> viewAllBranches();

	public ResponseEntity<List<BranchEntity>> viewBranchesByCity(String City);

}
