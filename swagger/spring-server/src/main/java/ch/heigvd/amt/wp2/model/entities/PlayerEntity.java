package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
    private Set<BadgeRewardEntity> badgeRewards;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PointRewardEntity> pointScaleReward;

    public PlayerEntity() {
        //only here for JPA
    }

    public PlayerEntity(ApplicationEntity application, String username) {
        this.application = application;
        this.username = username;
        this.badgeRewards = new HashSet<>();
        this.pointScaleReward = new HashSet<>();
    }


    public PlayerEntity(ApplicationEntity application, String username, Set<BadgeRewardEntity> badgeRewards, Set<PointRewardEntity> pointScaleReward) {
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

    public Set<BadgeRewardEntity> getBadgeRewards() {
        return badgeRewards;
    }

    public void setBadgeRewards(Set<BadgeRewardEntity> badgeRewards) {
        this.badgeRewards = badgeRewards;
    }

    public boolean addBadgeReward(BadgeRewardEntity badgeReward) {
        return badgeRewards.add(badgeReward);
    }

    public Set<PointRewardEntity> getPointScaleReward() {
        return pointScaleReward;
    }

    public void setPointScaleReward(Set<PointRewardEntity> pointScaleReward) {
        this.pointScaleReward = pointScaleReward;
    }

    public boolean addPointReward(PointRewardEntity pointReward) {
        return pointScaleReward.add(pointReward);
    }
}

