package books.repository;

import java.util.List;

import books.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static books.repository.BookRepository.PATH;

@RepositoryRestResource(collectionResourceRel = PATH, path = PATH)
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	String PATH = "books";

	List<Book> findByName(@Param("name") String name);

}
