package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.EventsApi;
import ch.heigvd.amt.wp2.api.model.Event;
import ch.heigvd.amt.wp2.api.model.PointReward;
import ch.heigvd.amt.wp2.model.entities.*;
import ch.heigvd.amt.wp2.repositories.ApplicationRepository;
import ch.heigvd.amt.wp2.repositories.BadgeRewardRepository;
import ch.heigvd.amt.wp2.repositories.PlayerRepository;
import ch.heigvd.amt.wp2.repositories.PointRewardRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class EventsApiController implements EventsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Autowired
    PointRewardRepository pointRewardRepository;

    @Override
    public ResponseEntity<Void> postEvent(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestBody Event event
    ) {
        ResponseEntity response = null;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            PlayerEntity player = playerRepository.getByApplicationAndUsername(application, event.getUsername());

            if (player == null) {
                player = playerRepository.save(new PlayerEntity(application, event.getUsername()));
            }

            String eventType = event.getEventType();
            Timestamp timestamp = new Timestamp(event.getTimestamp().getMillis());

            for (RuleEntity rule : application.getRules()) {
                if (rule.getEventType().equals(eventType)) {
                    // Check for badges
                    for (RuleBadgeEntity ruleBadge : rule.getBadges()) {
                        BadgeRewardEntity newBadge = new BadgeRewardEntity(
                                player,
                                timestamp,
                                ruleBadge.getBadge()
                        );

                        if (!player.getBadgeRewards().contains(newBadge)) {
                            boolean badgeAlreadyObtained = false;

                            for (BadgeRewardEntity badgeRewardEntity : player.getBadgeRewards()) {
                                if (badgeRewardEntity.getBadge().equals(ruleBadge.getBadge())) {
                                    badgeAlreadyObtained = true;
                                }
                            }

                            if (!badgeAlreadyObtained) {
                                badgeRewardRepository.save(newBadge);
                            }
                        }
                    }

                    // Check for point scales
                    for (RulePointScaleAmountEntity rulePointScaleAmount : rule.getPointScaleAmounts()) {
                        PointScaleAmountEntity pointScaleAmountEntity = rulePointScaleAmount.getPointScaleAmount();

                        PointRewardEntity newPoint = new PointRewardEntity(
                                player,
                                timestamp,
                                pointScaleAmountEntity.getPointScale(),
                                pointScaleAmountEntity.getAmount()
                        );

                        if (!player.getPointScaleReward().contains(newPoint)) {
                            pointRewardRepository.save(newPoint);
                        }
                    }
                }
            }

            response = ResponseEntity.noContent().build();
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }
}
