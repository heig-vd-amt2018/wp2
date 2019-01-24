package ch.heigvd.amt.wp2.repositories;

import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RuleRepository extends CrudRepository<RuleEntity, Long> {
    List<RuleEntity> getAllByApplication(ApplicationEntity application);

    RuleEntity getByApplicationAndName(ApplicationEntity application, String name);
}
