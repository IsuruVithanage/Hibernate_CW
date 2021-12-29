package controller;

import bo.BOFactory;
import bo.StudentBO;
import com.jfoenix.controls.JFXDatePicker;
import dao.StudentDAO;
import dto.StudentDTO;
import entity.Student;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;
import java.util.Optional;

public class StudentRegFormController {
    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);
    public AnchorPane contextStd;
    public TextField txtsName;
    public TextField txtAge;
    public TextField txtPhone;
    public TextField txtNIC;
    public TextArea txtAddress;
    public TextField txtPaName;
    public TextField txtpContact;
    public Label lbID;
    public JFXDatePicker dob;

    public void goToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm",contextStd);
    }


    public void register(MouseEvent mouseEvent) {
        StudentDTO student=new StudentDTO(
                lbID.getText(),
                txtsName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtAddress.getText(),
                dob.getValue().toString(),
                txtNIC.getText(),
                txtPhone.getText(),
                txtpContact.getText(),
                txtPaName.getText()
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                if (studentBO.add(student)) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, "Duplicate Entry..").show();
            }
        }

    }
}
