/*******************************************************************************
 * Copyright 2020 Raviteja Chowdari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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
