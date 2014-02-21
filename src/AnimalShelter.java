import java.util.LinkedList;

public class AnimalShelter {
	
	
	public static void main(String[] args) {
		Dog a1 = new Dog(0); Cat a2 = new Cat(1);
		Cat a3 = new Cat(2); Dog a4 = new Dog(3);
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
	
	Shelter() {
		dogs = new LinkedList<Dog>();
		cats = new LinkedList<Cat>();
	}
	
	public void enqueue(Animal a) {
		if( a.GetCategory() == "dog" ) {
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
		
		if( dogs.getFirst().GetId() <= cats.getFirst().GetId() ) {
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

class Animal {
	private int id;
	private String category;
	Animal() {
		
	}
	Animal(int i) {
		id = i;
		category = "animal";
	}
	Animal(int i, String s) {
		id = i;
		category = s;
	}
	public int GetId() {
		return id;
	}
	public String GetCategory() {
		return category;
	}
	public void SetCategory(String c) {
		category = c;
	}
}

class Dog extends Animal {
	Dog(int i) {
		super(i);
		super.SetCategory("dog");
	}
}

class Cat extends Animal {
	Cat(int i) {
		super(i);
		super.SetCategory("cat");
	}
}