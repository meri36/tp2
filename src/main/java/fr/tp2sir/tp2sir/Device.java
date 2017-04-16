package fr.tp2sir.tp2sir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Device {
	
	private long id;
	private String name;
	
	public Device() {
	}
	
	public Device(String name) {
		super();
		this.name = name;
	}
	
	@Id @GeneratedValue
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
