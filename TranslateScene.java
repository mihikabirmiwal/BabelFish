import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class TranslateScene implements EventHandler<ActionEvent> 
{

    Object list[];	
    public void setList(Object oList[])
    {
        list = oList;
    }
    
    // Event Handler Method
    public void handle(ActionEvent event) 
    {
        String source = ((Button)event.getSource()).getText();
        if (source.contentEquals("Close"))
            Platform.exit();
        else
        {
            ComboBox fromComboBox = (ComboBox) list[0];
            ComboBox toComboBox = (ComboBox) list[1];
            TextArea fromText = (TextArea) list[2];
            TextArea toText = (TextArea) list[3];
            String fromLanguage = (String)fromComboBox.getSelectionModel().getSelectedItem();
            String toLanguage = (String)toComboBox.getSelectionModel().getSelectedItem();
            String fromWord = fromText.getText();
            
            Translator myTranslator = App.getTranslator();
            TreeMap<String, ArrayList<String>> words = myTranslator.getDatabase();
            
            String[] wordArray = fromWord.split("\\s+");
            String translated = "";
            for(int i=0;i<wordArray.length;i++)
            {
            	translated+= myTranslator.doTranslation(wordArray[i], fromLanguage, toLanguage) + " " ;
            }
            toText.setText(translated);            
        }
    }
}