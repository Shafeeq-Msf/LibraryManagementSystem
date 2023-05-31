package librarymanagement;

public class Book {

	private String isbn;
	private String title;
	private String author;
	private String publicationYear;
	private String status;

	Book(String isbn, String title, String author, String publicationYear, String status) {

		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.status = status;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationDate) {
		this.publicationYear = publicationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return  "Book{" +
                "id='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear='" + publicationYear + '\'' +
                ", status='" + status + '\'' +
                '}';
		
	}
}
