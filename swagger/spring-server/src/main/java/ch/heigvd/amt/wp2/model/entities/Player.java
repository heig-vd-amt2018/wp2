package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "player")
public class Player extends AbstractDomainModelEntity<Long> {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "badge_rewards", nullable = true)
    private List<BadgeReward> badgeRewards;

    @Column(name = "point_rewards", nullable = true)
    private List<PointReward> pointRewards;

    public Player() {
        //only here for JPA
    }

    public Player(String username, List<BadgeReward> badgeRewards, List<PointReward> pointRewards) {
        this.username = username;
        this.badgeRewards = badgeRewards;
        this.pointRewards = pointRewards;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BadgeReward> getBadgeRewards() {
        return badgeRewards;
    }

    public void setBadgeRewards(List<BadgeReward> badgeRewards) {
        this.badgeRewards = badgeRewards;
    }

    public boolean addBadgeReward(BadgeReward badgeReward){
        return badgeRewards.add(badgeReward);
    }

    public List<PointReward> getPointRewards() {
        return pointRewards;
    }

    public void setPointRewards(List<PointReward> pointRewards) {
        this.pointRewards = pointRewards;
    }

    public boolean addPointReward(PointReward pointReward){
        return pointRewards.add(pointReward);
    }
}

