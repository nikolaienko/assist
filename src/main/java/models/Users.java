package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;
    public String username;
    public int password;
    public String fullname;
    public String info;
    
    public Users() {}
    
    public Users(String username, int password, String info) {
        this.username = username;
        this.password = password;
        this.info = info;
    }
 
}
