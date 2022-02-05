package StepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinitions s = new StepDefinitions();
        if(StepDefinitions.place_id==null){
            s.add_place_payload("Subhidh","English","India","82151");
            s.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            s.verifyPlace_id();
            s.user_calls_with_post_http_request("GetPlaceAPI","GET");
        }

    }
}
