package il.ac.tau.cs.sw1.riddle.c;

public class A {
	
	private short k = 1;
	private static int l;
	
	public A(int l) {
		this.l=l;
	}
	
	public int getK() {
		return k;
	}
	
	public int getL() {
		return l;
	}

	public static int getFirst() {
		System.out.println("001");
		return 1;
	}
	
	private static int getFirst(int i) {
		System.out.println("002");
		return -10;
	}

	public static int getSecond() {
		System.out.println("003");
		return 1;
	}
	
	public static int getSecond(int i) {
		System.out.println("004");
		return l+i;
	}

	public static int getThird() {
		System.out.println("005");
		return l;
	}

	public short getThird(int i) {
		System.out.println("006");
		return k;
	}
	
	public int getForth() {
		System.out.println("007");
		return getForth(0);
	}
	
	public static int getForth(int i) {
		System.out.println("008");
		return l+i;
	}

}
