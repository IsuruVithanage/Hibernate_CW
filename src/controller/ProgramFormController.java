package controller;

import bo.BOFactory;
import bo.ProgramBO;
import dto.ProgramDTO;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ProgramFormController {
    private final ProgramBO programBO=(ProgramBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);
    public AnchorPane contextPro;
    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public Label lbID;

    public void initialize(){
        try {
            setProgramID();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void backToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm",contextPro);
    }

    public void setProgramID() throws SQLException, ClassNotFoundException {
        lbID.setText(programBO.generateNewProgramId());
    }


    public void addProgram(MouseEvent mouseEvent){
        ProgramDTO programDTO=new ProgramDTO(
                lbID.getText(),
                txtName.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText()));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                if (programBO.add(programDTO)) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, e.getClass().getSimpleName()).show();
            }
        }
    }
}
