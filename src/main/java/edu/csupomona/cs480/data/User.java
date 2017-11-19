package edu.csupomona.cs480.data;

import java.util.Date;


/**
 * The basic user object.
 */
public class User {

	/** The unique user Id */
    private String id;
    /** The unique user Id */
    private String major;
    /** The timestamp when the user is being created */
    private String creationTime = new Date(System.currentTimeMillis()).toString();
    
    private String firstName;
    private String lastName;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String toString() {
		return String.format("User[firstName='%s', lastName='%s', id='%s']", firstName, lastName, id);
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
