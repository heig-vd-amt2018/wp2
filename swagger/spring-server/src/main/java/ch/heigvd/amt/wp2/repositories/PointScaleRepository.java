package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.PointScaleEntity;
import org.springframework.data.repository.CrudRepository;

public interface PointScaleRepository extends CrudRepository<PointScaleEntity, Long> {
    PointScaleEntity getByApplicationAndName(ApplicationEntity application, String name);
}
