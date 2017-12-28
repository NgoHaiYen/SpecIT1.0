package model;

public class Subteam {
    private int id;
    private String name;
    private int subleaderId;
    private int itteamId;

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

    public int getItteamId() {
        return itteamId;
    }

    public void setItteamId(int itteamId) {
        this.itteamId = itteamId;
    }
}
