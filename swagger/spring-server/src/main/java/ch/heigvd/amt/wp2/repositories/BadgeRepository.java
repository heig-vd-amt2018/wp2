package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRepository extends CrudRepository<BadgeEntity, Long> {
    List<BadgeEntity> getAllByApplication(ApplicationEntity application);

    BadgeEntity getByApplicationAndName(ApplicationEntity application, String name);
}
