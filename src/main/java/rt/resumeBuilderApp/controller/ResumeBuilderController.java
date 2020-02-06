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

package rt.resumeBuilderApp.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import rt.resumeBuilderApp.entities.Resume;
import rt.resumeBuilderApp.service.DocGenerator;
import rt.resumeBuilderApp.service.ResumeBuilder;
import rt.resumeBuilderApp.service.PropertiesManager;
import rt.resumeBuilderApp.ui.UILoader;

import java.awt.*;
import java.io.*;
import java.util.Map;


/**
 * Created by Raviteja  on 19-01-2020.
 */
public class ResumeBuilderController extends AbstractController {

    @FXML private TextField uploadFilePath ;
    @FXML private TextField templateSelectorPath;
    @FXML private Button submitBtn ;
    @FXML private Label message;
    private Map<String,String> scenes;
    private Scene helpScene , aboutScene;

    @FXML
    public void browseJSONFile(){
        browseFile(uploadFilePath);
    }

    @FXML
    public void browseTemplateFile(){
        browseFile(templateSelectorPath);
    }

    @FXML
    private void browseFile(TextField tf){
        message.setText("----");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Browse File");
        File file = chooser.showOpenDialog(tf.getParent().getScene().getWindow());
        if(file != null && !file.isDirectory()){
            //uploadFile = file;
            tf.setText(file.getPath());
        }
        if(uploadFilePath.getText().length()>0 && templateSelectorPath.getText().length()>0){
            submitBtn.setDisable(false);
        }
    }

    @FXML
    public void createResumeDoc()  {
        message.setText("----");
        if(uploadFilePath.getText().trim().length()>0 && templateSelectorPath.getText().trim().length()>0){
            ResumeBuilder builder = new ResumeBuilder(uploadFilePath.getText());
            try {
                Resume resume = builder.buildResume();
                String outPath = fileSaveDialog(new FileChooser.ExtensionFilter("Microsoft Word XML Document (*.docx)", "*.docx"));
                if(outPath!=null && outPath.trim().length()>0) {
                    DocGenerator generator = new DocGenerator(templateSelectorPath.getText(), outPath, resume);
                    if (generator != null) {
                        generator.generateResume();
                        message.setText("Resume generated at " + outPath);
                    } else {
                        message.setText("Unable to generate Resume. One or more file formats may be missing or of incorrect Format .Try again");
                    }
                }
            }catch(Exception ex){
                message.setText(ex.getStackTrace()[0]+" -- Error Occurred while creating document. Check input parameters ");
            }
        }else{
            message.setText("Unable to fetch file .Please check the file path");
        }
    }

    public String fileSaveDialog(FileChooser.ExtensionFilter extension){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(extension);
        File file = chooser.showSaveDialog(submitBtn.getParent().getScene().getWindow());
        if(file!=null){
            return file.getPath();
        }else{
            return "";
        }
    }


    @FXML
    public void resetForm(){
        message.setText("----");
        uploadFilePath.setText("");
        templateSelectorPath.setText("");
        submitBtn.setDisable(true);
    }


    @FXML
    public void initialize() {
        //to-do keep a listener to the text fields
        scenes = PropertiesManager.getSceneMap();
    }


    @FXML
    public void viewHelp()  {
        try {
            if(helpScene==null){
                helpScene =new UILoader().initScene(scenes.get("help"));
            }
            loadPopUp(helpScene,"Help");
        } catch (IOException e) {
            e.printStackTrace();
            message.setText(String.valueOf(e.getStackTrace()[0]));
        }
    }

    @FXML
    public void viewAbout(){
        try {
            if(aboutScene==null){
                aboutScene =new UILoader().initScene(scenes.get("about"));
            }
            loadPopUp(aboutScene,"About");
        } catch (IOException e) {
            e.printStackTrace();
            message.setText(String.valueOf(e.getStackTrace()[0]));
        }

    }

    public void loadPopUp(Scene scene,String title) throws IOException {
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
