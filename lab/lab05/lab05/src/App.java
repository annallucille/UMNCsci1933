public class App {
    public static void main(String[] args) throws Exception {
        Bookshelf bs = new Bookshelf(5);
        bs.add(new Book("Eragon", "Christopher Paolini", 10.0));
        bs.add(new Book("The Fellowship of the Ring", "J.R.R. Tolkein", 10.0));
        bs.add(new Book("Twilight", "Stephenie Meyer", 0.0));
        bs.add(new Book("The Diary of a Wimpy Kid", "Jeff Kinney", 0.0));
        bs.add(new Book("Dracula", "Bram Stoker", 7.5));
        bs.sort('A');
        System.out.println(bs);

        
        bs = BookshelfReader.readBooksFromFile("bookinput.txt");
        BookshelfReader.writeShelfToFile(bs, "bookshelf.txt");
    }
}
