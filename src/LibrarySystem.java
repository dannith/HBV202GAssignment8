import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class LibrarySystem {
    private List<Book> books;
    private List<User> users;
    private List<Lending> lendings;


    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.lendings = new ArrayList<>();
    }

    public void addBookWithTitleAndAuthorlist(String title, List<Author> authors) throws EmptyAuthorListException {
        if(authors.size() == 0) throw new EmptyAuthorListException("Book needs at least 1 author");
        
    	Book book = new Book(title, authors);
    	books.add(book);

    }

    public void addStudentUser(String name, boolean feePaid) {
        if (feePaid) {
            User studentUser = new Student(name,feePaid);
            users.add(studentUser);
        }
    }

    public void addFacultyMemberUser(String name, String department) {
        FacultyMember facultyMember = new FacultyMember(name, department);
        users.add(facultyMember);
    }

    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("Book does not exist");
    }

    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("User does not exist");
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if(!books.contains(book)) throw new UserOrBookDoesNotExistException("Book does not exist");
        if(!users.contains(user)) throw new UserOrBookDoesNotExistException("User does not exist");

        Lending lending = new Lending(book, user);
        lendings.add(lending);
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if(!books.contains(book)) throw new UserOrBookDoesNotExistException("Book does not exist");
        if(!users.contains(facultyMember)) throw new UserOrBookDoesNotExistException("Faculty member does not exist");

        Lending lending = null;
        for (Lending lend : lendings) {
            if(lend.getBook().equals(book) && lend.getUser().equals(facultyMember)) {
                lending = lend;
            }
        }

        if (lending == null) throw new UserOrBookDoesNotExistException("Cannot extend lending that does not exist");

        lending.setDueDate(newDueDate);
    }

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if(!books.contains(book)) throw new UserOrBookDoesNotExistException("Book does not exist");
        if(!users.contains(user)) throw new UserOrBookDoesNotExistException("User does not exist");

        Lending lending = null;
        for (Lending lend : lendings) {
            if(lend.getBook().equals(book) && lend.getUser().equals(user)) {
                lending = lend;
            }
        }

        if (lending == null) throw new UserOrBookDoesNotExistException("Cannot return book that is not borrowed");

        lendings.remove(lending);
    }
}