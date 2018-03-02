package model;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private int id;
    private String name;

    private int subleaderId;
    private Employee subleader;

    private Branch itteam;

    private Set<Request> requests = new HashSet<>(0);

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Employee getSubleader() {
        return subleader;
    }

    public void setSubleader(Employee subleader) {
        this.subleader = subleader;
    }

    public Branch getItteam() {
        return itteam;
    }

    public void setItteam(Branch itteam) {
        this.itteam = itteam;
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

    public int getSubleaderId() {
        return subleaderId;
    }

    public void setSubleaderId(int subleaderId) {
        this.subleaderId = subleaderId;
    }
}
