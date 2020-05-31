class WordList {
	Node content;
	
	WordList() {
		content = null;
	}
	WordList(Node l){content = l;};
// Declation of foobar and Vide
	static WordList foobar = new WordList(new Node("foo", new Node("bar", new Node("baz", null))));
	static WordList Vide = new WordList();
// is Empty
	boolean isEmpty() {return(content == null);}

// Methode length
	int length() {
		int count = 0;
		WordList word = new WordList(this.content);
		for (WordList cur = word; cur.content != null; cur.content = cur.content.next) {
			count += 1;
		}
		return count;
	}

// Methode Print
	String print() {
		String pri;
		WordList l;
		if (this.content == null) pri = "";
		else {
			pri = this.content.head;
			l = new WordList(this.content.next);
			for (WordList cur = l; cur.content != null; cur.content = cur.content.next) {
				pri += ", " + cur.content.head;
			}
	}
	return "[" + pri + "]";
	}

// Methode addFirst void, string
	void addFirst(String w) {
		this.content = new Node(w, this.content);
	}
// Methode addLast
	void addLast(String w) {
		Node.addLast(w, this.content);
		return;
	}
// Methode removeFirst string, ()
	String removeFirst() {
		if (this.content == null) return null;
		String w;
		w = this.content.head;
		this.content = this.content.next;
		return w;
	}
// Methode removeLast
	String removeLast() {
		String w;
		WordList word;
		if (this.content == null) return null;
		else if (this.content.next == null) {
			return this.removeFirst();
		}
		else if (this.content.next.next == null){
			w = this.content.next.head;
			this.content.next = null;
			return w;
		}
		else {
			return Node.removeLast(this.content.next);
		}
	}

// Methode Insert
	void insert(String s){
		this.content = Node.insert(s, this.content);
		return;
	}

// Methode InsertionSort
	void insertionSort() {
		this.content = Node.insertionSort(this.content);
		return;
	}

// WordList a partir d'un tableau de string
	WordList(String[] t) {
		content = null;
		for(int i = t.length - 1; i >= 0; i--) {
			content = new Node(t[i], content);
		}
	}

// to array
	String[] toArray() {
		String[] tab = new String[this.length()];
		WordList w = new WordList(this.content);
		for (int i = 0; i < tab.length ; i++){
			tab[i] = w.content.head;
			w.content = w.content.next;
		}
		return tab;
	}

// Main
	public static void main(String[] args) {
	String[] tab = foobar.toArray();
	WordList tabl = new WordList(tab);
	System.out.println(tabl.print());

	System.out.println(foobar.print());
	foobar.insert("w");
	System.out.println(foobar.print());
	foobar.insertionSort();
	System.out.println(foobar.print());

	}

}

