package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String userName;
    private String passWord;

    private Role role;

    private int teamId;
    private Team team;

    private int branchId;
    private Branch branch;

    private List<Request> created = new ArrayList<>(0);
    private List<Request> assigned = new ArrayList<>(0);
    private List<Request> relateRequests = new ArrayList<>(0);
    private List<Request> reads = new ArrayList<>(0); // danh sách những request đã đọc

    public List<Request> getReads() {
        return reads;
    }

    public void setReads(List<Request> reads) {
        this.reads = reads;
    }

    public List<Request> getRelateRequests() {
        return relateRequests;
    }

    public void setRelateRequests(List<Request> relateRequests) {
        this.relateRequests = relateRequests;
    }

    public List<Request> getAssigned() {
        return assigned;
    }

    public void setAssigned(List<Request> assigned) {
        this.assigned = assigned;
    }

    public List<Request> getCreated() {
        return created;
    }

    public void setCreated(List<Request> requests) {
        this.created = requests;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
