public class UnionFind{
// declaration
	static int[] equiv = new int[Percolation.length];
	//equiv = new int[a.length];
	
// find
	public static int find(int x) {
		while (equiv[x] != x) x = equiv[x];
		return x;
	}

//union
	public static int union(int x, int y) {
		equiv[y] = equiv[x];
		return equiv[y];
	}
}

