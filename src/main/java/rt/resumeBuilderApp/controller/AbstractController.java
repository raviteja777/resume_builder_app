package rt.resumeBuilderApp.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import rt.resumeBuilderApp.ui.UILoader;

import java.net.URL;


/**
 * Created by Raviteja  on 26-01-2020.
 */
public abstract  class AbstractController {

    @FXML
    public void nextScene(){
        //send control back to UILoader and load next scene
        new UILoader().loadNextScene();
    }



}
