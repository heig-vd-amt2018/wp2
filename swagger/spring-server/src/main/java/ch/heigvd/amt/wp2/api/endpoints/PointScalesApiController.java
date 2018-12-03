package ch.heigvd.amt.wp2.api.endpoints;

import ch.heigvd.amt.wp2.api.PointScalesApi;
import ch.heigvd.amt.wp2.api.model.InlineResponse201;
import ch.heigvd.amt.wp2.api.model.PointScale;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class PointScalesApiController implements PointScalesApi {
    @Override
    public ResponseEntity<InlineResponse201> createPointScale(PointScale pointScale) {
        return null;
    }

    @Override
    public ResponseEntity<PointScale> getPointScale(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales() {
        return null;
    }
}
