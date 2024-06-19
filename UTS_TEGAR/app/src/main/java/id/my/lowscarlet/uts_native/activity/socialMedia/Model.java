package id.my.lowscarlet.uts_native.activity.socialMedia;

public class Model {
    // TODO Edit This Scarlet
    public String id;
    public String name;
    public String url;
    public Boolean isVerified;

    public Model(String name, String url, Boolean isVerified) {
        this.name = name;
        this.url = url;
        this.isVerified = isVerified;
    }
}
