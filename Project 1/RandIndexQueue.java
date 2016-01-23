/*
James Hahn

This program implements the RandIndexQueue class which just creates
a queue to be simulated by several other java programs.
*/

import java.util.*;

public class RandIndexQueue<T> implements MyQ<T>, Indexable<T>, Shufflable{
	private T[] myArray;
	private int filledIndex;

	//Suppress warnings for the program
	//Constructor for the object, set the default index to -1 and instantiate the underling array
	@SuppressWarnings("unchecked")
	public RandIndexQueue(int size){
		myArray = (T[]) new Object[size];
		filledIndex = -1;
	}

	//Add an item of type T to the array
	//Return true if successful
	//Return false otherwise
	public boolean addItem(T item){
		try{
			myArray[++filledIndex] = item;
			return true;
		} catch(Exception e){
			return false;
		}
	}

	//Remvoe the "oldest" item in the array and
	//return that item of type T
	public T removeItem(){
		if(filledIndex == -1){
			return null;
		} else{
			T temp = myArray[0];
			filledIndex--;

			for(int i = 0; i < filledIndex+1; i++){
				myArray[i] = myArray[i+1];
			}

			return temp;
		}
	}

	//Get the logical size of the array
	public int size(){
		return (filledIndex + 1);
	}

	//Return the object of type T at index i in the array
	public T get(int i){
		try{
			if(myArray.length < (i+1)) throw new IndexOutOfBoundsException();
			else return myArray[i];
		} catch(IndexOutOfBoundsException e){
			return null;
		}
	}

	//Set index i of the array to an object of type T
	public void set(int i, T item){
		try{
			if(myArray.length < (i+1)) throw new IndexOutOfBoundsException();
			else myArray[i] = item;
			return;
		} catch(IndexOutOfBoundsException e){
			return;
		}
	}

	//Pseudo-randomly shuffle the deck
	//by looping through the array 5 times and swapping two random
	//indices many times
	public void shuffle(){
		Random randNum = new Random();

		for(int i = 0; i < myArray.length*5; i++){
			int rand = Math.abs(randNum.nextInt() % filledIndex);
			int rand2 = Math.abs(randNum.nextInt() % filledIndex);
			T temp = (T) myArray[rand];

			myArray[rand] = (T) myArray[rand2];
			myArray[rand2] = (T) temp;
		}
	}

	//Clear the entire array and make it seem like the array is empty
	public void clear(){
		for(T item: myArray){
			item = null;
		}

		filledIndex = -1;
	}

	//Return true if the array if full
	//Return false otherwise
	public boolean full(){
		if(filledIndex == myArray.length-1) return true;
		else return false;
	}

	//Return true if the array is empty
	//Return false otherwise
	public boolean empty(){
		if(filledIndex == -1) return true;
		else return false;
	}

	//Convert the class to a string by looping through each object in the array and getting
	//its value
	public String toString(){
		String s = "Contents: ";
		for(int i = 0; i < filledIndex+1; i++){
			s += myArray[i] + " ";
		}

		return s;
	}
}
