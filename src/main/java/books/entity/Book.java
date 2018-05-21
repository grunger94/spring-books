package books.entity;

import books.repository.BookStatusRepository;
import books.repository.BookUnavailabilityCauseRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Book {

	static final String ID_COLUMN = "book_id";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@OneToOne
	@JoinColumn(name = BookStatus.ID_COLUMN)
	@RestResource(path = BookStatusRepository.PATH, rel= BookStatusRepository.PATH)
	private BookStatus bookStatus;

	@OneToOne
	@JoinColumn(name = BookUnavailabilityCause.ID_COLUMN)
	@RestResource(path = BookUnavailabilityCauseRepository.PATH, rel= BookUnavailabilityCauseRepository.PATH)
	private BookUnavailabilityCause bookUnavailabilityCause;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	public BookUnavailabilityCause getBookUnavailabilityCause() {
		return bookUnavailabilityCause;
	}

	public void setBookUnavailabilityCause(BookUnavailabilityCause bookUnavailabilityCause) {
		this.bookUnavailabilityCause = bookUnavailabilityCause;
	}

}
