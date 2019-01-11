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
    private List<PointRewardEntity> pointRewards;

    public PlayerEntity() {
        //only here for JPA
    }

    public PlayerEntity(ApplicationEntity application, String username, List<BadgeRewardEntity> badgeRewards, List<PointRewardEntity> pointRewards) {
        this.application = application;
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

    public boolean addBadgeReward(BadgeRewardEntity badgeReward) {
        return badgeRewards.add(badgeReward);
    }

    public List<PointRewardEntity> getPointRewards() {
        return pointRewards;
    }

    public void setPointRewards(List<PointRewardEntity> pointRewards) {
        this.pointRewards = pointRewards;
    }

    public boolean addPointReward(PointRewardEntity pointReward) {
        return pointRewards.add(pointReward);
    }
}

