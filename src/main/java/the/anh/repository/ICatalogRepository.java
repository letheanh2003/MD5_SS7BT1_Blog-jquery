package the.anh.repository;

import the.anh.model.entity.Catalog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatalogRepository extends PagingAndSortingRepository<Catalog,Long> {
}
