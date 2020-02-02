package rt.resumeBuilderApp.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raviteja  on 11-01-2020.
 */
public class Experience implements Serializable{

    private String company;
    private String duration;
    private String designation;
    @JsonProperty("job_description")
    private List<String> description;

    //setters and getters


    public void setCompany(String company) {
        this.company = company;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public String getDuration() {
        return duration;
    }

    public String getDesignation() {
        return designation;
    }

    public List<String> getDescription() {
        return description;
    }

    @Override
    public String toString(){
        //StringBuilder description_para = new StringBuilder();
        //description.forEach(s -> description_para.append(s+"\n"));
        return String.format("%s%60s%30s\n%s\n",this.company,this.duration,this.designation,description.stream().reduce("",(s1,s2)->s1+"\n"+s2));
    }
}
