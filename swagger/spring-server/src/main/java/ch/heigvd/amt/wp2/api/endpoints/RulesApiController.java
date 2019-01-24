package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.RulesApi;
import ch.heigvd.amt.wp2.api.exceptions.NotFoundException;
import ch.heigvd.amt.wp2.api.model.Rule;
import ch.heigvd.amt.wp2.api.model.RulePatch;
import ch.heigvd.amt.wp2.api.model.RulePost;
import ch.heigvd.amt.wp2.model.entities.*;
import ch.heigvd.amt.wp2.repositories.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class RulesApiController implements RulesApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    RuleBadgeRepository ruleBadgeRepository;

    private List<String> toBadgeNames(List<RuleBadgeEntity> badgeEntities) {
        List<String> badges = new ArrayList<>();

        for (RuleBadgeEntity ruleBadge : badgeEntities) {
            badges.add(ruleBadge.getBadge().getName());
        }

        return badges;
    }

    private List<RuleBadgeEntity> toRuleBadges(ApplicationEntity application, RuleEntity rule, List<String> badgeNames) throws NotFoundException {
        List<RuleBadgeEntity> badges = new ArrayList<>();

        for (String badgeName : badgeNames) {
            BadgeEntity badge = badgeRepository.getByApplicationAndName(application, badgeName);

            if (badge == null) {
                throw new NotFoundException(404, "Badge not found.");
            }

            badges.add(new RuleBadgeEntity(rule, badge));
        }

        return badges;
    }

    private List<String> toPointScaleNames(List<RulePointScaleEntity> pointScaleEntities) {
        List<String> pointScales = new ArrayList<>();

        for (RulePointScaleEntity pointScale : pointScaleEntities) {
            pointScales.add(pointScale.getPointScale().getName());
        }

        return pointScales;
    }

    private List<RulePointScaleEntity> toPointScales(ApplicationEntity application, RuleEntity rule, List<String> pointScaleNames) throws NotFoundException {
        List<RulePointScaleEntity> pointScales = new ArrayList<>();

        for (String badgeName : pointScaleNames) {
            PointScaleEntity pointScale = pointScaleRepository.getByApplicationAndName(application, badgeName);

            if (pointScale == null) {
                throw new NotFoundException(404, "Point scale not found.");
            }

            pointScales.add(new RulePointScaleEntity(rule, pointScale));
        }

        return pointScales;
    }

    private void updateRuleEntity(ApplicationEntity application, RuleEntity rule, RulePatch rulePatch) throws NotFoundException {
        List<RuleBadgeEntity> badges = toRuleBadges(application, rule, rulePatch.getBadges());
        List<RulePointScaleEntity> pointScales = toPointScales(application, rule, rulePatch.getPointScales());

        rule.setEventType(rulePatch.getEventType());
        rule.setBadges(badges);
        rule.setPointScales(pointScales);
    }

    private RuleEntity toRuleEntity(ApplicationEntity application, RulePost rulePost) throws NotFoundException {
        RuleEntity entity = new RuleEntity();

        List<RuleBadgeEntity> badges = toRuleBadges(application, entity, rulePost.getBadges());
        List<RulePointScaleEntity> pointScales = toPointScales(application, entity, rulePost.getPointScales());

        entity.setApplication(application);
        entity.setName(rulePost.getName());
        entity.setEventType(rulePost.getEventType());
        entity.setBadges(badges);
        entity.setPointScales(pointScales);

        return entity;
    }

    private Rule toRule(RuleEntity entity) {
        Rule rule = new Rule();

        rule.setName(entity.getName());
        rule.setEventType(entity.getEventType());
        rule.setBadges(toBadgeNames(entity.getBadges()));
        rule.setPointScales(toPointScaleNames(entity.getPointScales()));

        return rule;
    }

    @Override
    public ResponseEntity<String> createRule(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestBody RulePost rulePost
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            String applicationName = rulePost.getName();

            RuleEntity rule = ruleRepository.getByApplicationAndName(application, applicationName);

            if (rule == null) {
                RuleEntity newRuleEntity = null;
                try {
                    newRuleEntity = toRuleEntity(application, rulePost);

                    ruleRepository.save(newRuleEntity);

                    String name = newRuleEntity.getName();

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{name}")
                            .buildAndExpand(name).toUri();

                    response = ResponseEntity.created(location).build();
                } catch (NotFoundException e) {
                    response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
                }
            } else {
                response = ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Rule> getRule(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestParam String ruleName
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            RuleEntity ruleEntity = ruleRepository.getByApplicationAndName(application, ruleName);

            if (ruleEntity != null) {
                Rule rule = toRule(ruleEntity);

                response = ResponseEntity.ok(rule);
            } else {
                response = ResponseEntity.notFound().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<Rule>> getRules(@ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            List<Rule> pointScales = new ArrayList<>();

            for (RuleEntity ruleEntity : ruleRepository.getAllByApplication(application)) {
                pointScales.add(toRule(ruleEntity));
            }

            return ResponseEntity.ok(pointScales);
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<String> updateRule(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestParam String ruleName,
            @ApiParam(value = "", required = true) @Valid @RequestBody RulePatch rulePatch
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            RuleEntity ruleEntity = ruleRepository.getByApplicationAndName(application, ruleName);

            if (ruleEntity != null) {
                try {
                    updateRuleEntity(application, ruleEntity, rulePatch);

                    ruleRepository.save(ruleEntity);

                    response = ResponseEntity.noContent().build();
                } catch (NotFoundException e) {
                    response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
                }
            } else {
                response = ResponseEntity.notFound().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }
}
