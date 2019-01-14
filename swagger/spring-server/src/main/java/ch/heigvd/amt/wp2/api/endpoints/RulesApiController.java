package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.RulesApi;
import ch.heigvd.amt.wp2.api.model.Badge;
import ch.heigvd.amt.wp2.api.model.Location;
import ch.heigvd.amt.wp2.api.model.PointScale;
import ch.heigvd.amt.wp2.api.model.Rule;
import ch.heigvd.amt.wp2.model.entities.BadgeEntity;
import ch.heigvd.amt.wp2.model.entities.PointScaleEntity;
import ch.heigvd.amt.wp2.model.entities.RuleEntity;
import ch.heigvd.amt.wp2.repositories.BadgeRepository;
import ch.heigvd.amt.wp2.repositories.PointScaleRepository;
import ch.heigvd.amt.wp2.repositories.RuleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class RulesApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepositoy;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<Location> createRule(@ApiParam(value = "", required = true) @Valid @RequestBody Rule rule) {
        RuleEntity newRuleEntity = toRuleEntity(rule);
        ruleRepositoy.save(newRuleEntity);
        Long id = newRuleEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newRuleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Rule> getRule(Long id) {
        Rule rule;
        RuleEntity ruleEntity = ruleRepositoy.findOne((long) id);

        rule = toRule(ruleEntity);

        return ResponseEntity.ok(rule);
    }

    @Override
    public ResponseEntity<List<Rule>> getRules() {
        List<Rule> rules = new ArrayList<>();
        for (RuleEntity ruleEntity : ruleRepositoy.findAll()) {
            rules.add(toRule(ruleEntity));
        }

        return ResponseEntity.ok(rules);
    }

    @Override
    public ResponseEntity<Location> updateRule(Long id, Rule rule) {
        return null;
    }

    private RuleEntity toRuleEntity(Rule rule) {
        RuleEntity entity = new RuleEntity();

        BadgeEntity badge = null; // badgeRepository.findOne(rule.getBadgeId());
        PointScaleEntity pointScale = null; // pointScaleRepository.findOne(rule.getPointScaleId());

        entity.setId(rule.getId());
        entity.setName(rule.getName());
        entity.setEventName(rule.getEventName());
        entity.setPointScale(pointScale);
        entity.setBadge(badge);
        entity.setThreshold(rule.getThreshold());

        return entity;
    }

    private Rule toRule(RuleEntity entity) {
        Rule rule = new Rule();
        rule.setName(entity.getName());
        // rule.setId(entity.getId());
        // rule.setImage(entity.getImage());
        // rule.setName(entity.getName());
        return rule;
    }
}
