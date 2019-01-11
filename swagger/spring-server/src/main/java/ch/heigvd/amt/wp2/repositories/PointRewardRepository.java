package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.PointRewardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRewardRepository extends CrudRepository<PointRewardEntity, Long> {

}
