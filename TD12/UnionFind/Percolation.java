public class Percolation{
// declaration
	static int size = 5;
	static int length = size * size;
	static boolean[] grid = new boolean[length];
        static boolean[] seen;

// init
	public static void init(){
		for (int i = 0; i < grid.length; i++){
            grid[i] = false;
            UnionFind.equiv[i] = i;
        }
	}

// print
	public static void print(){
		for (int j = 0; j < size; j++){
			for (int i = 0; i < size; i++){
				if (grid[(i + 1)* size - j - 1]) System.out.print("*");
				else System.out.print("-");
			}
			System.out.println("");
		}
	}

// random_shadow()
	public static int random_shadow(){
		int flag = 1;
		double num = 0;
		int num2 = 0;
		while (flag == 1) {
			num = Math.random() * length;
			num2 = (int)num;
			if (!grid[num2]) {
				grid[num2] = true;
				flag = 0;
			}
		}
        return num2;
	}
// detect_path
    public static boolean detect_path(int n, boolean up, boolean down) {
    	int j = n % size;
    	int i = (n - j) / size;
    	if (i < 0 || i == size || j < 0 || j == size || !grid[n]) return false;
    	if (seen[n]) return false;
    	seen[n] = true;
    	if (up && j == size - 1 && grid[n] ) return true;
    	else if (down && j == 0 && grid[n]) return true;
    	else if (j == size - 1)
    		return detect_path(n - 1, up, down)||
    				detect_path(n - size, up, down)||
    				detect_path(n + size, up, down);
    	else if (j == 0)
    		return detect_path(n + 1, up, down)||
    				detect_path(n - size, up, down)||
    				detect_path(n + size, up, down);
    	else {
    		return detect_path(n + 1, up, down)||
    				detect_path(n - 1, up, down)||
    				detect_path(n - size, up, down)||
    				detect_path(n + size, up, down);
    	}
    }
// is_percolation(int n)
/*
    public static boolean is_percolation(int n){
        seen = new boolean[length];
        boolean up = false;
        boolean down = false;
        for (int i = 0; i < length; i++) {
            seen[i] = false;
        }
        up = detect_path(n, true, false);
        for (int i = 0; i < length; i++) {
        	seen[i] = false;
        }
        down = detect_path(n, false, true);
        return up && down;
    }
*/

// is_percolation in exo 9
    public static boolean is_percolation(int n) {
    	boolean flag = false;
    	boolean[] represent;
    	represent = new boolean[length];
    	for (int i = 0; i < length; i++) represent[i] = false;
    	for (int i = 0; i < size; i++) {
    		represent[UnionFind.find(i * size + size - 1)] = true;
    	}
	for (int i = 0; i < size; i++) {
		if (represent[UnionFind.find(i * size)]) flag = true;
	}
	return flag;
    }
// In the table
	public static int in(int x, int k) {
		int j = x % size;
		int i = (x - j) / size;
		if (x < 0) return x + k;
		else if (x > length) return x - k;
		return x;
	}

//propagate_union
	public static void propagate_union(int x){
		int j = x % size;
		int i = (x - j) / size;
		if (i < 0 || i == size || j < 0 || j == size) return;
		if (grid[in(x + 1, 1)]) UnionFind.union(in(x + 1, 1), x);
		if (grid[in(x - 1, 1)]) UnionFind.union(in(x - 1, 1), x);
		if (grid[in(x + size, size)]) UnionFind.union(in(x + size, size), x);
		if (grid[in(x - size, size)]) UnionFind.union(in(x - size, size), x);
		return;
	}

// percolation
	public static double percolation(){
        int j;
        double count = 0;
        init();
        do {
            j = random_shadow();
	    propagate_union(j);
            count += 1.0;
        } while (!is_percolation(j));
        return count / length;
    }

// monte-carlo
	public static double montecarlo(int n){
        double proportion = 0.0;
		for (int i = 0; i < n; i++){
            proportion += percolation();
		}
	    return proportion / n;
		}

// main
	public static void main(String[] args){
		init();
		System.out.println(montecarlo(100));
	}

}
