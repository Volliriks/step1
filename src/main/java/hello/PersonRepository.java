package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);

	@Query("SELECT p FROM Person p WHERE " +
			"LOWER(p.lastName) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " )
	public List<City> findBySearchTerm(@Param("searchTerm") String searchTerm);

	@Query("SELECT p FROM  Person p join p.city c  " +
			"  WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:cityName, '%')) " )
	public List<City> findByCityName(@Param("cityName") String cityName);
}
