package librarymanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Validator {

	private static final Pattern ID_PATTERN = Pattern.compile("^\\d{1,6}$");
	private static final Pattern AUTHOR_TITLE_PATTERN = Pattern.compile("^[a-zA-Z ]+$");
	private static final Pattern PUBLISH_YEAR_PATTERN = Pattern.compile("^\\d{4}$");

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public String validateId() throws IOException {
		String bookid;

		while (true) {
			System.out.println("Enter Book ID: ");
			bookid = reader.readLine();

			if (!ID_PATTERN.matcher(bookid).matches()) {
				System.err.println("SORRY ! PLEASE ENTER VALID BOOK ID ");
			} else {
				break;
			}
		}
		return bookid;
	}

	public String validateAuthorTitle(String input) throws IOException {

		String title;
		while (true) {
			if (input == "Title") {
				System.out.println("Enter Title:");
			} else {
				System.out.println("Enter Author:");
			}
			title = reader.readLine();
			if (!AUTHOR_TITLE_PATTERN.matcher(title).matches()) {
				System.err.println("SORRY ! PLEASE ENTER VALID TITLE NAME " + input);
			} else {
				break;
			}

		}
		return title;
	}

	public String validatePublishYear() throws IOException {

		String year;
		while (true) {
			System.out.println("Enter Publish Year of Book: ");
			year = reader.readLine();

			if (!PUBLISH_YEAR_PATTERN.matcher(year).matches()) {
				System.err.println("SORRY ! PLEASE ENTER VALID PUBLISH YEAR ");

			} else {
				break;
			}
		}
		return year;
	}

}
