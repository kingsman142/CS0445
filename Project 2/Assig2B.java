public class Assig2B{
	public static void main(String[] args){
		File testFile = new File(args[0]);
		if(!testFile.exists()) System.exit(0);

		long start = 0; //start time
		long end = 0; //end time

		//Elapsed times
		long sAppend = 0;
		long msbAppend = 0;
		long sbAppend = 0;

		long sDelete = 0;
		long msbDelete = 0;
		long sbDelete = 0;

		long sInsert = 0;
		long msbInsert = 0;
		long sbInsert = 0;

		//Extra calculation
		long averageAppend = 0;
		long averageDelete = 0;
		long averageInsert = 0;

		//APPEND TESTS
		String s = new String();
		start = System.nanoTime();
		s.append();
		end = System.nanoTime();
		sAppend = end-start;

		MyStringBuilder msb = new MyStringBuilder();
		start = System.nanoTime();
		msb.append();
		end = System.nanoTime();
		msbAppend = end-start;

		StringBuilder sb = new StringBuilder();
		start = System.nanoTime();
		sb.append();
		end = System.nanoTime();
		sbAppend = end-start;

		//DELETE TESTS
		s = new String();
		start = System.nanoTime();
		s.delete(0, 1);
		end = System.nanoTime();
		sAppend = end-start;

		msb = new MyStringBuilder();
		start = System.nanoTime();
		msb.delete(0, 1);
		end = System.nanoTime();
		msbAppend = end-start;

		sb = new StringBuilder();
		start = System.nanoTime();
		sb.delete(0, 1);
		end = System.nanoTime();
		sbAppend = end-start;

		//INSERT TESTS
		s = new String();
		start = System.nanoTime();
		s.insert();
		end = System.nanoTime();
		sAppend = end-start;

		msb = new MyStringBuilder();
		start = System.nanoTime();
		msb.insert();
		end = System.nanoTime();
		msbAppend = end-start;

		sb = new StringBuilder();
		start = System.nanoTime();
		sb.insert();
		end = System.nanoTime();
		sbAppend = end-start;
	}
}
