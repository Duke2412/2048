package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import java.nio.file.Paths;

public class Game2048 extends Application {
    
    public static final int STEP = 45;
    
    private GameManager gameManager;
    
    public void music(){
        String bip = "NyanCat.mp3";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer.play();
    }
    @Override
    public void init() {
        if(STEP>=10){
            Font.loadFont(Game2048.class.getResource("ClearSans-Bold.ttf").toExternalForm(), 10.0);
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        StackPane root=new StackPane();
        if(STEP>=1){
            gameManager=new GameManager();
            root.getChildren().add(gameManager);
        }
        Scene scene = new Scene(root, 600, 700);
        if(STEP>=10){
            scene.getStylesheets().add(Game2048.class.getResource("game.css").toExternalForm());
            root.getStyleClass().addAll("game-root");
        }
        if(Game2048.STEP>=14){
            scene.setOnKeyPressed(ke -> {
                KeyCode keyCode = ke.getCode();
                if(keyCode.isArrowKey()){
                    Direction dir = Direction.valueFor(keyCode);
                    gameManager.move(dir);
                }
            });
        }
        music();
        primaryStage.setTitle("CAT2048");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}