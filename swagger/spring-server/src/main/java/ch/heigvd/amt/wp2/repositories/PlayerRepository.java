package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {

}
