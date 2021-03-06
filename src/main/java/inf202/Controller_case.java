package inf202;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller_case implements Initializable {

    GlobalNotifications globalNotifications = new GlobalNotifications();
    private Stage stage;
    private Scene scene;
    private Parent root;

    Database db = new Database();

    @FXML
    Button btn_backToVerwaltung, btn_caseSave;
    @FXML
    private TextField tf_caseDate, tf_caseCode, tf_caseClass, tf_caseState;
    @FXML
    private TextArea ta_description;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void backToVerwaltung(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Screen_verwaltung.fxml"));
        //programın kullandığı pencereye erişim
        stage = (Stage) btn_backToVerwaltung.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void caseSave( ActionEvent e) throws SQLException {
        if(tf_caseCode.getText() != null && tf_caseClass.getText() != null){
            if (tf_caseState.getText() == null || tf_caseState.getText().length() < 3){
                db.add_case(tf_caseDate.getText(), tf_caseCode.getText(), tf_caseClass.getText(),
                        "on-going", ta_description.getText());
            }else{
                db.add_case(tf_caseDate.getText(), tf_caseCode.getText(), tf_caseClass.getText(),
                        tf_caseState.getText(), ta_description.getText());
                System.out.printf(ta_description.getText());
            }

        }else {
            System.out.println("casecode or caseclass can not be null");
            globalNotifications.warningDialog("WARNING!", "Casecode or Caseclass can NOT be NULL");
        }
    }


}
