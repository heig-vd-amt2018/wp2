package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(
            mappedBy = "rule",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RuleBadgeEntity> badges;

    @OneToMany(
            mappedBy = "rule",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RulePointScaleEntity> pointScales;

    public RuleEntity() {
        //here fo JPA
    }

    public RuleEntity(
            ApplicationEntity application,
            String name,
            String eventType,
            List<RulePointScaleEntity> pointScales,
            List<RuleBadgeEntity> badges
    ) {
        this.application = application;
        this.name = name;
        this.eventType = eventType;
        this.pointScales = pointScales;
        this.badges = badges;
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

    public List<RulePointScaleEntity> getPointScales() {
        return pointScales;
    }

    public void setPointScales(List<RulePointScaleEntity> pointScales) {
        this.pointScales = pointScales;
    }

    public List<RuleBadgeEntity> getBadges() {
        return badges;
    }

    public void setBadges(List<RuleBadgeEntity> badges) {
        this.badges = badges;
    }
}
