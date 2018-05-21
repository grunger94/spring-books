package books.repository;

import books.entity.BookStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = BookStatus.PATH, path = BookStatus.PATH)
public interface BookStatusRepository extends PagingAndSortingRepository<BookStatus, Long> {

    List<BookStatus> findByDescription(@Param("description") String description);
}
