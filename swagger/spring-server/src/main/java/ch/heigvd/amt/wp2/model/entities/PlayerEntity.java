package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "player")
public class PlayerEntity extends AbstractDomainModelEntity<Long> {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "badgeRewards", nullable = true)
    private List<BadgeRewardEntity> badgeRewards;

    @Column(name = "pointRewards", nullable = true)
    private List<PointRewardEntity> pointRewards;

    public PlayerEntity() {
        //only here for JPA
    }

    public PlayerEntity(String username, List<BadgeRewardEntity> badgeRewards, List<PointRewardEntity> pointRewards) {
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

    public List<BadgeRewardEntity> getBadgeRewards() {
        return badgeRewards;
    }

    public void setBadgeRewards(List<BadgeRewardEntity> badgeRewards) {
        this.badgeRewards = badgeRewards;
    }

    public boolean addBadgeReward(BadgeRewardEntity badgeReward){
        return badgeRewards.add(badgeReward);
    }

    public List<PointRewardEntity> getPointRewards() {
        return pointRewards;
    }

    public void setPointRewards(List<PointRewardEntity> pointRewards) {
        this.pointRewards = pointRewards;
    }

    public boolean addPointReward(PointRewardEntity pointReward){
        return pointRewards.add(pointReward);
    }
}

