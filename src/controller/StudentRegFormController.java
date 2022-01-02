package controller;

import bo.BOFactory;
import bo.ProgramBO;
import bo.ProgramDataBO;
import bo.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import dto.ProgramDTO;
import dto.ProgramDataDTO;
import dto.StudentDTO;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class StudentRegFormController {
    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);
    private final ProgramBO programBO = (ProgramBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);
    private final ProgramDataBO programDataBO = (ProgramDataBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAMDATA);

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
    public JFXComboBox<String> cmpProgram;
    public Label lbRegDate;
    public Label lbsID;

    public void initialize() {
        loadDate();

        try {
            setStudentID();
            loadPrograms();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lbRegDate.setText(f.format(date));
    }

    public void goToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm", contextStd);
    }

    public void loadPrograms() throws Exception {
        List<ProgramDTO> programDTOList = programBO.findAll();

        List<String> idList = new ArrayList<>();


        for (ProgramDTO programDTO : programDTOList) {
            idList.add(programDTO.getProgramID());
        }

        cmpProgram.getItems().addAll(idList);

    }

    public void setStudentID() throws SQLException, ClassNotFoundException {
        lbsID.setText(studentBO.generateNewStudentId());
    }


    public void register(MouseEvent mouseEvent) {
        StudentDTO student = new StudentDTO(
                lbsID.getText(),
                txtsName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtAddress.getText(),
                dob.getValue().toString(),
                txtNIC.getText(),
                txtPhone.getText(),
                txtpContact.getText(),
                txtPaName.getText()
        );

        ProgramDataDTO programDataDTO = new ProgramDataDTO(
                "D005",
                lbsID.getText(),
                cmpProgram.getSelectionModel().getSelectedItem(),
                lbRegDate.getText()

        );


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                if ( studentBO.add(student) && programDataBO.add(programDataDTO)){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();


                } else{
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
        }


    }
}
