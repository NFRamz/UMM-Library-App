/**
 * Class Main.java
 * This class is the main class of the project.
 * This class is responsible for starting the application.
 * This class extends the Application class of the JavaFX library.
 *
 */
package Main;

import commands.CMD;

import data.LoginMenu;
import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        LoginMenu loginMenuObj = new LoginMenu();


            try{
                CMD.runCommands(CMD.listCommands[0]);

                loginMenuObj.menu();

            }catch (IOException e){
                System.out.println("Error: Main.java, Method: start\n"+"Details information:\n"+e);
            }




    }



    public static void main(String[] args) {
        launch(args);
    }

}
