class Prefix {
	String[] t;

	final static String start = "<START>", end = "<END>", par = "<PAR>";

// Prefix
	Prefix(int n) {
		t = new String[n];
		for(int i = 0; i < n; i++)
			t[i] = start;
	}
// eq
	static boolean eq(Prefix p1, Prefix p2) {
		if (p1.t.length != p2.t.length) return false;
		else {
			for (int i = 0; i < p1.t.length; i++) {
				if (!(p1.t[i].equals(p2.t[i]))) return false;
			}
		}
		return true;
	}

// addShift
	Prefix addShift(String w) {
		String a;
		Prefix p = new Prefix(this.t.length);
		for(int i = 0; i < this.t.length; i++) {
			if (i == this.t.length - 1) p.t[i] = w;
			else p.t[i] = this.t[i + 1];
		}
		return p;
	}

// hashCode
	public int hashCode() {
		int h = 0;
		for(int i = 0; i < this.t.length - 1; i++) 
			h = 37 * h + t[i].hashCode();
		return h;
	}
	public int hashCode(int n) {
		int h = 0;
		for (int i = 0; i < this.t.length - 1; i++) 
			h = (37 * h + t[i].hashCode()) % n;
		if (h < 0) return h + n;
		else return h;
	}


// test(main)
	static Prefix ppp = new Prefix(5);
	static Prefix pp = new Prefix(3);

	public static void main(String[] args){
	System.out.println(eq(ppp, pp));
	System.out.println(55 % 7);
	}
}
