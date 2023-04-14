public class Stack<T extends Comparable<T>>{
    int size;
    Node<T> start;
    int n;

    public Stack(){
        this.size = 5;
        start = new Node<>(null);
        start.setNext(null);
    }

    public Stack(int size){
        this.size = size;
        start = new Node<>(null);
        start.setNext(null);
        n = 0;
    }

    public T pop()throws StackException{
        if(size == 0){
            throw new StackException(0);
        }
        Node<T> temp = start.getNext();
        if(temp.getNext() == null){
            start.setNext(null);
            n--;
            return temp.getData();
        }
        start.setNext(temp.getNext());
        n--;
        return temp.getData();

    }
    public void push(T item)throws StackException{
        if(n+1 > size ){
            throw new StackException(size + 1);
        }
        if(size == 0){
            start.setNext(new Node<T>(item));
            n++;
        }
        Node<T> temp = start.getNext();
        Node<T> newNode = new Node<T>(item);
        start.setNext(newNode);
        newNode.setNext(temp);
        n++;

    }
}