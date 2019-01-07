package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.model.Badge;
import ch.heigvd.amt.wp2.api.model.Location;
import ch.heigvd.amt.wp2.model.entities.BadgeEntity;
import ch.heigvd.amt.wp2.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public ResponseEntity<Location> createBadge( @ApiParam(value = "", required = true) @Valid @RequestBody Badge badge) {

        BadgeEntity newBadgeEntity = toBadgeEntity(badge);
        badgeRepository.save(newBadgeEntity);
        Long id = newBadgeEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBadgeEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Badge> getBadge(Long id) {

        Badge badge;
        BadgeEntity badgeEntity = badgeRepository.findOne((long)id);

        badge = toBadge(badgeEntity);

        return ResponseEntity.ok(badge);
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges() {

        List<Badge> badges = new ArrayList<>();
        for (BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badges.add(toBadge(badgeEntity));
        }

        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<Location> updateBadge(Long id, Badge badge) {
        return null; // TODO
    }


    private BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setDescription(badge.getDescription());
        entity.setId(badge.getId());
        entity.setImage(badge.getImage());
        entity.setName(badge.getName());
        return entity;
    }

    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setDescription(entity.getDescription());
        badge.setId(entity.getId());
        badge.setImage(entity.getImage());
        badge.setName(entity.getName());
        return badge;
    }
}
