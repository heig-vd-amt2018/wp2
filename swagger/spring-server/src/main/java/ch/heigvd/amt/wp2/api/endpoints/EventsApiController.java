package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.EventsApi;
import ch.heigvd.amt.wp2.api.model.Event;
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

            for (RuleEntity rule : application.getRules()) {
                if (rule.getEventType().equals(eventType)) {
                    for (RuleBadgeEntity ruleBadge : rule.getBadges()) {
                        badgeRewardRepository.save(new BadgeRewardEntity(player, ruleBadge.getBadge()));
                    }

                    for (RulePointScaleAmountEntity rulePointScaleAmount : rule.getPointScaleAmounts()) {
                        PointScaleAmountEntity pointScaleAmountEntity = rulePointScaleAmount.getPointScaleAmount();

                        pointRewardRepository.save(
                                new PointRewardEntity(
                                    player,
                                    pointScaleAmountEntity.getPointScale(),
                                    pointScaleAmountEntity.getAmount()
                            )
                        );
                    }
                }
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }
}
