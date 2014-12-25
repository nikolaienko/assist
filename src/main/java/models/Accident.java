package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 12/21/14.
 */
@Entity
public class Accident {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @ElementCollection(fetch=FetchType.EAGER)
    public List<Device> deviceId;


    public Double lat;
    public Double lng;
    public Date accidentDate;
    public boolean resolveStatus;

    public Accident() {
    }

    public Accident(List<Device> deviceId, Double lat, Double lng) {
        this.deviceId = deviceId;
        this.lat = lat;
        this.lng = lng;
        this.accidentDate = new Date();
        resolveStatus = false;
    }
}
