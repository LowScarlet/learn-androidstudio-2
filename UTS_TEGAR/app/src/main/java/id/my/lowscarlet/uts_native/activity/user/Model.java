package id.my.lowscarlet.uts_native.activity.user;

public class Model {
    // TODO Edit This Scarlet
    public String id;
    public String username;
    public String email;
    public String password;
    public String role;
    public Boolean isActive;

    public Model(String username, String email, String password, String role, Boolean isActive) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
}
