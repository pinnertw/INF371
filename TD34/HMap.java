class HMap{
	int size;
	EntryList[] t;

// Initialisation
	HMap(int n) {
		size = n;
		t = new EntryList[n];
	}
	HMap() {
		size = 20;
		t = new EntryList[size];
	}

// find
	public static HMap tab = new HMap();
	WordList find(Prefix key) {
		int h = key.hashCode(tab.size);
		for(EntryList cur = tab.t[h]; cur != null; cur = cur.next){
			if (Prefix.eq(cur.head.key, key)) return cur.head.value;
		}
		return null;
	}

// addSimple Prefix key, String w
	void addSimple(Prefix key, String w) {
		
	}
}
