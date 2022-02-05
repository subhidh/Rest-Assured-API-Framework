package Resources;

public enum APIRecources {
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    private String resource;
    APIRecources(String s) {
        this.resource = s;
    }
    public String getResource(){
        return resource;
    }
}
