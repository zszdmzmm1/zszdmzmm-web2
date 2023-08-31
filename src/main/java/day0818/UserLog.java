package day0818;

public class UserLog {
    private int id;
    private int userId;
    private String time;
    private String behavior;

    public UserLog(int userId, String time, String behavior) {
        this.userId = userId;
        this.time = time;
        this.behavior = behavior;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
