package ch.jolly.nights.bo;

public enum Status {
    NOTHING(0, ""),
    ADMIN(1, "Admin"),
    PENDING(2, "Pending"),
    ACCEPTED(3, "Accepted"),
    DECLINED(4, "Declined");

    private int key;
    private String type;

    Status(int key, String type) {
        this.key = key;
        this.type = type;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Status getStatus(int key) {
        switch (key) {
            case 1:
                return ADMIN;
            case 2:
                return PENDING;
            case 3:
                return ACCEPTED;
            case 4:
                return DECLINED;
        }
        return null;
    }
}
