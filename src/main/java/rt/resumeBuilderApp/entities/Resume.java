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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by Raviteja  on 11-01-2020.
 * Resume bean class - maps to Json template
 */
public class Resume implements Serializable {

    //details
    private String name;
    private String phone;
    private String email;
    private Address address;

    @JsonProperty("other_details")
    private Map<String,Object> others;

    //fields in resume
    private String headline;
    private List<String> objectives;

    @JsonProperty("employment_history")
    private List<Experience> employmentHistory;

    @JsonProperty("education")
    private List<Education> educationList;
    private List<String> responsibilities;
    private List<String> skills;
    private List<String> certifications;
    private String declaration;


    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<String, Object> getOthers() {
        return others;
    }

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public List<String> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<String> objectives) {
        this.objectives = objectives;
    }

    public List<Experience> getEmploymentHistory() {

        return employmentHistory;
    }
    public void setEmploymentHistory(List<Experience> employmentHistory) {
        this.employmentHistory = employmentHistory;
    }
    public List<Education> getEducationList() {
        return educationList;
    }
    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }
    public List<String> getResponsibilities() {
        return responsibilities;
    }
    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    //override to string to display resume object
    @Override
    public String toString(){
        Class<? extends Resume> thisClass = this.getClass();
        Field[] fields = thisClass.getDeclaredFields();
        StringBuilder resumeString = new StringBuilder();
        for (Field field : fields){
            try {
                resumeString.append(field.get(this)+"\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resumeString.toString();
    }
}
