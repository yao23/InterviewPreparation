import java.util.LinkedList;

public class AnimalShelter {
	
	
	public static void main(String[] args) {
		Dog a1 = new Dog(); Cat a2 = new Cat();
		Cat a3 = new Cat(); Dog a4 = new Dog();
		Shelter s = new Shelter();
		s.dequeueAny(); s.dequeueCat(); s.dequeueDog();
		s.enqueue(a1); s.dequeueCat();
		s.enqueue(a2); s.enqueue(a3); s.enqueue(a4);
		s.dequeueDog();
	}
	
}

class Shelter {
	LinkedList<Dog> dogs;
	LinkedList<Cat> cats;
	private int order = 0; // set as a timestamp
	
	Shelter() {
		dogs = new LinkedList<Dog>();
		cats = new LinkedList<Cat>();
	}
	
	public void enqueue(Animal a) {
		a.SetId(order);
		order++;
		if( a instanceof Dog ) {
			dogs.add((Dog)a);
			System.out.println("Dog " + a.GetId() + " is in shelter!");
		}
		else {
			cats.add((Cat)a);
			System.out.println("Cat " + a.GetId() + " is in shelter!");
		}
	}
	
	public Animal dequeueAny() {
		if( dogs.isEmpty() && cats.isEmpty() ) {
			System.out.println("Shelter is empty! Cannot Dequeue!");
			return null;
		}
		else if( dogs.isEmpty() )
			dequeueCat();
		else if( cats.isEmpty() )
			dequeueDog();
		
		Dog dog = dogs.getFirst();
		Cat cat = cats.getFirst();
		
		if( dog.IsOlderThan(cat) ) {
			System.out.println("Dog " + dogs.getFirst().GetId() + " is out!");
			return (Animal)dogs.removeFirst();
		}			
		else {
			System.out.println("Cat " + cats.getFirst().GetId() + " is out!");
			return (Animal)cats.removeFirst();			
		}
			
	}
	
	public Cat dequeueCat() {
		if( cats.isEmpty() ) {
			System.out.println("No cat now! Cannot Dequeue!");
			return null;
		}
		System.out.println("Cat " + cats.getFirst().GetId() + " is out!");
		return cats.removeFirst();
	}
	
	public Dog dequeueDog() {
		if( dogs.isEmpty() ) {
			System.out.println("No dog now! Cannot Dequeue!");
			return null;
		}
		System.out.println("Dog " + dogs.getFirst().GetId() + " is out!");
		return dogs.removeFirst();
	}
}

abstract class Animal {
	private int id;
	private String category;
	Animal() {
		
	}
	Animal(int i) {
		id = i;
		category = "animal";
	}
	Animal(String s) {
		category = s;
	}
	public int GetId() {
		return id;
	}
	public String GetCategory() {
		return category;
	}
	public void SetId(int i) {
		id = i;
	}
	public void SetCategory(String c) {
		category = c;
	}
	public boolean IsOlderThan(Animal a) {
		return (this.GetId() <= a.GetId());
	}
}

class Dog extends Animal {
	Dog() {
		super("dog");
	}
}

class Cat extends Animal {
	Cat() {
		super("cat");
	}
}