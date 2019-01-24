package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.model.PointScale;
import ch.heigvd.amt.wp2.api.model.PointScalePatch;
import ch.heigvd.amt.wp2.api.model.PointScalePost;
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
    public ResponseEntity<String> createPointScale(@ApiParam(value = "", required = true) @Valid @RequestBody PointScalePost pointScalePost) {
        PointScaleEntity newPointScaleEntity = toPointScaleEntity(pointScalePost);
        pointScaleRepository.save(newPointScaleEntity);
        String name = newPointScaleEntity.getName();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{name}")
                .buildAndExpand(name).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<PointScale> getPointScale(String pointScaleName) {
        return null;
    }

    @Override
    public ResponseEntity<String> updatePointScale(String pointScaleName, PointScalePatch pointScalePatch) {
        return null;
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales() {

        List<PointScale> pointScales = new ArrayList<>();
        for (PointScaleEntity pointScaleEntity : pointScaleRepository.findAll()) {
            pointScales.add(toPointScale(pointScaleEntity));
        }

        return ResponseEntity.ok(pointScales);
    }

    private PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();

        entity.setName(pointScale.getName());
        entity.setDescription(pointScale.getDescription());

        return entity;
    }

    private PointScaleEntity toPointScaleEntity(PointScalePost pointScalePost) {
        PointScaleEntity entity = new PointScaleEntity();

        entity.setName(pointScalePost.getName());
        entity.setDescription(pointScalePost.getDescription());

        return entity;
    }

    private PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();

        pointScale.setName(entity.getName());
        pointScale.setDescription(entity.getDescription());

        return pointScale;
    }

}
