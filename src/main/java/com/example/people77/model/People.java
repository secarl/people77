package com.example.people77.model;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

//load the data's model

@NoArgsConstructor
@Entity
@Data
@Table(name="people")
public class People {
	
	@Id
	private int nationalid;
	
	private String name;
	
    private String lastname;
    
    private int age;
	
    private URL picture;
    
    public int getNationalId() {
		return nationalid;
	}

	public void setNationalId(int nationalid) {
		this.nationalid = nationalid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public URL getPicture() {
		return picture;
	}

	public void setPicture(URL picture) {
		this.picture = picture;
	}
	

}