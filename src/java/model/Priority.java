package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Priority implements Serializable{
    private int id;
    private String name;

    private List<Request> requests = new ArrayList<>(0);

    public Priority() {

    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
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
