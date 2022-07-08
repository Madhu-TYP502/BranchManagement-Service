package com.tyss.dashboard.branch.mng.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(value = "Branch")
public class BranchEntity implements Serializable {

	private static final long serialVersionUID = -4088660376879293980L;

	@Id
	private String id;

	@NonNull
	private String branchName;

	@NonNull
	private String type;

	@NonNull
	private String branchManager;

	@NonNull
	private String state;

	@NonNull
	private String city;

	@NonNull
	private String contact;

	@NonNull
	private String supervisorID;

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "branchName : " + branchName + " city : " + city + " state : " + state + " contact : " + contact;
	}

}
