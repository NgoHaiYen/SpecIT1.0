package model;

import database.EmployeeDb;
import database.ImageDb;
import database.PriorityDb;
import database.StatusDb;

import java.sql.Date;

public class Request {
    private int id;
    private String subject;
    private String content;
    private int createdBy;
    private int status;
    private int priority;
    private String deadline;
    private int assignedTo;
    private int rating;
    private int teamId;

    private int branchId;
    private String createdAt;
    private String resolvedAt;
    private String closedAt;
    private String updatedAt;
    private String deletedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedByName() {
        EmployeeDb edb = new EmployeeDb();
        return edb.findById(createdBy);
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatusName() {
        StatusDb sdb = new StatusDb();
        return sdb.findById(status);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPriorityName() {
        PriorityDb pdb = new PriorityDb();
        return pdb.getPriorityNameById(priority);
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAssignedToName() {
        EmployeeDb edb = new EmployeeDb();
        return edb.findById(assignedTo);
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(String resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }


    public String getImage(){
        ImageDb idb = new ImageDb();
        return idb.getImageByRequestId(id);
    }
}
