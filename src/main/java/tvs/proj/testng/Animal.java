package tvs.proj.testng;

import tvs.proj.testng.exceptions.*;

public class Animal {

	private String name;
	private String species;
	private AnimalState status;
	private Boolean sick;
	
	private int satisfaction;
	
	public static enum AnimalState {SLEEPING, DEAD, AWAKE}
	
	public Animal(String name, String species) {
		this.name=name;
		this.species=species;
		this.status= AnimalState.AWAKE;
		this.sick=false;
	}
	
	public boolean vaccinate(Vaccine vaccine) throws InvalidOperationException {
		if(status.equals(AnimalState.AWAKE)) {
			if(!getSpecies().equals(vaccine.getGroup())) {
				if(!isSick()) sick=true;
				else status=AnimalState.DEAD;
				return false;				
			} else return true;
		} else throw new InvalidOperationException();
	}
	
	public void sicken() throws InvalidOperationException {
		if(status.equals(AnimalState.AWAKE) && !isSick())
			sick=true;
		else throw new InvalidOperationException();
	}
	
	public void die() throws InvalidOperationException {
		if(status.equals(AnimalState.AWAKE))
			status=AnimalState.DEAD;
		else throw new InvalidOperationException();
	}
	
	public void sleep() throws InvalidOperationException {
		if(status.equals(AnimalState.AWAKE))
			status=AnimalState.SLEEPING;
		else throw new InvalidOperationException();
	}
	
	public void awake() throws InvalidOperationException {
		if(status.equals(AnimalState.SLEEPING))
			status=AnimalState.AWAKE;
		else throw new InvalidOperationException();
	}
	
	public void cure(Veterinarian vet) throws InvalidOperationException {
		if(status.equals(AnimalState.AWAKE) && isSick()) {
			try {
				vet.cure(this);
				sick=false;
			} catch(CannotCureAnimalException e) {
				this.status=AnimalState.DEAD;
			}
		} else throw new InvalidOperationException();
	}
	
	public void cure() throws InvalidOperationException {
		if(status.equals(AnimalState.AWAKE) && isSick()) {
			sick=false;
		} else throw new InvalidOperationException();
	}
	
	// Access methods
	public AnimalState getState() {return status;}
	public boolean isSick() {return sick;}
	public String getName() {return name;}

	public String getSpecies() { return species; }
	public int getSatisfaction() {return satisfaction;}
	
	public void setSatisfaction(int area) {
		if(!isSick()) {
			if(area<10)
				satisfaction=15;
			if(area>=10 && area<=20)
				satisfaction=30;
			if(area>=20)
				satisfaction=35;
		} else {
			if(area<=20)
				satisfaction=5;
			else satisfaction=15;
		}
	}
}