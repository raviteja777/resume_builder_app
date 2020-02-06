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

/**
 * Created by Raviteja  on 11-01-2020.
 */
public class Address implements Serializable {
    private String house;
    private String locality;
    private String city;
    private String pincode;


    public void setHouse(String house) {
        this.house = house;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getHouse() {
        return house;
    }

    public String getLocality() {
        return locality;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    @Override
    public String toString(){
        return ""+this.house+"\n"+this.locality+"\n"+this.city+"\n"+this.pincode+"\n";
    }
}
