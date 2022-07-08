package com.tyss.dashboard.branch.mng.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tyss.dashboard.branch.mng.entities.BranchEntity;


public interface BranchServices {

	public ResponseEntity<BranchEntity> addBranch(BranchEntity branchEntity);

	public ResponseEntity<BranchEntity> viewBranch(String branchName);

	public ResponseEntity<List<BranchEntity>> viewAllBranches();

	public ResponseEntity<List<BranchEntity>> viewBranchesByCity(String City);

	ResponseEntity<String> deleteBranch(String branchID);

}
