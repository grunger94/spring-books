package books.repository;

import books.entity.BookUnavailabilityCause;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

import static books.repository.BookUnavailabilityCauseRepository.PATH;

@RepositoryRestResource(collectionResourceRel = PATH, path = PATH)
public interface BookUnavailabilityCauseRepository extends PagingAndSortingRepository<BookUnavailabilityCause, Long> {

    String PATH = "bookUnavailabilityCauses";

    List<BookUnavailabilityCause> findByCause(@Param("cause") String cause);

}
