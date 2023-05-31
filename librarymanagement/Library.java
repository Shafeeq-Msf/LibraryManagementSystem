package librarymanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Library {

	Map<String, Book> bookMap = new HashMap<>();

	Validator validator = new Validator();

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public Library() {
		bookMap = new HashMap<>();
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public void start() {
		int choice;

		do {
			displayMenu();
			choice = getUserChoice();

			switch (choice) {
			case 1:
				addBook();
				break;
			case 2:
				removeBook();
				break;
			case 3:
				updateBook();
				break;
			case 4:
				searchBook();
				break;
			case 5:
				displayBooks();
				break;
			case 6:
				showAllAvailableBooks();
				break;
			case 7:
				borrowBook();
				break;
			case 8:
				returnBook();
				break;

			case 9:
				System.out.println("Exiting the program...");
				break;
			default:
				System.err.println("Invalid choice! Please try again.");
				break;
			}

			System.out.println();
		} while (choice != 9);

		// Close the BufferedReader
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void displayMenu() {
		System.out.println("===== Library Management System =====");
		System.out.println("1. Add Book");
		System.out.println("2. Remove Book");
		System.out.println("3. Update Book Information");
		System.out.println("4. Search Book");
		System.out.println("5. Display All Books");
		System.out.println("6. Show All Available Books");
		System.out.println("7. Borrow Book");
		System.out.println("8. Return Book");
		System.out.println("9. Exit");
	}

	public int getUserChoice() {
		int choice = 0;
		try {
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(reader.readLine());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Invalid choice! Please enter a valid integer.");
			choice = getUserChoice(); // Recursively call the method to prompt for a valid choice again
		}
		return choice;
	}

	public void addBook() {
		System.out.println("===== Add Book =====");
		try {
			int count = 0;
			String choice = null;

			do {
				String isbn = validator.validateId();
				String title = validator.validateAuthorTitle("Author");
				String author = validator.validateAuthorTitle("Title");
				String publicationYear = validator.validatePublishYear();
				Book book = new Book(isbn, title, author, publicationYear, "Available");
				bookMap.put(isbn, book);
				System.out.println("Book Added Successfully!");

				count++;

				boolean validChoice = false;
				while (!validChoice) {
					System.out.print("Do you want to add another book? (Y/N): ");
					choice = reader.readLine();
					if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) {
						validChoice = true;
					} else {
						System.err.println("SORRY ! PLEASE ENTER VALID ONE EITHER 'Y' or 'N'.");
					}
				}

			} while (choice.equalsIgnoreCase("Y"));

			System.out.println("Total books added: " + count);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeBook() {
		System.out.println("===== Remove Book =====");
		try {
			System.out.print("Enter ISBN: ");
			String isbn = reader.readLine();

			if (bookMap.containsKey(isbn)) {
				bookMap.remove(isbn);
				System.out.println("Book removed successfully!");
			} else {
				System.err.println("SORRY ! BOOK NOT FOUND!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBook() {
		System.out.println("===== Update Book Information =====");
		int count = 0;
		try {
			System.out.print("Enter ISBN: ");
			String isbn = reader.readLine();

			if (bookMap.containsKey(isbn)) {
				System.out.print("Enter new Title: ");
				String title = reader.readLine();
				System.out.print("Enter new Author: ");
				String author = reader.readLine();
				System.out.print("Enter new Publication Date: ");
				String publicationYear = reader.readLine();

				Book book = bookMap.get(isbn);
				book.setTitle(title);
				book.setAuthor(author);
				book.setPublicationYear(publicationYear);

				System.out.println("Book information updated successfully!");
				count++;
				System.out.println("Currently Updated books: " + count);
			} else {
				System.err.println("SORRY ! BOOK NOT FOUND!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchBook() {
		System.out.println("===== Search Book =====");
		try {
			System.out.print("Enter ISBN: ");
			String isbn = reader.readLine();

			if (bookMap.containsKey(isbn)) {
				Book book = bookMap.get(isbn);

				System.out.println(
						"\n----------------------------------------------------------------------------------------------");
				System.out.format("%-10s%-20s%-20s%-20s%-20s", "ID", "TITLE", "AUTHOR", "PUBLISH YEAR", "STATUS");
				System.out.println(
						"\n----------------------------------------------------------------------------------------------");
				System.out.format("%-10s%-20s%-20s%-20s%-20s", book.getIsbn(), book.getTitle(), book.getAuthor(),
						book.getPublicationYear(), book.getStatus());
				System.out.println("\n");
				System.out.println("Book found!");
			} else {
				System.err.println("SOORY ! BOOK NOT FOUND!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayBooks() {
		System.out.println("===== Display All Books =====");
		int count = 0;
		boolean flag = false;
		System.out.println(
				"\n----------------------------------------------------------------------------------------------");
		System.out.format("%-10s%-20s%-20s%-20s%-20s", "ID", "TITLE", "AUTHOR", "PUBLISH YEAR", "STATUS");
		System.out.println(
				"\n----------------------------------------------------------------------------------------------");
		for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
			Book book = entry.getValue();
			System.out.format("%-10s%-20s%-20s%-20s%-20s", book.getIsbn(), book.getTitle(), book.getAuthor(),
					book.getPublicationYear(), book.getStatus());
			System.out.println("\n");
			count++;
			flag = true;
		}
		System.out.println();
		System.out.println("Total books: " + count);

		System.out.println(
				"\n----------------------------------------------------------------------------------------------");
		if (flag == false)
			System.err.println("There are no Books in Library");
	}

	public void showAllAvailableBooks() {
		System.out.println("===== Available Books =====");
		boolean flag = false;
		int count = 0;
		System.out.println(
				"\n----------------------------------------------------------------------------------------------");
		System.out.format("%-20s%-20s%-20s%-20s%-20s", "ID", "TITLE", "AUTHOR", "PUBLISH YEAR", "STATUS");
		System.out.println(
				"\n----------------------------------------------------------------------------------------------");
		if (bookMap.size() > 0) {
			for (Book book : bookMap.values()) {
				if (book.getStatus() == "Available") {
					System.out.format("%-20s%-20s%-20s%-20s%-20s", book.getIsbn(), book.getTitle(), book.getAuthor(),
							book.getPublicationYear(), book.getStatus());
					System.out.println();
					count++;
					flag = true;
					System.out.println();

				}

			}
			System.out.println("Total Available books: " + count);
			System.out.println();
		} else {
			System.err.println("No Books Available in the library");
		}
		System.out.println(
				"\n----------------------------------------------------------------------------------------------");
		if (flag == false)
			System.err.println("There are no books with status Available");
	}

	public void borrowBook() {
		System.out.println("===== Borrow Book =====");
		boolean flag = false;
		try {
			String isbn = validator.validateId();

			for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
				String key = entry.getKey();
				Book book = entry.getValue();

				if (key.equals(isbn) && book.getStatus().equals("Available")) {
					flag = true;
					System.out.println("Book borrowed successfully!");
					book.setStatus("Not Available");

				}
			}

			if (flag == false) {
				System.err.println("This book is not available to borrow");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void returnBook() {
		System.out.println("===== Return Book =====");
		boolean flag = false;

		try {
			String isbn = validator.validateId();

			for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
				String key = entry.getKey();
				Book book = entry.getValue();
				if (key.equals(isbn) && book.getStatus().equals("Not Available")) {
					flag = true;
					book.setStatus("Available");
					System.out.println("Returned Book Details: " + book);
					System.out.println("Book returned successfully!");
				}
			}

			if (flag == false) {
				System.err.println("We cannot return this book");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
