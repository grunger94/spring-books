package books;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	List<Book> findByName(@Param("name") String name);

}
