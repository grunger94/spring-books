package books.repository;

import books.entity.BookStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

import static books.repository.BookStatusRepository.PATH;

@RepositoryRestResource(collectionResourceRel = PATH, path = PATH)
public interface BookStatusRepository extends PagingAndSortingRepository<BookStatus, Long> {

    String PATH = "bookstatus";

    List<BookStatus> findByDescription(@Param("description") String description);

}
