package rt.resumeBuilderApp.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Raviteja  on 11-01-2020.
 */
public class Education implements Serializable{
    private String institution;
    private String duration;
    private String course;
    private String grade;


    //getters and setters
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getInstitution() {
        return institution;
    }

    public String getDuration() {
        return duration;
    }

    public String getCourse() {
        return course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString(){
        return String.format("%s%10s%30s%20s\n",institution,course,duration,grade);
    }
}
