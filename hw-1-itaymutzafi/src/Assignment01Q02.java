

public class Assignment01Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		int end = Integer.parseInt(args[0]);
		double sum = 0;
		for (int i =0; i<end; i++) {
		 sum += ((i % 2 == 0 ? 1 : -1)*(1.0/(2*i+1)));
		}
		piEstimation = 4*sum;		
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
