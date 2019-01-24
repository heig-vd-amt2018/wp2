package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.model.PointScale;
import ch.heigvd.amt.wp2.api.model.PointScalePatch;
import ch.heigvd.amt.wp2.api.model.PointScalePost;
import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.PointScaleEntity;
import ch.heigvd.amt.wp2.repositories.ApplicationRepository;
import ch.heigvd.amt.wp2.repositories.PointScaleRepository;
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
public class PointScalesApiController implements PointScalesApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    private void updatePointScaleEntity(PointScaleEntity pointScale, PointScalePatch pointScalePatch) {
        pointScale.setDescription(pointScalePatch.getDescription());
    }

    private PointScaleEntity toPointScaleEntity(ApplicationEntity application, PointScalePost pointScalePost) {
        PointScaleEntity entity = new PointScaleEntity();

        entity.setApplication(application);
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

    @Override
    public ResponseEntity<String> createPointScale(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestBody PointScalePost pointScalePost
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            String applicationName = pointScalePost.getName();

            PointScaleEntity pointScale = pointScaleRepository.getByApplicationAndName(application, applicationName);

            if (pointScale == null) {
                PointScaleEntity newPointScaleEntity = toPointScaleEntity(application, pointScalePost);

                pointScaleRepository.save(newPointScaleEntity);

                String name = newPointScaleEntity.getName();

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{name}")
                        .buildAndExpand(name).toUri();

                response = ResponseEntity.created(location).build();
            } else {
                response = ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<PointScale> getPointScale(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestParam String pointScaleName
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            PointScaleEntity pointScaleEntity = pointScaleRepository.getByApplicationAndName(application, pointScaleName);

            if (pointScaleEntity != null) {
                PointScale pointScale = toPointScale(pointScaleEntity);

                response = ResponseEntity.ok(pointScale);
            } else {
                response = ResponseEntity.notFound().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales(@ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            List<PointScale> pointScales = new ArrayList<>();

            for (PointScaleEntity pointScaleEntity : pointScaleRepository.getAllByApplication(application)) {
                pointScales.add(toPointScale(pointScaleEntity));
            }

            return ResponseEntity.ok(pointScales);
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<String> updatePointScale(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @RequestParam String pointScaleName,
            @ApiParam(value = "", required = true) @Valid @RequestBody PointScalePatch pointScalePatch
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            PointScaleEntity pointScaleEntity = pointScaleRepository.getByApplicationAndName(application, pointScaleName);

            if (pointScaleEntity != null) {
                updatePointScaleEntity(pointScaleEntity, pointScalePatch);

                pointScaleRepository.save(pointScaleEntity);

                response = ResponseEntity.noContent().build();
            } else {
                response = ResponseEntity.notFound().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }
}
