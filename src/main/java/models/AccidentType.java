package models;

import javax.persistence.*;

/**
 * Created by vlad on 12/21/14.
 */
@Entity
public class AccidentType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    @Column(length = 40)
    public String value;

    public AccidentType(String value) {
        this.value = value;
    }

    public AccidentType() {
    }
}
