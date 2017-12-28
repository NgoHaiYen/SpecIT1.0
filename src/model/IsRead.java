package model;

public class IsRead {
    private int requestId;
    private int readerId;
    private int status;

    public IsRead() {
    }

    public IsRead(int requestId, int readerId) {
        this.requestId = requestId;
        this.readerId = readerId;
    }

    public IsRead(int requestId, int readerId, int status) {

        this.requestId = requestId;
        this.readerId = readerId;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
