package application;
import java.io.File;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application
{

//	private final String FILENAME = "C:\\Users\\user0581\\Dewangshu\\LoginRegDew\\src\\application\\app.properties";
	
	String currentDir = System.getProperty("user.dir");
	
    private final String FILENAME = currentDir+"\\"+"app.properties";
    @Override
    public void start(Stage stage) throws Exception
    {
        Splash splash = new Splash();
        splash.show();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(splash.getSplashScene());
        splash.getSequentialTransition().setOnFinished(e -> {
            Timeline timeline = new Timeline();
            KeyFrame key = new KeyFrame(Duration.millis(500),
                    new KeyValue(splash.getSplashScene().getRoot().opacityProperty(), 0));
            timeline.getKeyFrames().add(key);
            timeline.setOnFinished((event) -> {
                try {
                	File f = new File(FILENAME);
                	if(f.exists() && !f.isDirectory()) { 
                		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        //stage.show();
                	}
                	else {
                		System.out.println("app.properties file does not exists in this project..");
                		Parent root = FXMLLoader.load(getClass().getResource("DBConfig.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                	}
                    
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            timeline.play();
        });
        //stage.initStyle(StageStyle.UNIFIED);
        stage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }

}