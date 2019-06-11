package nl.paulderaaij.reservation.infrastructure.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leadpony.justify.api.JsonSchema;
import org.leadpony.justify.api.JsonValidationService;
import org.leadpony.justify.api.Problem;
import org.leadpony.justify.api.ProblemHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.json.stream.JsonParser;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsControllerTest {
    private static final String SCHEMA_AVAILABLE_EVENTS_ENDPOINT = "/paths/~1events~1available/get/responses/200/content/application~1json/schema";
    private static final String SCHEMA_EVENT_RESERVATIONS_ENDPOINT = "/paths/~1events~1{id}~1reservations/get/responses/200/content/application~1json/schema";
    private static final String API_SPECIFICATION = "src/main/resources/api-specification.json";

    private static final JsonValidationService service = JsonValidationService.newInstance();
    private List<Problem> problems;
    private ProblemHandler handler;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        problems = new ArrayList<>();
        handler = problems::addAll;
    }

    @Test
    public void testAvailableEventsEndPointConformsToSchema() throws Exception {
        JsonSchema schema = service.readSchema(Paths.get(API_SPECIFICATION));
        MockHttpServletResponse response = mockMvc.perform(
                get("/events/available")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
       
        StringReader responseReader = new StringReader(response.getContentAsString());
        parseResponseAccordingToSchema(schema, responseReader, SCHEMA_AVAILABLE_EVENTS_ENDPOINT);

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertTrue("The response of the controller doesn't match the contract. Errors: " + problems.toString(), problems.isEmpty());
    }

    @Test
    public void testReservationsEndPointConformsToSpecification() throws Exception {
        JsonSchema schema = service.readSchema(Paths.get(API_SPECIFICATION));
        MockHttpServletResponse response = mockMvc.perform(
                get("/events/f67270e4-00e5-4a37-b5d4-a2ee642e7e68/reservations")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        StringReader responseReader = new StringReader(response.getContentAsString());
        parseResponseAccordingToSchema(schema, responseReader, SCHEMA_EVENT_RESERVATIONS_ENDPOINT);

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertTrue("The response of the controller doesn't match the contract. Errors: " + problems.toString(), problems.isEmpty());
    }

    private void parseResponseAccordingToSchema(JsonSchema schema, StringReader responseReader, String jsonPointerTowardsEndpoint) {
        Assert.assertNotNull("Can't get the schema for the end point: " + jsonPointerTowardsEndpoint, schema.getSubschemaAt(jsonPointerTowardsEndpoint));
        try (JsonParser parser = service.createParser(responseReader, schema.getSubschemaAt(jsonPointerTowardsEndpoint), handler)) {
            while (parser.hasNext()) {
                parser.next();
            }
        }
    }
}
