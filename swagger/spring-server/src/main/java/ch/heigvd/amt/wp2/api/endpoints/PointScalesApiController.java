package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.model.PointScaleDescription;
import ch.heigvd.amt.wp2.api.model.PointScalePatch;
import ch.heigvd.amt.wp2.api.model.PointScalePost;
import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.model.entities.PointScaleDescriptionEntity;
import ch.heigvd.amt.wp2.repositories.ApplicationRepository;
import ch.heigvd.amt.wp2.repositories.PointScaleDescriptionRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    PointScaleDescriptionRepository pointScaleDescriptionRepository;

    private void updatePointScaleDescriptionEntity(PointScaleDescriptionEntity pointScale, PointScalePatch pointScalePatch) {
        pointScale.setDescription(pointScalePatch.getDescription());
    }

    private PointScaleDescriptionEntity toPointScaleDescriptionEntity(ApplicationEntity application, PointScalePost pointScalePost) {
        PointScaleDescriptionEntity entity = new PointScaleDescriptionEntity();

        entity.setApplication(application);
        entity.setName(pointScalePost.getName());
        entity.setDescription(pointScalePost.getDescription());

        return entity;
    }

    private PointScaleDescription toPointScaleDescription(PointScaleDescriptionEntity entity) {
        PointScaleDescription pointScaleDescription = new PointScaleDescription();

        pointScaleDescription.setName(entity.getName());
        pointScaleDescription.setDescription(entity.getDescription());

        return pointScaleDescription;
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

            PointScaleDescriptionEntity pointScaleDescription = pointScaleDescriptionRepository.getByApplicationAndName(application, applicationName);

            if (pointScaleDescription == null) {
                PointScaleDescriptionEntity newPointScaleDescriptionEntity = toPointScaleDescriptionEntity(application, pointScalePost);

                pointScaleDescriptionRepository.save(newPointScaleDescriptionEntity);

                String name = newPointScaleDescriptionEntity.getName();

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
    public ResponseEntity<PointScaleDescription> getPointScale(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @PathVariable("pointScaleName") String pointScaleName
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            PointScaleDescriptionEntity pointScaleDescriptionEntity = pointScaleDescriptionRepository.getByApplicationAndName(application, pointScaleName);

            if (pointScaleDescriptionEntity != null) {
                PointScaleDescription pointScaleDescription = toPointScaleDescription(pointScaleDescriptionEntity);

                response = ResponseEntity.ok(pointScaleDescription);
            } else {
                response = ResponseEntity.notFound().build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<List<PointScaleDescription>> getPointScales(@ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            List<PointScaleDescription> pointScales = new ArrayList<>();

            for (PointScaleDescriptionEntity pointScaleDescriptionEntity : pointScaleDescriptionRepository.getAllByApplication(application)) {
                pointScales.add(toPointScaleDescription(pointScaleDescriptionEntity));
            }

            response = ResponseEntity.ok(pointScales);
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<String> updatePointScale(
            @ApiParam(value = "", required = true) @Valid @RequestHeader String apiKey,
            @ApiParam(value = "", required = true) @Valid @PathVariable("pointScaleName") String pointScaleName,
            @ApiParam(value = "", required = true) @Valid @RequestBody PointScalePatch pointScalePatch
    ) {
        ResponseEntity response;

        ApplicationEntity application = applicationRepository.findByApiKey(apiKey);

        if (application != null) {
            PointScaleDescriptionEntity pointScaleDescriptionEntity = pointScaleDescriptionRepository.getByApplicationAndName(application, pointScaleName);

            if (pointScaleDescriptionEntity != null) {
                updatePointScaleDescriptionEntity(pointScaleDescriptionEntity, pointScalePatch);

                pointScaleDescriptionRepository.save(pointScaleDescriptionEntity);

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
