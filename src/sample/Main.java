package sample;

import com.sun.prism.paint.Color;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application {
   BorderPane bp = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception{
        intro();
        primaryStage.setTitle("Sieve of Eratosthenes Visualizer");
        primaryStage.setScene(new Scene(bp,800,600));
        primaryStage.show();

    }
    void intro(){
        VBox vbox = new VBox();
        Label l = new Label("Enter The Value of N");
        l.setStyle("-fx-font-size: 16pt;");
        TextField tf = new TextField();
        HBox hBoxTF = new HBox(tf);
        hBoxTF.setAlignment(Pos.CENTER);
        Button enter = new Button("Enter");
        enter.setOnAction(e -> enterOnAction(tf));
        HBox hBoxButton = new HBox(enter);
        hBoxButton.setAlignment(Pos.CENTER_RIGHT);
        enter.setStyle("-fx-font-size: 12pt");
        vbox.getChildren().addAll(l,hBoxTF,hBoxButton);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10,10,10,10));
        bp.setCenter(vbox);

    }
    void enterOnAction(TextField tf){
        if(tf.getText().equals("") ){
            Label completeInfo = new Label("Please Enter the Value of N ");
            completeInfo.setStyle("-fx-font-size: 16pt");
            Button okay = new Button("Okay");
            okay.setStyle("-fx-font-size: 10pt");
            HBox buttonsHpane = new HBox(30);
            buttonsHpane.setAlignment(Pos.CENTER);
            buttonsHpane.getChildren().add(okay);
            VBox pane = new VBox(30);
            pane.getChildren().addAll(completeInfo, buttonsHpane);
            pane.setPadding(new Insets(30, 30, 30, 30));
            Scene completeInfoScene = new Scene(pane);
            Stage completeInfoStage = new Stage();
            completeInfoStage.setTitle("Missing Information");
            completeInfoStage.setScene(completeInfoScene);
            completeInfoStage.show();
            okay.setOnAction(e -> completeInfoStage.close());
        }else{
            int n = Integer.parseInt(tf.getText());
            GridPane gp = new GridPane();
            Pane[] arr = new Pane[n + 1];
            boolean[] notPrime = new boolean[n+1];
            int count = 2;
            int size = (int)Math.ceil(Math.sqrt(n));
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(count == n+1)
                        break;
                    Label l = new Label(String.valueOf(count));
                   arr[count] =  new Pane(l);
                   l.setPadding(new Insets(10,10,10,10));
                   l.setStyle(" -fx-font-size: 16; -fx-font-weight: bold");
                    arr[count].setStyle("-fx-background-color : greenyellow ; -fx-border-color : black");
                    gp.add(arr[count],j,i);
                    count ++;
                }
            }
            int end = (int) Math.ceil(Math.sqrt(n+1));
            //label.setText(table[0]); // set text for the first time

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

                private int i = 2;

                @Override
                public void handle(ActionEvent event) {
                    if(!notPrime[i]){

                        for (int j = i*i; j <= n ; j+=i) {
                            notPrime[j] = true;
                            arr[j].setStyle("-fx-background-color : orangered; -fx-border-color : black");
                            bp.setCenter(gp);

                        }

                    }
                    i ++;
                }
            }));
            timeline.setCycleCount(end - 2);
            timeline.play();
           /* for (int i = 2; i < end; i++) {
                if(!notPrime[i]){
                    for (int j = i*i; j <= n ; j+=i) {
                        notPrime[j] = true;
                        arr[j].setStyle("-fx-background-color : orangered; -fx-border-color : black");

                        bp.setCenter(gp);

                    }
                }
            }*/

            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 2; i <= n; i++) {
                if(!notPrime[i]){
                    ans.add(i);
                }
            }
            Label ansl = new Label("Prime Numbers : "+String.valueOf(ans));
            ansl.setStyle(" -fx-font-size: 16; -fx-font-weight: bold");
            gp.setAlignment(Pos.CENTER);
            HBox hb = new HBox(ansl);
            hb.setAlignment(Pos.CENTER);
            hb.setPadding(new Insets(0,0,20,0));
            bp.setBottom(hb);
            bp.setCenter(gp);
        }



    }


    public static void main(String[] args) {
        launch(args);
    }
}
