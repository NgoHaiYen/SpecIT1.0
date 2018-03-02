package model;

import java.util.HashSet;
import java.util.Set;

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

    private Set<Request> created = new HashSet<>(0);
    private Set<Request> assigned = new HashSet<>(0);
    private Set<Request> relateRequests = new HashSet<>(0);
    private Set<Request> reads = new HashSet<>(0); // danh sách những request đã đọc

    public Set<Request> getReads() {
        return reads;
    }

    public void setReads(Set<Request> reads) {
        this.reads = reads;
    }

    public Set<Request> getRelateRequests() {
        return relateRequests;
    }

    public void setRelateRequests(Set<Request> relateRequests) {
        this.relateRequests = relateRequests;
    }

    public Set<Request> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Request> assigned) {
        this.assigned = assigned;
    }

    public Set<Request> getCreated() {
        return created;
    }

    public void setCreated(Set<Request> requests) {
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
