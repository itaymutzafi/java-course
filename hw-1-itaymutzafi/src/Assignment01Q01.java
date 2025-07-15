
public class Assignment01Q01 {

	public static void main(String[] args) {
		for (String d : args) {
			char firstchar = d.charAt(0);
			int i;
            i = (int) firstchar;
            if (i % 5 ==0) {
				System.out.println(firstchar);
			}
		}		
	}

}
