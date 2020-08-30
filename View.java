import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.control.Separator;
import javafx.scene.Group;
import java.util.Arrays;
import javafx.collections.FXCollections;


public class View 
{

    private TextArea translatorDescription;
    private ComboBox fromComboBox;
    private TextArea fromText;
    private Button translateButton;
    private ComboBox toComboBox;
    private TextArea toText;
    private Stage myStage;
    private Button exit;
    final int SPAN = 7;
    final int FONT = 13;
    
 
    public Stage getStage()   
    { 
    	return myStage;  
    }
    public Button getButton() 
    { 
    	return exit;
    }
    
    private void createLabel(String labelName, int xIndex, int yIndex, Font font, GridPane gPane)
    {
        Label label = new Label(labelName);

        if(font != null)
           label.setFont(font);
        GridPane.setConstraints(label, xIndex, yIndex);
        GridPane.setColumnSpan(label, 1);
        gPane.getChildren().add(label);
    }

    private void createSeparator(int yIndex, GridPane gPane)
    {
        final Separator sep = new Separator();
        sep.setValignment(VPos.CENTER);
        GridPane.setConstraints(sep, 0, yIndex);
        GridPane.setColumnSpan(sep,SPAN);
        gPane.getChildren().add(sep);
    }

    private ComboBox createComboBox(String languageArray[], int xIndex, int yIndex, GridPane gPane)
    {
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(languageArray));
        comboBox.setValue("English");
        GridPane.setConstraints(comboBox, xIndex, yIndex);
        GridPane.setColumnSpan(comboBox,1);
        gPane.setValignment(comboBox, VPos.TOP);
        gPane.getChildren().add(comboBox);
        return comboBox;
    }

    private TextArea createTextArea(int xIndex, int yIndex, GridPane gPane)
    {
        TextArea textArea = new TextArea("");
        GridPane.setConstraints(textArea,xIndex,yIndex);
        GridPane.setColumnSpan(textArea,2);
        gPane.getChildren().add(textArea);
        return textArea;
    }
    private Button createButton(String buttonName, int xIndex, int yIndex, GridPane gPane)
    {
        Button button = new Button(buttonName);
        GridPane.setConstraints(button,xIndex,yIndex);
        GridPane.setColumnSpan(button,1);
        gPane.getChildren().add(button);
        return button;
    }

    public void setStage(Stage stage) 
    {
        myStage = stage;
        int yIndex = 0;
        
        Group root = new Group();
        GridPane gPane = new GridPane();
        gPane.setPadding(new Insets(10,10,10,10));
        
        String languageArray[] = App.getTranslator().getLanguages();
        
        Arrays.sort(languageArray);
        Scene scene = new Scene(root, 500, 400, Color.GRAY);
        stage.setScene(scene);
        scene.setRoot(gPane);
        
        createLabel("BabelFish",3,yIndex,new Font(20),gPane);
        yIndex++;

        createSeparator(yIndex, gPane);
        yIndex++;     

        translatorDescription = new TextArea("Enter word or phrase and choose language to translate and choose the appropriate languages from the dropdown");
        translatorDescription.setPrefHeight(100);
        GridPane.setConstraints(translatorDescription, 0, yIndex);
        GridPane.setColumnSpan(translatorDescription, SPAN);
        translatorDescription.setEditable(false);
        gPane.getChildren().add(translatorDescription);
        yIndex++;
    
        createSeparator(yIndex, gPane);
        yIndex++;
        
        createLabel("From List", 0, yIndex, new Font(FONT), gPane);
        createLabel("From Language", 1, yIndex, new Font(FONT), gPane);
        createLabel("To Language", 4, yIndex, new Font(FONT), gPane);
        createLabel("To List", 6, yIndex, new Font(FONT), gPane);
        yIndex++;
        
        createSeparator(yIndex, gPane);
        yIndex++;

        fromComboBox = createComboBox (languageArray, 0, yIndex, gPane);
        
        fromText = createTextArea(1, yIndex, gPane);
        
        translateButton = createButton("Translate", 3, yIndex, gPane);
        
        toText = createTextArea(4, yIndex, gPane);
        toText.setEditable(false);
        
        toComboBox = createComboBox(languageArray, 6, yIndex, gPane);
        yIndex++;

        createSeparator(yIndex, gPane);
        yIndex++;

        exit = createButton("Close",3,yIndex,gPane);
        exit.setPrefWidth(80);
        
        TranslateScene myTranslateScene = App.getTranslateScene();
        Object list[] = new Object[4];
        list[0] = fromComboBox;
        list[1] = toComboBox;
        list[2] = fromText;
        list[3] = toText;
        myTranslateScene.setList(list);
        translateButton.setOnAction(myTranslateScene);
        exit.setOnAction(myTranslateScene);        
        myStage.show();
    }
}

