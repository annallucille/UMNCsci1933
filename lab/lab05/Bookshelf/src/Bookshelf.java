public class Bookshelf {
    protected Book[] books = new Book[20];
    private int size = 19;
    private int nextEmpty = 0;

    public Bookshelf(int a){
        books = new Book[a];
        size = a-1;
    }
    public Bookshelf(){}


    public boolean add(Book newBook){
        if(nextEmpty <= size){
            books[nextEmpty] = newBook;
            nextEmpty += 1;
            return true;
        }
        else return false;
    }

    public Bookshelf getBooksByAuthor(String author){
        Bookshelf authorBooks = new Bookshelf(this.size);
        int next = 0;
        for(int i = 0; i<= size; i++){
            if(this.books[i].getAuthor().equals(author)){
            authorBooks.books[next] = this.books[i];
            next+=1;
            }
        }
        return authorBooks;
    }


    public String toString(){
        String s = "\0";
        for (int i =0; i<= size; i++){
           s = s + books[i].toString()+"\n";
        }
        return s;
    }

    public void sort(char sortBy){
        Book placeHolder;
        for(int i = 1; i<= size; i++){
        for(int k = 0; k<=i-1;k++){
            if(books[i].compareTo(books[k], sortBy)<0){
                placeHolder = books[i]; books[i] = books[k]; books[k] = placeHolder; } } }
    }


}
