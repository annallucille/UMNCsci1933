public class SparseVector {

	private Node head;
	private int length;

	public SparseVector(int len){
		head = null;
		length = len;
	}

	// Prints out a sparse vector (including zeros)
	public String toString(){

		String result = "";
		Node currNode = head;
		int currIndex = 0;
		while( currNode != null ){
			int idx = currNode.getIndex();

			// Pad the space between nodes with zero
			while( currIndex < idx ){
				result += "0, ";
				currIndex++;
			}
			result += currNode;
			currNode = currNode.getNext();
			currIndex++;

			// Only add a comma if this isn't the last element
			if( currNode != null ){ result += ", "; }
		}
		return result;
	}

	// TODO: Implement me for milestone 2
	public void addElement(int index, double value){
		if(head == null){
			Node ptr = new Node(index, value);
			head = ptr;
		}

		else {
			Node ptr = head;
			while (ptr.getNext() != null) {
				ptr = ptr.getNext();
			}
			Node newNode = new Node(index, value);
			ptr.setNext(newNode);
		}
	}

	// TODO: Implement me for milestone 4
	public static double dot( SparseVector x, SparseVector y ){
		double product = 0.0;
		if (x.length != y.length){
			return product;
		}
		if(x.head != null && y.head != null) {
			Node xPtr = x.head;
			Node yPtr = y.head;
			while (xPtr != null) {
				if(xPtr.getIndex()==yPtr.getIndex()) {
					product += xPtr.getValue() * yPtr.getValue();
					xPtr = xPtr.getNext();
					yPtr = yPtr.getNext();
				}
				else if (xPtr.getIndex() > yPtr.getIndex()){
					yPtr = yPtr.getNext();
				}
				else{
					xPtr = xPtr.getNext();
				}

			}
			return product;
		}
		return product;
	}


	// TODO: Test out your code here!
	public static void main(String[] args) {
		SparseVector x = new SparseVector(100000000);
		x.addElement(0, 1.0);
		x.addElement(10000000, 3.0);
		x.addElement(10000001, -2.0);
		SparseVector y = new SparseVector(100000000);
		y.addElement(0, 2.0);
		y.addElement(10000001, -4.0);
		double result = dot(x, y);
		System.out.println(result);

	}
}


