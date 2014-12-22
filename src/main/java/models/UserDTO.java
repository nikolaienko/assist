package models;

/**
 * Created by vlad on 12/21/14.
 */
public class UserDTO {
    public String login;
    public String password;
    public String fullname;
    public String info;
    public Long deviceId;
    public Double lat;
    public Double lng;

    public UserDTO(String login, String password, String fullname, String info, Long deviceId, Double lat, Double lng) {
        this.login = login;
        this.password = password;
        this.fullname = fullname;
        this.info = info;
        this.deviceId = deviceId;
        this.lat = lat;
        this.lng = lng;
    }
}
