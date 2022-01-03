package controller;

import bo.BOFactory;
import bo.ProgramBO;
import com.jfoenix.controls.JFXButton;
import dto.ProgramDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.LoadFXMLFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProgramFormController {
    private final ProgramBO programBO = (ProgramBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.PROGRAM);
    public AnchorPane contextPro;
    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public Label lbID;
    public TableColumn<ProgramDTO, String> colID;
    public TableColumn<ProgramDTO, String> colName;
    public TableColumn<ProgramDTO, String> colDuration;
    public TableColumn<ProgramDTO, String> colFee;
    public TableView<ProgramDTO> tblProgram;
    public JFXButton btnadd;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    ObservableList<ProgramDTO> obList = FXCollections.observableArrayList();
    int rowNumber;

    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("proName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        try {
            setProgramID();
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblProgram.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            rowNumber = (Integer) newValue;
            btnadd.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

            try {
                setProgramData(obList.get(rowNumber).getProgramID());
            } catch (Exception e) {
                //new Alert(Alert.AlertType.WARNING, "Error").show();
            }
        });

        txtName.setOnAction(event -> {
            validate(KeyCode.ENTER, txtDuration, "^[A-z ]{3,20}$", txtName);
            txtName.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtDuration.setOnAction(event -> {
            validate(KeyCode.ENTER, txtFee, "^[1-9 A-z ]{6,8}$", txtDuration);
            txtDuration.setStyle("-jfx-unfocus-color: #89f0c9");
        });

        txtFee.setOnAction(event -> {
            validate(KeyCode.ENTER, txtFee, "^[1-9]{1,}$", txtFee);
            txtFee.setStyle("-jfx-unfocus-color: #89f0c9");
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

    private void setProgramData(String programID) throws Exception {
        List<ProgramDTO> all = programBO.findAll();
        for (ProgramDTO programDTO : all) {
            if (programDTO.getProgramID().equals(programID)) {
                txtName.setText(programDTO.getProgramID());
                txtDuration.setText(programDTO.getDuration());
                txtFee.setText(String.valueOf(programDTO.getFee()));
                lbID.setText(programDTO.getProgramID());
            }
        }
    }

    public void backToMain(MouseEvent mouseEvent) throws IOException {
        LoadFXMLFile.load("MainForm", contextPro);
    }

    public void setProgramID() throws SQLException, ClassNotFoundException {
        lbID.setText(programBO.generateNewProgramId());
    }

    public void loadTable() throws Exception {
        obList.clear();
        List<ProgramDTO> all = programBO.findAll();

        for (ProgramDTO programDTO : all) {
            obList.add(programDTO);
        }
        tblProgram.setItems(obList);
    }


    public void addProgram(MouseEvent mouseEvent) {
        if (txtName.getText().trim().isEmpty() || txtDuration.getText().trim().isEmpty() || txtFee.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Empty text Fields").show();
            return;
        } else {
            ProgramDTO programDTO = new ProgramDTO(
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

                        setProgramID();
                        loadTable();
                        tblProgram.refresh();
                        clear();

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.WARNING, e.getClass().getSimpleName()).show();
                }
            }
        }
    }

    public void clear() {
        txtFee.setText("");
        txtDuration.setText("");
        txtName.setText("");
        try {
            setProgramID();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProgram(MouseEvent mouseEvent) {
        ProgramDTO programDTO = new ProgramDTO(
                lbID.getText(),
                txtName.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText()));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                if (programBO.update(programDTO)) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Message");
                    alert2.setContentText("Saved..");
                    alert2.show();

                    obList.remove(rowNumber);
                    obList.add(programDTO);
                    tblProgram.refresh();

                    clear();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, e.getClass().getSimpleName()).show();
            }
        }

    }
}
