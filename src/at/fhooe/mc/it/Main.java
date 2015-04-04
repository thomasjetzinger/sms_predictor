package at.fhooe.mc.it;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private MainModel mMainModel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mMainModel = new MainModel();

        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("ui.fxml"));
        Parent root = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        mainController.setMainModel(mMainModel);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
