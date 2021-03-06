package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
    PlayerEntity getByApplicationAndUsername(ApplicationEntity application, String username);
}
