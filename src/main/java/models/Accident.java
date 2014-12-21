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

    @ElementCollection(fetch=FetchType.EAGER)
    public List<AccidentType> accidentTypeId;

    public Double lat;
    public Double lng;
    public Date accidentDate;
    public boolean resolveStatus;

    public Accident() {
    }

    public Accident(List<Device> deviceId, List<AccidentType> accidentTypeId, Double lat, Double lng, Date accidentDate, boolean resolveStatus) {
        this.deviceId = deviceId;
        this.accidentTypeId = accidentTypeId;
        this.lat = lat;
        this.lng = lng;
        this.accidentDate = accidentDate;
        this.resolveStatus = resolveStatus;
    }
}
