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

import rt.resumeBuilderApp.entities.Resume;
import rt.resumeBuilderApp.service.DocGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raviteja  on 12-01-2020.
 */
public class TestDocGenerator {

    public static void main(String args[]){

        final String CURRENT_DIR = System.getProperty("user.dir");

        String templatePath = CURRENT_DIR+"\\templates\\default.docx";
        String outPath =CURRENT_DIR+"\\output\\resume_out.docx";

        try {
            Resume res = TestResumeBuilder.build();
            DocGenerator generator = new DocGenerator(templatePath,outPath,res);
            generator.generateResume();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
