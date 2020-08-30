import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
	private static Translator myTranslator;
	private static View myView;
	private static TranslateScene myTranslateScene;
	
	public App() 
	{
		myTranslator = new Translator();
		myTranslateScene = new TranslateScene();
		myView = new View();		
	}
	
	public static Translator getTranslator()           
	{ 
		return myTranslator;
	}
	public static View getView()
	{ 
		return myView;       
	}
	public static TranslateScene getTranslateScene() 
	{ 
		return myTranslateScene;
	}
	

	public static void main(String[] args) 
	{ 
		launch(args); 
	}

	public void start(Stage stage) throws Exception 
	{
		System.out.println("Initializing app window...");
		myView.setStage(stage);
		System.out.println("Initialization has completed");
	}	
}