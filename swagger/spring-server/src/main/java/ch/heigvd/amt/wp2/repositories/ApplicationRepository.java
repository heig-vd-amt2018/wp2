package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.Badge;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findByNameAndApiKey(String name, String apiKey);
}
