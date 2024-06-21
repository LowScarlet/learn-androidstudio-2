package id.my.ardy.uts_native.activity.book;

public class Model {
    // TODO Edit This Scarlet
    public String id;
    public String title;
    public String isbn;
    public String publicationYear;
    public String genre;
    public String authorId;
    public String publisherId;
    public String categoryId;

    public Model(String title, String isbn, String publicationYear, String genre, String authorId, String publisherId, String categoryId) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.categoryId = categoryId;
    }
}
