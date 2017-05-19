package Client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;


public class Controller implements Initializable {

    private ConversationController conversationController = new ConversationController();
    @FXML
    private TextArea fromServ;

    @FXML
    private TextArea typpedMessage;

    @FXML
    private Button button;

    @FXML
    ListView<String> currentUsers;

    @FXML
    public void clickHandler(MouseEvent arg0)
    {
        System.out.println(currentUsers.getSelectionModel().getSelectedItem());
        conversationController.s
    }

    private String text = "";
    private ObservableList<String> items;
    private String recipient = "";
    private ClientController c;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        fromServ.setText("Insert name");

        button.setOnAction((event) -> {
            text = typpedMessage.getText();
            typpedMessage.setText("");
            conversationController.setText(text);
        });

        items = FXCollections.observableArrayList();
        currentUsers.setItems(items);




        Thread login = new Thread(() -> conversationController.conversationStart(c, this));
        login.start();

        Thread currentUserHandler = new Thread(() -> {

            int size = items.size();

            while (true) {

                if (items.size() != size) {
                    currentUsers.setItems(items);
                    size = items.size();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        currentUserHandler.start();

    }


    public void changeText(String text) {
        fromServ.setText(text);
    }

    public void setCurrentUsers(String[] users) {

        Platform.runLater(() -> {
            currentUsers.getItems().clear();
            for (int i = 0; i < users.length; i++) {
                items.add(users[i]);
            }
            currentUsers.setItems(items);
        });

    }


}

