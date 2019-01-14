package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "application")
public class ApplicationEntity extends AbstractDomainModelEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "api_key", nullable = false)
    private Long apiKey;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BadgeEntity> badges;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PointScaleEntity> pointScales;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PlayerEntity> players;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RuleEntity> rules;

    public ApplicationEntity() {
        //here fo JPA
    }

    public ApplicationEntity(String name, Long apiKey, List<BadgeEntity> badges, List<PointScaleEntity> pointScales, List<PlayerEntity> players, List<RuleEntity> rules) {
        this.name = name;
        this.apiKey = apiKey;
        this.badges = badges;
        this.pointScales = pointScales;
        this.players = players;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getApiKey() {
        return apiKey;
    }

    public void setApiKey(Long apiKey) {
        this.apiKey = apiKey;
    }

    public List<BadgeEntity> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeEntity> badges) {
        this.badges = badges;
    }

    public boolean addBadge(BadgeEntity badge) {
        return badges.add(badge);
    }

    public List<PointScaleEntity> getPointScales() {
        return pointScales;
    }

    public void setPointScales(List<PointScaleEntity> pointScales) {
        this.pointScales = pointScales;
    }

    public boolean addPointScale(PointScaleEntity pointScale) {
        return pointScales.add(pointScale);
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    public boolean addPlayer(PlayerEntity player) {
        return players.add(player);
    }

    public List<RuleEntity> getRules() {
        return rules;
    }

    public void setRules(List<RuleEntity> rules) {
        this.rules = rules;
    }

    public boolean addRule(RuleEntity rule) {
        return rules.add(rule);
    }
}
