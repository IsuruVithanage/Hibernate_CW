package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;

public class StudentRegFormController {
    public AnchorPane contextStd;
    public TextField txtsName;
    public TextField txtAge;
    public TextField txtPhone;
    public TextField txtNIC;
    public TextArea txtAddress;
    public TextField txtPaName;
    public TextField txtpContact;

    public void goToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm",contextStd);
    }


    public void register(MouseEvent mouseEvent) {
    }
}
