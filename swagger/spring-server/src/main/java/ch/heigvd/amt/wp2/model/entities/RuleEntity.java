package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "rule")
public class RuleEntity extends AbstractDomainModelEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "point_rewards", nullable = false)
    private List<PointRewardEntity> pointRewards;

    @Column(name = "badges", nullable = false)
    private List<BadgeRewardEntity> badgeRewards;

    public RuleEntity() {
        //here fo JPA
    }

    public RuleEntity(ApplicationEntity application, String name, String eventType, List<PointRewardEntity> pointRewards, List<BadgeRewardEntity> badgeRewards) {
        this.application = application;
        this.name = name;
        this.eventType = eventType;
        this.pointRewards = pointRewards;
        this.badgeRewards = badgeRewards;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<PointRewardEntity> getPointRewards() {
        return pointRewards;
    }

    public void setPointRewards(List<PointRewardEntity> pointRewards) {
        this.pointRewards = pointRewards;
    }

    public List<BadgeRewardEntity> getBadgeRewards() {
        return badgeRewards;
    }

    public void setBadgeRewards(List<BadgeRewardEntity> badgeRewards) {
        this.badgeRewards = badgeRewards;
    }
}
