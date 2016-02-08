public class Assig2C{
	public static void main(String[] args){
		MyStringBuilder msb = new MyStringBuilder();
		System.out.println("Testing append...");
		msb.append("this is a sentence");
		System.out.println(msb);
		msb.reverse();
		System.out.println("\nTesting reverse...");
		System.out.println(msb);
	}
}
