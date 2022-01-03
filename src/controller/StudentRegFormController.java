package controller;

import bo.BOFactory;
import bo.ProgramBO;
import bo.ProgramDataBO;
import bo.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import dto.ProgramDTO;
import dto.ProgramDataDTO;
import dto.StudentDTO;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public TextField txtID;
    public JFXComboBox<String> cmbRegProgram;
    public TextField txtRegDate;
    public Label txtFee;
    public AnchorPane btnClear;
    public JFXButton btnDelete;

    public void initialize() {
        loadDate();

        btnDelete.setDisable(true);

        try {
            setStudentID();
            loadPrograms();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbRegProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setRegDate(String.valueOf(newValue));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        cmpProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setFee(String.valueOf(newValue));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        txtsName.setOnAction(event -> {
            validate(KeyCode.ENTER, txtAge, "^[A-z ]{3,20}$", txtsName);
            txtsName.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtAge.setOnAction(event -> {
            validate(KeyCode.ENTER, txtPhone, "^[1-9]{1,2}$", txtAge);
            txtAge.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtPhone.setOnAction(event -> {
            validate(KeyCode.ENTER, txtNIC, "^[0-9]{10}$", txtPhone);
            txtPhone.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtPaName.setOnAction(event -> {
            validate(KeyCode.ENTER, txtpContact, "^[A-z ]{3,20}$", txtPaName);
            txtPaName.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtpContact.setOnAction(event -> {
            validate(KeyCode.ENTER, txtpContact, "^[0-9]{10}$", txtpContact);
            txtpContact.setStyle("-jfx-unfocus-color: #89f0c9");
        });

    }

    public void validate(KeyCode keyCode, TextField txt, String regex, TextField txtNow) {
        if (keyCode == KeyCode.ENTER) {
            if (!txtNow.getText().matches(regex)) {
                txtNow.setText("");
            } else {
                txt.requestFocus();
            }
        }
    }

    private void setFee(String valueOf) throws Exception {
        List<ProgramDTO> all = programBO.findAll();
        for (ProgramDTO programDTO : all) {
            if (programDTO.getProgramID().equals(cmpProgram.getSelectionModel().getSelectedItem())) {
                txtFee.setText(String.valueOf(programDTO.getFee()));
                return;
            }
        }
    }

    private void setRegDate(String newValue) throws Exception {
        List<ProgramDataDTO> all = programDataBO.findAll();
        for (ProgramDataDTO programDataDTO : all) {
            if (programDataDTO.getsID().equals(lbsID.getText()) && programDataDTO.getpID().equals(cmbRegProgram.getSelectionModel().getSelectedItem())) {
                txtRegDate.setText(programDataDTO.getDate());
                return;
            }
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

        if (txtsName.getText().trim().isEmpty() || txtAge.getText().trim().isEmpty() || txtNIC.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty() || txtPhone.getText().trim().isEmpty() || txtPaName.getText().trim().isEmpty() || txtpContact.getText().trim().isEmpty() || dob.getValue() == null || cmpProgram.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Empty").show();
        } else {


            if (txtID.getText().trim().isEmpty() || !isExists(txtID.getText())) {

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

                ProgramDataDTO programDataDTO = null;
                try {
                    programDataDTO = new ProgramDataDTO(
                            programDataBO.generateNewProgramDataId(),
                            lbsID.getText(),
                            cmpProgram.getSelectionModel().getSelectedItem(),
                            lbRegDate.getText()

                    );
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        if (studentBO.add(student) && programDataBO.add(programDataDTO)) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Message");
                            alert2.setContentText("Saved..");
                            alert2.show();


                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                        }
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                }

            } else {
                ProgramDataDTO programDataDTO = null;
                try {
                    programDataDTO = new ProgramDataDTO(
                            programDataBO.generateNewProgramDataId(),
                            lbsID.getText(),
                            cmpProgram.getSelectionModel().getSelectedItem(),
                            lbRegDate.getText()

                    );
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        if (programDataBO.add(programDataDTO)) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Message");
                            alert2.setContentText("Saved..");
                            alert2.show();


                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                        }
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                }
            }
        }


    }

    public boolean isExists(String id) {
        StudentDTO studentDTO = null;
        try {
            studentDTO = studentBO.find(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentDTO != null;
    }

    public void search(MouseEvent mouseEvent) {
        if (txtID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Empty").show();
        } else {
            try {
                btnDelete.setDisable(false);
                StudentDTO studentDTO = studentBO.find(txtID.getText());
                List<ProgramDataDTO> programDataDTOS = programDataBO.findAll();
                if (studentDTO != null) {
                    txtsName.setText(studentDTO.getName());
                    txtAge.setText(String.valueOf(studentDTO.getAge()));
                    txtNIC.setText(studentDTO.getNic());
                    lbsID.setText(studentDTO.getStudentID());
                    txtAddress.setText(studentDTO.getAddress());
                    txtPhone.setText(studentDTO.getPhoneNumber());
                    txtPaName.setText(studentDTO.getParentName());
                    txtpContact.setText(studentDTO.getParentPhoneNumber());
                    dob.setValue(LocalDate.parse(studentDTO.getDateOfBirth()));

                    List<String> ids = new ArrayList<>();
                    for (ProgramDataDTO programDataDTO : programDataDTOS) {
                        if (programDataDTO.getsID().equals(txtID.getText())) {
                            ids.add(programDataDTO.getpID());
                        }
                    }

                    cmbRegProgram.getItems().addAll(ids);


                } else {
                    new Alert(Alert.AlertType.WARNING, "Data not found").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void deleteStudent(MouseEvent mouseEvent) {
        try {
            List<ProgramDataDTO> all = programDataBO.findAll();
            all.removeIf(programDataDTO -> !programDataDTO.getsID().equals(lbsID.getText()));


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
            /*try {
                if (programDataBO.delete(lbsID.getText())) {

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();


                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }*/

                boolean bool = false;
                for (ProgramDataDTO programDataDTO : all) {
                    bool = programDataBO.delete(programDataDTO.getId());
                }
                if (bool && studentBO.delete(lbsID.getText())) {

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();


                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void clear(MouseEvent mouseEvent) {
        btnDelete.setDisable(true);
        txtFee.setText("");
        txtpContact.setText("");
        txtRegDate.setText("");
        txtPaName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtNIC.setText("");
        txtAge.setText("");
        txtID.setText("");
        txtsName.setText("");
        dob.setValue(null);
        cmbRegProgram.getItems().clear();
        try {
            setStudentID();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
