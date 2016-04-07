package tvs.proj.testng;

import java.util.ArrayList;
import java.util.List;

import tvs.proj.testng.exceptions.*;

public class Habitat {
	
	private int area;
	private int maxAnimals;
	private Zoo zoo;
	private List<Animal> animalList;
	
	private String htype;
	
	public Habitat(int area, List<Animal> animals, int maxAnimals, Zoo zoo) throws
	InvalidOperationException
	{	
			if(animals.isEmpty()) throw new InvalidOperationException();
			if(maxAnimals<animals.size()) throw new InvalidOperationException();
			if((area/animals.size())<4) throw new InvalidOperationException();
			
			htype = animals.get(0).getSpecies();
			for(Animal a : animals) {
				if(!htype.equals(a.getSpecies()) && !zoo.has(a))
					throw new InvalidOperationException();
			}
			
			this.area=area;
			animalList=animals;
			this.maxAnimals=maxAnimals;
			this.zoo=zoo;
			
			for(Animal a : animalList) {
				a.setSatisfaction(area/animals.size());
			}
	}
	
	void add(Animal animal) throws InvalidOperationException {
		if(zoo.has(animal) && animal.getSpecies().equals(htype) && animalList.size()<maxAnimals && (area/(animalList.size()+1))>=4) {
			animalList.add(animal);
			for(Animal a : animalList) {
				a.setSatisfaction(area/animalList.size());
			}
		} else throw new InvalidOperationException();
	}
	
	void remove(Animal animal) throws InvalidOperationException {
		if(animalList.size()>1) {
			animalList.remove(animal);
			for(Animal a : animalList) {
				a.setSatisfaction(area/animalList.size());
			}
		} else throw new InvalidOperationException();
	}
	
	public void setArea(int area) throws InvalidOperationException {
		
		if((area/animalList.size())<4) throw new InvalidOperationException();
		
		int sum=0;
		for(Habitat h : zoo.getHabitats()) {
			sum+=h.getArea();
		}
		sum-=getArea();
		sum+=area;
		
		if(zoo.getArea() < sum) throw new InvalidOperationException();
		
		this.area=area;
		
		for(Animal a : animalList) {
			a.setSatisfaction(area/animalList.size());
		}
	}
	
	public int getSatisfaction(Animal a) throws NotInHabitatException {
		if(!has(a)) throw new NotInHabitatException();
		else return a.getSatisfaction();
	}
	
	public int getArea() {return area;}
	public boolean has(Animal animal) {return animalList.contains(animal);}
	public List<Animal> getAnimals() { return new ArrayList<Animal>(); }
	public Zoo getZoo() {return zoo;}
	public String getHabitatType() {return htype;}

}