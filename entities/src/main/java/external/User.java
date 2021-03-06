package external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

// Note: This is a copy of entities.User from UserService, with @Entity annotation and NamedQueries removed.
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    private int id;

    private String name;

    private String surname;

    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}