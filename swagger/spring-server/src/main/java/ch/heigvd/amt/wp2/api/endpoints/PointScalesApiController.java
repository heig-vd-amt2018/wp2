package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.model.Location;
import ch.heigvd.amt.wp2.api.model.PointScale;
import ch.heigvd.amt.wp2.model.entities.PointScaleEntity;
import ch.heigvd.amt.wp2.repositories.PointScaleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class PointScalesApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<Location> createPointScale(@ApiParam(value = "", required = true) @Valid @RequestBody PointScale pointScale) {

        PointScaleEntity newPointScaleEntity = toPointScaleEntity(pointScale);
        pointScaleRepository.save(newPointScaleEntity);
        Long id = newPointScaleEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPointScaleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<PointScale> getPointScale(Long id) {

        PointScale pointScale;
        PointScaleEntity pointScaleEntity = pointScaleRepository.findOne((long)id);

        pointScale = toPointScale(pointScaleEntity);

        return ResponseEntity.ok(pointScale);
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales() {

        List<PointScale> pointScales = new ArrayList<>();
        for (PointScaleEntity pointScaleEntity : pointScaleRepository.findAll()) {
            pointScales.add(toPointScale(pointScaleEntity));
        }

        return ResponseEntity.ok(pointScales);
    }

    @Override
    public ResponseEntity<Location> updatePointScales(Long id, PointScale pointScale) {
        return null; //TODO
    }


    private PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setDescription(pointScale.getDescription());
        entity.setId(pointScale.getId());
        entity.setName(pointScale.getName());
        return entity;
    }

    private PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();
        pointScale.setDescription(entity.getDescription());
        pointScale.setId(entity.getId());
        pointScale.setName(entity.getName());
        return pointScale;
    }

}
