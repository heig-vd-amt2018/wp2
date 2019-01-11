package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointScaleRepository extends CrudRepository<Player, Long> {
    List<Player> findByUsername(String username);
}
