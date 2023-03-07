import java.util.List;

public class Book {
    private String title;

    private List<Author> authors;

    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        this.title = title;
        if(authors.size() == 0) throw new EmptyAuthorListException("Book needs at least 1 author");
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if(authors.size() == 0) throw new EmptyAuthorListException("Book needs at least 1 author");
        this.authors = authors;
    }

}
