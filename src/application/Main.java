package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	// for setting default window sizes
	private int	vSize = 720;
	private int hSize = 1280;
/*	
	private static Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
	Main.primaryStage = primaryStage;
	Main.primaryStage.setTitle("Smart Home");
	
	initRootLayout();
	showSmartHome();
	}

	public void initRootLayout(){
    	rootLayout = Singleton.getInstance()._rootLayout;//creating the instance
		Scene scene = Singleton.getInstance()._scene;
		scene.getStylesheets().add(Main.class.getResource("dark.css").toString());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	public void showSmartHome() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("smart_home.fxml"));
			AnchorPane SmartHome = (AnchorPane) loader.load();
			rootLayout.setCenter(SmartHome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getPrimaryStage() {
	    return Main.primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
*/

@Override
public void start(Stage primaryStage) throws Exception {
    // setting up first pane and scene
    FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("smart_home.fxml"));
    Parent firstPane = firstPaneLoader.load();
    Scene firstScene = new Scene(firstPane, hSize, vSize);

    // setting up second pane and scene
    FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("smart_home_usage.fxml"));
    Parent secondPane = secondPageLoader.load();
    Scene secondScene = new Scene(secondPane, hSize, vSize);
    
    // setting up third pane and scene
    FXMLLoader thirdPageLoader = new FXMLLoader(getClass().getResource("smart_home_diagnostics.fxml"));
    Parent thirdPane = thirdPageLoader.load();
    Scene thirdScene = new Scene(thirdPane, hSize, vSize);

    // setting up second and third scene in first scene
    SmartHomeController smartHomeController = (SmartHomeController) firstPaneLoader.getController();
    smartHomeController.setUsageScene(secondScene);
    smartHomeController.setDiagnosticsScene(thirdScene);

    // setting up first and third scene in second scene
    SmartHomeUsageController smartHomeUsageController = (SmartHomeUsageController) secondPageLoader.getController();
    smartHomeUsageController.setHomeScene(firstScene);
    smartHomeUsageController.setDiagnosticsScene(thirdScene);
    
    // setting up first and second scene in first scene
    SmartHomeDiagnosticsController smartHomeDiagnosticsController = (SmartHomeDiagnosticsController) thirdPageLoader.getController();
    smartHomeDiagnosticsController.setHomeScene(firstScene);
    smartHomeDiagnosticsController.setUsageScene(secondScene);
 

    primaryStage.setTitle("Smart Home");
    primaryStage.setScene(firstScene);
    primaryStage.show();
}
}