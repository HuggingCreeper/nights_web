package ch.jolly.nights.bo;

public enum PageIds {
    LOGIN(0),
    REGISTER(1),
    HOME(2),
    MYPROFILE(3),
    ADDEVENT(4),
    INVITATIONS(5),
    SHOWINVITE(6),
    ADMINEVENT(7),
    EDITEVENT(8);

    private int id;


    PageIds(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
