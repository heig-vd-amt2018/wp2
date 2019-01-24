package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.ApplicationsApi;
import ch.heigvd.amt.wp2.api.model.Application;
import ch.heigvd.amt.wp2.model.entities.ApplicationEntity;
import ch.heigvd.amt.wp2.repositories.ApplicationRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ApplicationsApiController implements ApplicationsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Void> createApplication(@ApiParam(value = "", required = true) @Valid @RequestBody Application application) {
        ApplicationEntity newApplicationEntity = toApplicationEntity(application);

        applicationRepository.save(newApplicationEntity);

        return ResponseEntity.noContent().build();
    }

    private ApplicationEntity toApplicationEntity(Application application) {
        ApplicationEntity entity = new ApplicationEntity();

        entity.setApiKey(application.getApiKey());

        return entity;
    }
}
