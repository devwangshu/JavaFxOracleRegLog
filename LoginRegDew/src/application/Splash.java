package application;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.paint.CycleMethod; 


public class Splash
{

    static Scene splash;
    static Rectangle rect = new Rectangle();
    final private Pane pane;
    final private SequentialTransition seqT;

    public Splash()
    {
        pane = new Pane();
        pane.setStyle("-fx-background-color:#A3E4D7;-fx-border-width: 0px");

        splash = new Scene(pane,450,350);
        seqT = new SequentialTransition();
    }

    public void show()
    {

        //rectangle insertion
        int scale = 50;
        int dur = 200;
        rect = new Rectangle(120 - 2 * scale, 20, scale, scale);
      //Setting the radial gradient 
        Stop[] stops = new Stop[] { 
           new Stop(0.0, Color.WHITE),  
           new Stop(0.3, Color.RED), 
           new Stop(1.0, Color.DARKRED) 
        };
        RadialGradient radialGradient = 
                new RadialGradient(0, 0, 300, 178, 60, false, CycleMethod.REPEAT, stops); 
        //rect.setFill(Color.KHAKI);
        rect.setFill(radialGradient);

        int[] rotins = {scale, 2 * scale, 3 * scale, 4 * scale, 5 * scale, -6 * scale, -5 * scale, -4 * scale, -3 * scale, -2 * scale};
        int x, y;
        for (int i : rotins) {
            RotateTransition rt = new RotateTransition(Duration.millis(dur), rect);
            rt.setByAngle(i / Math.abs(i) * 90);
            rt.setCycleCount(1);
            TranslateTransition pt = new TranslateTransition(Duration.millis(dur), rect);
            x = (int) (rect.getX() + Math.abs(i));
            y = (int) (rect.getX() + Math.abs(i) + (Math.abs(i) / i) * scale);
            pt.setFromX(x);
            pt.setToX(y);
            ParallelTransition pat = new ParallelTransition();
            pat.getChildren().addAll(pt, rt);
            pat.setCycleCount(1);
            seqT.getChildren().add(pat);
        }
        seqT.play();
        seqT.setNode(rect);
        Label label = new Label("CDAC Kolkata");
        label.setFont(new Font("Verdana", 30));
        label.setStyle("-fx-text-fill:blue");
        label.setLayoutX(120);
        label.setLayoutY(70);
        Label lab = new Label("Launching...");
        lab.setFont(new Font("Times New Roman", 20));
        lab.setStyle("-fx-text-fill:white");
        lab.setLayoutX(170);
        lab.setLayoutY(180);
        //A complimentary image

//        Image image = new Image("https://s3.amazonaws.com/media.eremedia.com/uploads/2012/08/24111405/stackoverflow-logo-700x467.png");
//        ImageView iv = new ImageView(image);
//        iv.setFitWidth(32);
//        iv.setFitHeight(32);
//        iv.setX(174);
//        iv.setY(130);
        //now adding everything to position, opening the stage, start the animation
//        pane.getChildren().addAll(rect, label, lab, iv);
        ProgressBar progress = new ProgressBar();
        progress.setLayoutX(170);
        progress.setLayoutY(250);
        pane.getChildren().addAll(rect, label, lab,progress);

        seqT.play();
    }

    public Scene getSplashScene()
    {
        return splash;
    }

    public SequentialTransition getSequentialTransition()
    {
        return seqT;
    }
}
