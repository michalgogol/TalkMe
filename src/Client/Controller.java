package Client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class Controller {

    @FXML
    private TextArea fromServ;

    @FXML
    private Button button;

    @FXML
    private void initialize()
    {

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

    }





}

