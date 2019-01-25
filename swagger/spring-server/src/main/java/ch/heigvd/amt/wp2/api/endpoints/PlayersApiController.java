package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.PlayersApi;
import ch.heigvd.amt.wp2.api.model.Player;
import ch.heigvd.amt.wp2.api.model.PlayerBadgesReward;
import ch.heigvd.amt.wp2.api.model.PointScaleAmount;
import ch.heigvd.amt.wp2.api.model.PointScalesRewards;
import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.BadgeRewardEntity;
import ch.heigvd.amt.wp2.model.entities.PlayerEntity;
import ch.heigvd.amt.wp2.model.entities.PointRewardEntity;
import ch.heigvd.amt.wp2.repositories.ApplicationRepository;
import ch.heigvd.amt.wp2.repositories.BadgeRewardRepository;
import ch.heigvd.amt.wp2.repositories.PlayerRepository;
import ch.heigvd.amt.wp2.repositories.PointRewardRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class PlayersApiController implements PlayersApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PointRewardRepository pointRewardRepository;

    private Player toPlayer(PlayerEntity playerEntity) {
        Player player = new Player();

        List<PlayerBadgesReward> badges = new ArrayList<>();
        List<PointScalesRewards> points = new ArrayList<>();

        for (BadgeRewardEntity badge : playerEntity.getBadgeRewards()) {
             PlayerBadgesReward pbr = new PlayerBadgesReward();
             pbr.setName(badge.getBadge().getName());
             pbr.setTimestamp(badge.getCreatedDate().toString());

             badges.add(pbr);
        }

        for (PointRewardEntity point : playerEntity.getPointScaleReward()) {
            PointScalesRewards psr = new PointScalesRewards();

            psr.setName(point.getPointScale().getName());
            psr.setAmout((int) point.getAmount());
            psr.setTimestamp(point.getCreatedDate().toString());
        }

        player.setBadgesRewards(badges);
        player.setPointScalesrewards(points);

        return player;
    }

    @Override
    public ResponseEntity<Player> getPlayer(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @PathVariable("username") String username
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            PlayerEntity playerEntity = playerRepository.getByApplicationAndUsername(application, username);

            if (playerEntity != null) {
                Player player = toPlayer(playerEntity);

                response = ResponseEntity.ok(player);
            } else {
                response = ResponseEntity.notFound().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }
}
