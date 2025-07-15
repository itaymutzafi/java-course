
public class Assignment01Q03 {

	public static void main(String[] args) {
		int numOfOdd = 0;
		int n = Integer.parseInt(args[0]);
		int a = 1;
		int b = 1;
		
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		System.out.print(a + " " + b);
		numOfOdd +=2; //* print and update oddNum with 1,1
		for (int i=2; i<n; i++) {
			int c = a+b;
			System.out.print(" "+ c);
			if (c %2 == 1){
				numOfOdd+= 1;
			}
			a=b;
			b=c;		
			
		}
		
		System.out.println();
		System.out.println("The number of odd numbers is: "+numOfOdd);

	}
}
