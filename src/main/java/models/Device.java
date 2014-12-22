package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vlad on 12/21/14.
 */
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public Double lat;
    public Double lng;
    @ElementCollection(fetch=FetchType.EAGER)
    public List<Long> users;

    public Device() {
    }

    public Device(List<Long> users, Double lat, Double lng) {
        this.users = users;
        this.lat = lat;
        this.lng = lng;
    }

}
