package dbclientapp.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class addAppointment {

    @FXML
    private ComboBox<?> addAppointmentContact;

    @FXML
    private ComboBox<?> addAppointmentCustomerID;

    @FXML
    private TextArea addAppointmentDesc;

    @FXML
    private DatePicker addAppointmentEndDate;

    @FXML
    private TextField addAppointmentEndTime;

    @FXML
    private Button addAppointmentExitBtn;

    @FXML
    private TextField addAppointmentID;

    @FXML
    private TextField addAppointmentLocation;

    @FXML
    private Button addAppointmentSaveBtn;

    @FXML
    private DatePicker addAppointmentStartDate;

    @FXML
    private TextField addAppointmentStartTime;

    @FXML
    private TextField addAppointmentTitle;

    @FXML
    private TextField addAppointmentType;

    @FXML
    private ComboBox<?> addAppointmentUserID;

    @FXML
    void addAppointmentExitOnClick(ActionEvent event) {

    }

    @FXML
    void addAppointmentSaveOnClick(ActionEvent event) {

    }

}
