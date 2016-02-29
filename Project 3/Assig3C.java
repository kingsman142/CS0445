/*
James Hahn
This program tests out MyStringBuilder2's newly implemented method, reverse().
This program is extra credit, as it is a method in StringBuilder that I added onto MyStringBuilder2.
*/

public class Assig3C{
	public static void main(String[] args){
		MyStringBuilder2 msb2 = new MyStringBuilder2();
		System.out.println("Testing append...");
		msb2.append("this is a sentence");
		System.out.println(msb2);
		msb2.reverse();
		System.out.println("\nTesting reverse...");
		System.out.println(msb2);
	}
}
