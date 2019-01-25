package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.PointScaleDescriptionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointScaleDescriptionRepository extends CrudRepository<PointScaleDescriptionEntity, Long> {
    List<PointScaleDescriptionEntity> getAllByApplication(ApplicationEntity application);

    PointScaleDescriptionEntity getByApplicationAndName(ApplicationEntity application, String name);
}
