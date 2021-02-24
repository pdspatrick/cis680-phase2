package RetirementCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static RetirementCalculator.Main.dataInput;
import static RetirementCalculator.Controller.*;

public class TextFieldFiltering extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();

        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println("Text change from " + oldValue + " to " + newValue);
        });
        UnaryOperator<Change> filter = change -> {
            if (change.isAdded()) {
                String addedText = change.getText();
                if (addedText.matches("[0-9]*\\.")) {
                    return change ;
                }
                // remove illegal characters:
                int length = addedText.length();
                addedText = addedText.replaceAll("[^0-9\\.]", "");
                change.setText(addedText);

                // modify caret position if size of text changed:
                int delta = addedText.length() - length ;
                change.setCaretPosition(change.getCaretPosition() + delta);
                change.setAnchor(change.getAnchor() + delta);
            }
            return change ;
        };
        textField.setTextFormatter(new TextFormatter<String>(filter));

        StackPane root = new StackPane(textField);
        root.setPadding(new Insets(20));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);

}
}
