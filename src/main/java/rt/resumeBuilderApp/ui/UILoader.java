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

package rt.resumeBuilderApp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import rt.resumeBuilderApp.service.PropertiesManager;

import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * Created by Raviteja  on 23-01-2020.
 */
public class UILoader extends Application  {

    private static Map<String,String> SCENE_MAP ;
    private static Iterator<String> scenes ;
    private static Stage stage;

    public static void main(String args[]){
        try {
            PropertiesManager.initSettings();
            initSceneMap(PropertiesManager.getSceneMap());
            launch(args);
        } catch(Exception e){
            new UILoader().errorHandler(e);
            e.printStackTrace();
        }

    }

    public void start(Stage stage){
        this.stage = stage;
        try {
            loadNextScene();
        }catch(Exception ex){
            errorHandler(ex);
        }
    }

    public static void initSceneMap(Map<String,String> sceneMap){
        if(SCENE_MAP==null || SCENE_MAP.size()==0){
            SCENE_MAP = sceneMap;
            scenes = SCENE_MAP.keySet().iterator();
        }
    }

    public void loadNextScene(){
        try {
            if (scenes.hasNext()) {
                String currentSceneName = scenes.next();
                String scenePath = SCENE_MAP.get(currentSceneName);
                System.out.println(currentSceneName+"--"+scenePath);
                Scene currentScene = initScene(scenePath);
                refreshStage(currentScene, currentSceneName.toUpperCase());
            } else {
                throw new Exception("No more screens to load");
            }
        }catch(Exception ex){
            errorHandler(ex);
        }
    }


    public Scene initScene(String path) throws IOException{
        URL url = getClass().getResource(path);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        return scene;
    }

    public void refreshStage(Scene scene, String title){
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void errorHandler(Exception ex){
        Label label = new Label();
        if(ex!=null) {
            ex.printStackTrace();
            label.setText(" "+ex.toString() + "\n" + ex.getStackTrace()[0].toString());
            Scene scene = new Scene(label);
            refreshStage(scene, "Error Message");
        }
    }


}
