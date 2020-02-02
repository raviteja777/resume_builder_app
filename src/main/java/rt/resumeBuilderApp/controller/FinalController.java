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
public class FinalController extends AbstractController {

    @FXML
    public void openGithubLink(){
        String path = PropertiesManager.getConfigProperty("github_link");
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(new UILoader());
        hostServices.showDocument(path);
    }
}
