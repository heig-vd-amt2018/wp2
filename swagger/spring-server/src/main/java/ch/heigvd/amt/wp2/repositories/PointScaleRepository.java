package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.PointScaleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointScaleRepository extends CrudRepository<PointScaleEntity, Long> {
    List<PointScaleEntity> findByUsername(String username);
}
