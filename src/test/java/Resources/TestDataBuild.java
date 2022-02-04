package Resources;

import POJO_serialization.Address;
import POJO_serialization.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public Address addPlacePayLoad() {
        Address add = new Address();
        add.setAccuracy(50);
        add.setAddress("Bajla Chowk, Castiers Town");
        Location loc = new Location();
        loc.setLat(-45.356);
        loc.setLng(-56.312);
        add.setLocation(loc);
        add.setName("Frontline House");
        add.setPhone_number("456456465");
        List<String> types = new ArrayList<>();
        types.add("shoe");
        types.add("shop");
        add.setTypes(types);
        add.setWebsite("google.com");
        add.setLanguage("English");
        return add;
    }
}
