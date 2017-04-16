package fr.tp2sir.tp2sir;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("ElectronicDevice")
public class ElectronicDevice extends Device{

	
	private long consommation;
	private Person personnes;
	
	public ElectronicDevice() {
		super();
	}
	public ElectronicDevice(long cconsommation) {
		super();
		this.consommation = cconsommation;
	}
	

	public long getCconsommation() {
		return consommation;
	}
	public void setCconsommation(long cconsommation) {
		this.consommation = cconsommation;
	}
	@ManyToOne
	public Person getPersonnes() {
		return personnes;
	}
	public void setPersonnes(Person personnes) {
		this.personnes = personnes;
	}
	
	
}
