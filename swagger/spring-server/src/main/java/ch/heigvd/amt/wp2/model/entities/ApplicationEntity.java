package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.Set;

@Entity
@Table(name = "application")
public class ApplicationEntity extends AbstractDomainModelEntity<Long> {
    @Column(
            name = "api_key",
            unique = true,
            nullable = false
    )
    private String apiKey;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BadgeEntity> badges;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PointScaleDescriptionEntity> pointScales;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PlayerEntity> players;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RuleEntity> rules;

    public ApplicationEntity() {
        //here fo JPA
    }

    public ApplicationEntity(String apiKey, Set<BadgeEntity> badges, Set<PointScaleDescriptionEntity> pointScales, Set<PlayerEntity> players, Set<RuleEntity> rules) {
        this.apiKey = apiKey;
        this.badges = badges;
        this.pointScales = pointScales;
        this.players = players;
        this.rules = rules;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Set<BadgeEntity> getBadges() {
        return badges;
    }

    public void setBadges(Set<BadgeEntity> badges) {
        this.badges = badges;
    }

    public boolean addBadge(BadgeEntity badge) {
        return badges.add(badge);
    }

    public Set<PointScaleDescriptionEntity> getPointScales() {
        return pointScales;
    }

    public void setPointScales(Set<PointScaleDescriptionEntity> pointScales) {
        this.pointScales = pointScales;
    }

    public boolean addPointScale(PointScaleDescriptionEntity pointScale) {
        return pointScales.add(pointScale);
    }

    public Set<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerEntity> players) {
        this.players = players;
    }

    public boolean addPlayer(PlayerEntity player) {
        return players.add(player);
    }

    public Set<RuleEntity> getRules() {
        return rules;
    }

    public void setRules(Set<RuleEntity> rules) {
        this.rules = rules;
    }

    public boolean addRule(RuleEntity rule) {
        return rules.add(rule);
    }
}
