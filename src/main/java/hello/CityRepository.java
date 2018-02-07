package hello;


import java.util.List;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;

@RepositoryRestResource(collectionResourceRel = "city", path = "city")
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    List<City> findByName(@Param("name") String name);

    // find city by its name or country with "like"
    @Query("SELECT c FROM City c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) " )

    public List<City> findBySearchTerm(@Param("searchTerm") String searchTerm);


    @Query("SELECT c FROM  City c join c.person p  " +
            "  WHERE LOWER(p.lastName) LIKE LOWER(CONCAT('%',:lastName, '%')) " )
    public List<City> findBylastName(@Param("lastName") String lastName);
}

