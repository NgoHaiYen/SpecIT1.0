package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Priority implements Serializable{
    private int id;
    private String name;

    private Set<Request> requests = new HashSet<>(0);

    public Priority() {

    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

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
}
