package model;

import database.EmployeeDb;

public class Relater {
    private int relationId;
    private int requestId;
    private int employeeId;
    private String createdAt;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName(){
        EmployeeDb employeeDb = new EmployeeDb();
        return employeeDb.getNameById(employeeId);
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public int getRelationId() { return relationId; }

    public void setRelationId(int relationId) { this.relationId = relationId; }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
