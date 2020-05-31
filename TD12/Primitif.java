class Primitif {
	static int i = 1;
	static long n = 1;
	static int w = 1;
	public static void main(String[] args) {
		while (w < 20){
			i = i * w;
			n = n * w;
			w++;
		}
		System.out.println(i);
		System.out.println(n);

}
}
