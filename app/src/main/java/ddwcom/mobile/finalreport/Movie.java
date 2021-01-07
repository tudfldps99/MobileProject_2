package ddwcom.mobile.finalreport;

import java.io.Serializable;

public class Movie implements Serializable {
    long _id;
    int img;
    String name;
    String actor;
    String director;
    String grade;
    String opening;

    public Movie(long _id, String name, String actor, String director) {
        this._id = _id;
        this.name = name;
        this.actor = actor;
        this.director = director;
    }

    public Movie(int img, String name, String actor, String director, String grade, String opening) {
        this.img = img;
        this.name = name;
        this.actor = actor;
        this.director = director;
        this.grade = grade;
        this.opening = opening;
    }

    public Movie(long _id, int img, String name, String actor, String director, String grade, String opening) {
        this._id = _id;
        this.img = img;
        this.name = name;
        this.actor = actor;
        this.director = director;
        this.grade = grade;
        this.opening = opening;
    }


    public long get_id() {
        return _id;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getActor() {
        return actor;
    }

    public String getDirector() {
        return director;
    }

    public String getGrade() {
        return grade;
    }

    public String getOpening() {
        return opening;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_id);
        sb.append(".\t\t");
        sb.append(name);
        sb.append(",\t");
        sb.append(actor);
        sb.append(",\t");
        sb.append(director);
        sb.append(",\t");
        sb.append(grade);
        sb.append(",\t");
        sb.append(opening);
        sb.append(",\t");

        return sb.toString();
    }
}
