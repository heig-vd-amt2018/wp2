package ch.heigvd.amt.wp2.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRewardRepository extends CrudRepository<Player, Long> {
    List<Player> findByUsername(String username);
}
