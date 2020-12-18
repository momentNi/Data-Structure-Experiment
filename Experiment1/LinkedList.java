class ListNode<T extends Comparable<T>> {
	public T data;
	public ListNode<T> link;
	
	public ListNode() {
		data = null;
		link = null;
	}
	
	public ListNode(T item, ListNode<T> node) {
		data = item;
		link = node;
	}
}

public class LinkedList<T extends Comparable<T>> {
	ListNode<T> first;
	ListNode<T> last;
	
	public LinkedList() {
		first = new ListNode<T>();
		first.link = first;
		last = first;
	}
	
	public void append(T value) {
		last.link = new ListNode<T>(value, null);
		last = last.link;
	}
	
	public T find_first(T key) throws Exception {
		ListNode<T> p = new ListNode<T>();
		p = first.link;
		while(p != null) {
			if (p.data.equals(key))
				return p.data;
			p = p.link;
		}
		throw new Exception("ListItemNotFoundException");
	}		
	
	
	//	Method find_first should search the LinkedList for the first occurrence of an item
	//	that matches the value in the parameter key. It should return a reference to the first matching item.
	//	If the invoking LinkedList object is empty or no item is found that matches the parameter, 
	//	a ListItemNotFoundException should be thrown.

	
	public LinkedList<T> find_all(T key) {
		LinkedList<T> ll = new LinkedList<T>();
		ListNode<T> p = new ListNode<T>();
		p = first.link;
		while(p != null) {
			if (p.data.equals(key))
				ll.append(p.data);
			p = p.link;
		}
		return ll;
	}
	
	
	//	Method find_all should search the invoking LinkedList for all elements that match the value
	//	in the parameter key. It should return an LinkedList object containing copies of all the items
	//	that match the parameter key.
	//	If the invoking LinkedList object is empty or no item is found that matches the parameter, 
	//	this function should return an empty LinkedList.
	
	
	public void remove_first(T key) {
		ListNode<T> p = new ListNode<T>();
		p = first;
		while (p.link != null) {
			if (p.link.data.equals(key)) {
				p.link = p.link.link;
				break;
			}
			p = p.link;
		}
	}
	
	
	//	Method remove_first should remove the first element from the invoking LinkedList
	//	whose data item matches the parameter key. 
	//	If the invoking LinkedList object is empty or no item is found that matches the parameter, 
	//	this function should do nothing.
	
	public void remove_all(T key) {
		ListNode<T> p = new ListNode<T>();
		p = first;
		while (p.link != null) {
			if (p.link.data.equals(key)) {
				p.link = p.link.link;
			}
			else {
				p = p.link;
			}
		}
	}
		

//	public void remove_all(T key) {
//		ListNode<T> p = new ListNode<T>();
//		ListNode<T> q = new ListNode<T>();
//		p = first;
//		q = p.link;
//		while (q != null) {
//			if (q.data == key) {
//				q = q.link;
//				p.link = q;
//			}
//			else {
//				p = p.link;
//				q = q.link;
//			}
//		}
//	}
//	
	
	//	Method remove_all should remove all elements from the invoking LinkedList
	//	whose data items match the parameter key.
	//	If the invoking LinkedList object is empty or no item is found that matches the parameter, 
	//	this function should do nothing. 
	
	public void printAll() {
    	ListNode<T> p = new ListNode<T>();
		p = first.link;
		while( p != null) {
			System.out.println(p.data);
			p = p.link;
		}
    }
	
	public static void main(String[] args) throws Exception {
		LinkedList<Integer> list = new LinkedList<Integer> ();
		list.append(2);
		list.append(2);
		list.append(3);
		list.append(2);
		list.append(2);
		list.append(2);
		list.append(2);
		list.append(2);
		list.append(2);
		list.append(3);
		for (int i = 0; i < 5; i++)
			list.append((int)(Math.random()*10));
		list.printAll();
		System.out.println("=========================");
		System.out.println(list.find_first(3));
		System.out.println("=========================");
		System.out.println(list.find_all(3));
		System.out.println("=========================");
		list.remove_first(2);
		System.out.println("=========================");
		list.remove_all(2);
		System.out.println("=========================");
		list.printAll();

	}

}