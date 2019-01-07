package ch.heigvd.amt.wp2.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "application")
public class Application extends AbstractDomainModelEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "api_key", nullable = false)
    private Long apiKey;

    @Column(name = "badges", nullable = true)
    private List<Badge> badges;

    @Column(name = "pointScales", nullable = true)
    private List<PointScale> pointScales;

    @Column(name = "players", nullable = true)
    private List<Player> players;

    @Column(name = "rules", nullable = true)
    private List<Rule> rules;

    public Application() {
        //here fo JPA
    }

    public Application(String name, Long apiKey, List<Badge> badges, List<PointScale> pointScales, List<Player> players, List<Rule> rules) {
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

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public boolean addBadge(Badge badge){
        return badges.add(badge);
    }

    public List<PointScale> getPointScales() {
        return pointScales;
    }

    public void setPointScales(List<PointScale> pointScales) {
        this.pointScales = pointScales;
    }

    public boolean addPointScale(PointScale pointScale){
        return pointScales.add(pointScale);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean addPlayer(Player player){
        return players.add(player);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public boolean addRule(Rule rule){
        return rules.add(rule);
    }
}
