package data;

import Features.Database;
import exception.custom.IllegalAdminAccess;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import sound.Sound;


public class LoginMenu {

    public final static TextField usernameField = new TextField();

    public void menuGUI(){
        Admin adminObj = new Admin();
        Student studentObj = new Student();
        Stage primaryStage = new Stage();
        Student.arr_userStudent.add(new Student.UserStudent("Naufal Ramzi", "202310370311026", "Teknik", "Informatika"));

        primaryStage.setTitle("UMM Library");

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> LABEL <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //Label
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        //Notification label
        Label errorLoginMessage   = new Label("Pengguna tidak ditemukan !");

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> FIELD <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //Field

        PasswordField passwordField = new PasswordField();

        //Field prompt
        usernameField.setPromptText("Masukkan NIM (15 Digit!)");
        passwordField.setPromptText("Masukkan PIC");

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> BUTTON <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //Button
        Button loginButtom = new Button("Login");
        loginButtom.getStylesheets().add("file:src/main/java/css/Login_button.css");

        Button closeButton = new Button("X");
        closeButton.getStylesheets().add("file:src/main/java/css/closeButton.css");
        Tooltip closeButtonPopup = new Tooltip("Exit");
        Tooltip.install(closeButton, closeButtonPopup);

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> IMAGE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //Image
        Image backgroundImage = new Image("file:src/main/java/image/background_login_image.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Image logoImage = new Image("file:src/main/java/image/logo.png");
        ImageView logoImageView = new ImageView(logoImage);

        Image logoNameImage = new Image("file:src/main/java/image/logoName.png");
        ImageView logoNameImageView = new ImageView(logoNameImage);

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SHAPE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        //Shape
        Rectangle rectangle = new Rectangle(300, 380);
        Rectangle blurShape = new Rectangle(1366,768);
        Rectangle errorBackground = new Rectangle(300,70);

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Settings Element <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        //Font Style
        usernameLabel.setFont(Font.font("Calibri Body", FontWeight.BOLD, 15));
        passwordLabel.setFont(Font.font("Calibri Body", FontWeight.BOLD, 15));
        errorLoginMessage.setFont(Font.font("Calibri Body", FontWeight.BOLD, 13));
        loginButtom.setFont(Font.font("Calibri Body", FontWeight.BOLD,10));

        //Font Color
        errorLoginMessage.setStyle("-fx-text-fill: #FFFFFF;");

        //Image size
        logoImageView.setFitHeight(120);
        logoImageView.setFitWidth(120);

        logoNameImageView.setFitHeight(90);
        logoNameImageView.setFitWidth(200);

        //Shape blur settings
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(10.0);
        blurShape.setEffect(blur);
        blurShape.setOpacity(0.5);

        //Shape rounded settings
        rectangle.setArcWidth(50);
        rectangle.setArcHeight(50);

        errorBackground.setArcWidth(50);
        errorBackground.setArcHeight(50);

        //Shape color
        rectangle.setFill(Color.WHITE);
        blurShape.setFill(Color.BLACK);
        errorBackground.setFill(Color.MAROON);

        //Group element
        Group errorElementGroup = new Group();
        errorElementGroup.getChildren().add(errorBackground);
        errorElementGroup.getChildren().add(errorLoginMessage);

        //Visible settings
        errorElementGroup.setVisible(false);

        //Position element (advanced settings)
        logoImageView.setTranslateX(4);
        logoImageView.setTranslateY(-158);

        logoNameImageView.setTranslateX(4);
        logoNameImageView.setTranslateY(-65);

        usernameLabel.setTranslateY(58);
        passwordLabel.setTranslateY(58);

        usernameField.setTranslateY(58);
        passwordField.setTranslateY(58);

        loginButtom.setTranslateY(63);

        closeButton.setTranslateX(645);
        closeButton.setTranslateY(-355);

        errorLoginMessage.setTranslateX(69);
        errorLoginMessage.setTranslateY(48);

        rectangle.setTranslateX(0);
        rectangle.setTranslateY(20);

        blurShape.setTranslateX(0);
        blurShape.setTranslateY(0);


        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(logoNameImageView,0,0);

        grid.add(usernameLabel, 0,4);
        grid.add(passwordLabel, 0,6);


        grid.add(usernameField, 0,5);
        grid.add(passwordField, 0,7);

        grid.add(loginButtom, 0,9);
        grid.add(errorElementGroup,0,10);

        grid.setVgap(10);
        grid.setHgap(5);


        //Animation element show
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), errorElementGroup);
        translateTransition.setFromY(170);
        translateTransition.setToY(200);

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),errorElementGroup);
        translateTransition1.setFromY(200);
        translateTransition1.setToY(170);


        //Overwrite element
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView,blurShape,errorElementGroup,rectangle,grid,logoImageView,logoNameImageView,closeButton);

        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        //Action Button
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event1 -> {
            translateTransition1.play();
            PauseTransition delay1 = new PauseTransition(Duration.seconds(2));
            delay1.setOnFinished(event2 -> errorElementGroup.setVisible(false));
        });

        closeButton.setOnAction(event -> primaryStage.close());

        loginButtom.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (Database.admin_login_checker(username, password)) {
                adminObj.menu();
                primaryStage.close();
            } else {
                try {
                    if (username.length() == 15 && Database.student_login_checker(username, password)) {
                        Database.book_display();
                        studentObj.menu();
                        errorLoginMessage.setVisible(false);
                        primaryStage.close();
                    } else {
                        // Kondisi untuk aksi jika kredensial tidak cocok dengan admin dan bukan siswa
                        delay.play();
                        Sound.falseLogin();
                        errorElementGroup.setVisible(true);
                        translateTransition.play();
                    }
                } catch (IllegalAdminAccess message) {
                    errorLoginMessage.setText(message.getMessage());
                    errorElementGroup.setVisible(true);
                    delay.play();
                    Sound.falseLogin();
                    translateTransition.play();
                }
            }
        });

    }
}