package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.BadgesApi;
import ch.heigvd.amt.wp2.api.model.Badge;
import ch.heigvd.amt.wp2.api.model.InlineResponse201;
import ch.heigvd.amt.wp2.api.model.Location;
import ch.heigvd.amt.wp2.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class BadgesApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepositoryRepository;


    @Override
    public ResponseEntity<Location> createBadge(Badge badge) {
        return null;
    }

    @Override
    public ResponseEntity<Badge> getBadge(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges() {
        return null;
    }

    /*public ResponseEntity<Object> createFruit(@ApiParam(value = "", required = true) @Valid @RequestBody Fruit fruit) {
        BadgeEntity newFruitEntity = toFruitEntity(fruit);
        fruitRepository.save(newFruitEntity);
        Long id = newFruitEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newFruitEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<Fruit>> getFruits() {
        List<Fruit> fruits = new ArrayList<>();
        for (BadgeEntity fruitEntity : fruitRepository.findAll()) {
            fruits.add(toFruit(fruitEntity));
        }
        /*
        Fruit staticFruit = new Fruit();
        staticFruit.setColour("red");
        staticFruit.setKind("banana");
        staticFruit.setSize("medium");
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(staticFruit);

        return ResponseEntity.ok(fruits);
    }


    private BadgeEntity toFruitEntity(Fruit fruit) {
        BadgeEntity entity = new BadgeEntity();
        entity.setColour(fruit.getColour());
        entity.setKind(fruit.getKind());
        entity.setSize(fruit.getSize());
        return entity;
    }

    private Fruit toFruit(BadgeEntity entity) {
        Fruit fruit = new Fruit();
        fruit.setColour(entity.getColour());
        fruit.setKind(entity.getKind());
        fruit.setSize(entity.getSize());
        return fruit;
    }*/

}
