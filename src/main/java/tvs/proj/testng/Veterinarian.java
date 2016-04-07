package tvs.proj.testng;

import java.util.List;

import tvs.proj.testng.exceptions.*;

public class Veterinarian {
	
	private String name;
	private List<String> species;
	private int treatedAnimals;
	
	public Veterinarian(String name, List<String> species) {
		this.name=name;
		this.species=species;
		treatedAnimals=0;
	}
	
	public void cure(Animal a) throws CannotCureAnimalException {
		try {
			if(knows(a.getSpecies()) && !(treatedAnimals==100)) {
				a.cure();
				treatedAnimals++;
			} else {
				a.die();
				throw new CannotCureAnimalException();	
			}
		} catch(InvalidOperationException e) {
			throw new CannotCureAnimalException();
		}
	}
	
	public void vaccinate(Animal a, Vaccine vaccine) {
		try{
			a.vaccinate(vaccine);
		} catch(InvalidOperationException e) {
			
		}
	}
	
	public void rest() {treatedAnimals=0;}
	public String getName() {return name;}
	public boolean knows(String species) {return this.species.contains(species);}
}