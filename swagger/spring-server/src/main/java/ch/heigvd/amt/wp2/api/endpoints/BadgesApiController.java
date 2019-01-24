package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.model.Badge;
import ch.heigvd.amt.wp2.api.model.BadgePatch;
import ch.heigvd.amt.wp2.api.model.BadgePost;
import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.BadgeEntity;
import ch.heigvd.amt.wp2.repositories.ApplicationRepository;
import ch.heigvd.amt.wp2.repositories.BadgeRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    BadgeRepository badgeRepository;

    private BadgeEntity toBadgeEntity(ApplicationEntity application, BadgePost badgePost) {
        BadgeEntity entity = new BadgeEntity();

        entity.setApplication(application);
        entity.setName(badgePost.getName());
        entity.setDescription(badgePost.getDescription());
        entity.setImage(badgePost.getImage());

        return entity;
    }

    private void updateBadgeEntity(BadgeEntity entity, BadgePatch badgePatch) {
        entity.setDescription(badgePatch.getDescription());
        entity.setImage(badgePatch.getImage());
    }


    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();

        badge.setName(entity.getName());
        badge.setDescription(entity.getDescription());
        badge.setImage(entity.getImage());

        return badge;
    }

    @Override
    public ResponseEntity<String> createBadge(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestBody BadgePost badgePost
    ) {
        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            BadgeEntity newBadgeEntity = toBadgeEntity(application, badgePost);

            badgeRepository.save(newBadgeEntity);

            String name = newBadgeEntity.getName();

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{name}")
                    .buildAndExpand(name).toUri();

            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<Badge> getBadge(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestParam String badgeName
    ) {
        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            BadgeEntity badgeEntity = badgeRepository.getByApplicationAndName(application, badgeName);

            if (badgeEntity != null) {
                Badge badge = toBadge(badgeEntity);

                return ResponseEntity.ok(badge);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges(@ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey) {
        List<Badge> badges = new ArrayList<>();

        for(BadgeEntity badgeEntity : badgeRepository.findAll()) {
            badges.add(toBadge(badgeEntity));
        }

        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<String> updateBadge(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestParam String badgeName,
            @ApiParam(value = "", required = true) @Valid @RequestBody BadgePatch badgePatch
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if(application != null){
            BadgeEntity badgeEntity = badgeRepository.getByApplicationAndName(application, badgeName);

            if (badgeEntity != null) {
                updateBadgeEntity(badgeEntity,badgePatch);

                badgeRepository.save(badgeEntity);

                response = ResponseEntity.noContent().build();
            } else {
                response = ResponseEntity.notFound().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }
}
