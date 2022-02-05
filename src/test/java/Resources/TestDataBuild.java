package Resources;

import POJO_serialization.Address;
import POJO_serialization.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public Address addPlacePayLoad(String name, String language, String address, String phone) {
        Address add = new Address();
        add.setAccuracy(50);
        add.setAddress(address);
        Location loc = new Location();
        loc.setLat(-45.356);
        loc.setLng(-56.312);
        add.setLocation(loc);
        add.setName(name);
        add.setPhone_number(phone);
        List<String> types = new ArrayList<>();
        types.add("shoe");
        types.add("shop");
        add.setTypes(types);
        add.setWebsite("google.com");
        add.setLanguage(language);
        return add;
    }
}
