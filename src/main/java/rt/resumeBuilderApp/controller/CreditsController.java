package rt.resumeBuilderApp.controller;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import rt.resumeBuilderApp.service.PropertiesManager;
import rt.resumeBuilderApp.ui.UILoader;

/**
 * Created by Raviteja  on 02-02-2020.
 */
public class CreditsController extends AbstractController {

    @FXML
    public void openGithubLink(){
        String path = "https://github.com/raviteja777/resume_builder_app";
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(new UILoader());
        hostServices.showDocument(path);
    }
}
