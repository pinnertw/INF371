class Node {
	String head;
	Node next;
	
	Node(String head, Node next) {
		this.head = head;
		this.next = next;
	}

// Length_rec
	static int length_rec(Node l) {
		if (l == null) return 0;
		else if (l.next == null) return 1;
		return 1 + length_rec(l.next);
	}

// Length
	static int length(Node l) {
		int count = 0;
		for (Node cur = l; cur != null; cur = cur.next) {
			count += 1;
		}
		return count;
	}

// Print
	static String printNodes(Node l) {
		String pri;
		if (l == null) pri = "";
		else {
			pri = l.head;
			l = l.next;
			for (Node cur = l; cur != null; cur = cur.next) {
				pri += ", " + cur.head;
			}
		}
		return "[" + pri + "]";
	}

// AddLast
	static void addLast(String s, Node l) {
		if (l == null) l = new Node(s, null);
		else if (l.next == null)
			l.next = new Node(s, null);
		else addLast(s, l.next);
		return;
	}

// RemoveLast
	static String removeLast(Node l) {
		String w;
		if (l == null) return null;
		else if (l.next == null) {
			return null;
		}
		else if (l.next.next == null){
			w = l.next.head;
			l.next = null;	
			return w;
		}
		else return removeLast(l.next);
	}

// Insert
	static Node insert(String s, Node l) {
		if (l == null) return new Node(s, null);
		else if (l.head.compareTo(s) > 0)
			return new Node(s, l);
		else
			return new Node(l.head, insert(s, l.next));
	}
// insertionSort
	static Node insertionSort(Node l) {
		if (l == null) return null;
		return insert(l.head, insertionSort(l.next));	
	}

	static Node merge(Node l1, Node l2) {
		if (l2 == null) return l1;
		else if (l1 == null) return l2;
		else if (l1.head.compareTo(l2.head) > 0)
			return new Node(l1.head, merge(l1.next, l2));
		else
			return new Node(l2.head, merge(l1, l2.next));
			
	}

// Main
	public static void main(String[] args) {
	Node foobar = new Node("foo", new Node("bar", new Node("baz", null)));
	Node foos = null;
//	System.out.println(printNodes(foobar));
//	System.out.println(printNodes(insertionSort(foobar)));
	
//	System.out.println(printNodes(foos));

	}
}

