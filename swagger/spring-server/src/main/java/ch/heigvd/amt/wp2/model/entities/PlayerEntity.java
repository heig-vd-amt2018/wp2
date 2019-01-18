package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "player")
public class PlayerEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BadgeRewardEntity> badgeRewards;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PointScaleRewardEntity> pointScaleReward;

    public PlayerEntity() {
        //only here for JPA
    }

    public PlayerEntity(ApplicationEntity application, String username, List<BadgeRewardEntity> badgeRewards, List<PointScaleRewardEntity> pointScaleReward) {
        this.application = application;
        this.username = username;
        this.badgeRewards = badgeRewards;
        this.pointScaleReward = pointScaleReward;
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

    public boolean addBadgeReward(BadgeRewardEntity badgeReward) {
        return badgeRewards.add(badgeReward);
    }

    public List<PointScaleRewardEntity> getPointScaleReward() {
        return pointScaleReward;
    }

    public void setPointScaleReward(List<PointScaleRewardEntity> pointScaleReward) {
        this.pointScaleReward = pointScaleReward;
    }

    public boolean addPointReward(PointScaleRewardEntity pointReward) {
        return pointScaleReward.add(pointReward);
    }
}

