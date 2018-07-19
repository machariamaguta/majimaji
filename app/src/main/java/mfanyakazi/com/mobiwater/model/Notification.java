package mfanyakazi.com.mobiwater.model;

public class Notification {

    String type;
    String title;
    String time;
    String description;

    public Notification(){

    }

    public Notification(String type, String title, String time, String description){
        this.type = type;
        this.time = time;
        this.title = title;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getCreatedAt() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return type;
    }

    public void setTitle(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

