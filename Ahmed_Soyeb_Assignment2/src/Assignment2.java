import java.io.FileInputStream; //use this for reading Input File"

import java.io.FileNotFoundException;

public class Assignment2{
	
	public static void main(String args[]) throws FileNotFoundException{
		
		FileInputStream fstream = new FileInputStream("Assignment2.txt");
					
		DequeUsingStacks<String> deque = new DequeUsingStacks<String>(); //create new objects to with DequeUsingStacks
		
		new Command(fstream, deque); //create objects to manipulate command operation
		
	}
}
