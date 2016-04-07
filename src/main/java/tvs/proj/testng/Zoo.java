package tvs.proj.testng;

import java.util.ArrayList;
import java.util.List;

import tvs.proj.testng.exceptions.*;

public class Zoo {

	private String name;
	private int area;
	private int maxAnimals;
	private List<Animal> animalList;
	private List<Habitat> habitatList;

	public Zoo(String name, int area, int maxAnimals) { 
		this.name=name;
		this.area=area;
		this.maxAnimals=maxAnimals;
		animalList = new ArrayList<Animal>(maxAnimals);
		habitatList = new ArrayList<Habitat>();
	}

	/* Animal related methods */
	// Adds an animal to Zoo
	public void add(Animal a) throws InvalidOperationException {
		if(!has(a) && animalList.size()< maxAnimals)
			animalList.add(a);			
		else throw new InvalidOperationException();
	}

	// Removes the animal from the Zoo if it is not in any habitat of the Zoo.
	// Returns true in this case. Otherwise, returns false and the animal is not removed.
	public boolean remove(Animal a) {
		if(has(a)) {
			for(Habitat h : habitatList) {
				if(h.has(a))
					return false;
			}
			animalList.remove(a);
			return true;
		}
		else return false;
	}

	// Returns all animals of the zoo
	public List<Animal> getAnimals() {return animalList;}

	// Returns true if the animal belongs to the zoo, false otherwise.
	public boolean has(Animal animal) {return animalList.contains(animal);}

	/* Habitat related methods */
	// Adds an habitat to the zoo
	public void add(Habitat h) { 
		int sum=0;
		
		for(Habitat habitat : habitatList) {
			sum+=habitat.getArea();
		}
		
		if((getArea()-sum)>= h.getArea())
			habitatList.add(h);
	}

	// Removes an empty habitat from the zoo.
	public void remove(Habitat h) throws InvalidOperationException {
		if(habitatList.contains(h)&& h.getAnimals().isEmpty()) {			
			habitatList.remove(h);	
		} else throw new InvalidOperationException();
	}

	// Put the animal of Zoo in the habitat of the Zoo. If the animal is already in
	// another habitat it is removed from that habitat.
	public void placeIn(Animal animal, Habitat habitat) throws InvalidOperationException {
		if(has(animal) && habitatList.contains(habitat)) {
			for(Habitat h : habitatList) {
				if(h.has(animal) && h.equals(habitat))
					return;
				if(h.has(animal) && !h.equals(habitat)) {
					h.remove(animal);
					break;
				}
			}//Question: if we put the animal in the same habitat exception or just return?
			habitat.add(animal);
			for(Animal a : animalList) {
				a.setSatisfaction(habitat.getArea()/animalList.size());
			}
		} else throw new InvalidOperationException();
	}
	
	// Returns all habitats of the zoo
	public List<Habitat> getHabitats() {return habitatList;}
	
	// some other stuff
	public int getArea() {return area;}

	public String getName() {return name;}
}
