package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
    ApplicationEntity findByApiKey(String apiKey);
}
