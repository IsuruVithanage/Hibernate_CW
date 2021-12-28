package controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;

public class ProgramFormController {
    public AnchorPane contextPro;

    public void backToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm",contextPro);
    }
}
