package data;

import com.main.LibrarySystem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import util.iMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;


public class Admin extends User implements iMenu {

    //=================================== ATRIBUT =====================================
    public static String adminusername = "1";
    public static String adminpassword = "1";

//=================================== Main & Start Method ==================================



//======================================= MENU Method =======================================
    @Override
    public void menu(){
        Stage adminMenuStage = new Stage();
        adminMenuStage.setTitle("UMM Library - Admin Menu");

        //Label
        Label sceneTitle = new Label("Menu Admin");

        //Button
        Button addStudentButton     = new Button("Tambah Mahasiswa");
        Button displayStudentButton = new Button("Daftar Mahasiswa");
        Button addBookButton        = new Button("Tambah Buku");
        Button logoutButton         = new Button("Logout");

        //Image
        Image backgroundImage = new Image("file:src/main/java/image/background_login_image.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        //Shape
        Rectangle backgroundShape = new Rectangle();

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Settings Element <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        //Css Buton
        addStudentButton.getStylesheets().add("file:src/main/java/css/Login_button.css");
        displayStudentButton.getStylesheets().add("file:src/main/java/css/Login_button.css");
        addBookButton.getStylesheets().add("file:src/main/java/css/Login_button.css");
        logoutButton.getStylesheets().add("file:src/main/java/css/Login_button.css");

        //Shape rounded settings
        backgroundShape.setArcWidth(50);
        backgroundShape.setArcHeight(50);

        //Shape size
        backgroundShape.setWidth(300);
        backgroundShape.setHeight(300);

        //Shape color
        backgroundShape.setFill(Color.WHITE);

        //Position elements
        sceneTitle.setTranslateX(40);

        backgroundShape.setTranslateX(0);
        backgroundShape.setTranslateY(15);

        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle,0,1);

        grid.add(addStudentButton, 0,2);
        grid.add(displayStudentButton, 0,3);
        grid.add(addBookButton, 0,4);
        grid.add(logoutButton,0,5);

        grid.setVgap(20);
        grid.setHgap(5);

        //Overwrite elements

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, backgroundShape ,grid);
        Scene scene = new Scene(stackPane);


        //Create window
        adminMenuStage.setFullScreen(true);
        adminMenuStage.setFullScreenExitHint("");
        adminMenuStage.setScene(scene);
        adminMenuStage.show();

        //Action Button
        addStudentButton.setOnAction(event -> {
            addstudent();
            adminMenuStage.close();
        });

        displayStudentButton.setOnAction(event -> {
            displaystudent();
            adminMenuStage.close();
        });

        addBookButton.setOnAction(event -> {
            inputBook();
            adminMenuStage.close();
        });

        logoutButton.setOnAction(event -> {
            LibrarySystem librarySystemObj = new LibrarySystem();
            librarySystemObj.start(new Stage());
            adminMenuStage.close();
        });

    }

//===================================== Other Method =======================================
    public void addstudent() {

        // Membuat form baru
        Stage addStudentStage = new Stage();
        addStudentStage.setTitle("Tambah Mahasiswa");


        //Label
        Label sceneTitle    = new Label("Tambah Mahasiswa");
        Label nameLabel     = new Label("Nama");
        Label nimLabel      = new Label("NIM");
        Label fakultasLabel = new Label("Fakultas");
        Label jurusanLabel  = new Label("Jurusan");

        //Notification Label
        Label sumbitFailed = new Label("NIM harus 15 digit!");
        sumbitFailed.setVisible(false);

        //Button
        Button submitButton = new Button("Submit");
        Button returnButton = new Button("Kembali");

        //Field
        TextField nameField     = new TextField();
        TextField nimField      = new TextField();
        TextField fakultasField = new TextField();
        TextField jurusanField  = new TextField();

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        nameLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        nimLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        fakultasLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        jurusanLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        sumbitFailed.setFont(Font.font("Calibri Body",FontWeight.BOLD,15));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");
        sumbitFailed.setStyle("-fx-text-fill: #FF1E1E;");


        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(sceneTitle, 0,0);

        grid.add(nameLabel, 0,1);
        grid.add(nimLabel, 0,2);
        grid.add(fakultasLabel, 0,3);
        grid.add(jurusanLabel, 0,4);

        grid.add(nameField, 1,1);
        grid.add(nimField, 1,2);
        grid.add(fakultasField, 1,3);
        grid.add(jurusanField, 1,4);


        grid.add(returnButton,1,5);
        grid.add(submitButton,2,5);

        grid.add(sumbitFailed, 0,5);

        grid.setVgap(10);
        grid.setHgap(0);


        Scene scene = new Scene(grid);
        addStudentStage.setFullScreen(true);
        addStudentStage.setFullScreenExitHint("");
        addStudentStage.setScene(scene);
        addStudentStage.show();

        //Action Button
        submitButton.setOnAction(event -> {
            if (nimField.getText().length() == 15) {
                Admin adminObj = new Admin();

                Student.arr_userStudent.add(new Student.UserStudent(nameField.getText(), nimField.getText(), fakultasField.getText(), jurusanField.getText()));
                adminObj.menu();
                addStudentStage.close();

            } else {
                sumbitFailed.setVisible(true);
            }
        });
        returnButton.setOnAction(event -> {
            Admin adminObj =  new Admin();
            adminObj.menu();
            addStudentStage.close();
        });

    }

    public void displaystudent() {
        // Membuat stage baru
        Stage displayStudentStage = new Stage();
        displayStudentStage.setTitle("UMM Library - Daftar Mahasiswa");

        //Label
        Label sceneTitle    = new Label("Daftar Mahasiswa");

        //Button
        Button returnButton = new Button("Kembali");

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        // Membuat ListView untuk menampilkan mahasiswa
        ListView<String> listView = new ListView<>();

        for (Student.UserStudent i : Student.arr_userStudent) {
            String studentInfo = "Nama     : " + i.nama +"\n" +
                                 "NIM      : " + i.nim + "\n" +
                                 "Fakultas : " + i.fakultas + "\n" +
                                 "Prodi    : " + i.prodi + "\n" +
                                 "===========================";
            listView.getItems().add(studentInfo);
        }

        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle,0,0);
        grid.add(listView,0,1);
        grid.add(returnButton,0,2);

        grid.setVgap(5);

        Scene scene = new Scene(grid);
        displayStudentStage.setMaximized(true);
        displayStudentStage.setScene(scene);
        displayStudentStage.show();

        //Action
        returnButton.setOnAction(event -> {
            Admin adminObj = new Admin();
            adminObj.menu();
            displayStudentStage.close();
        });
    }


    public void inputBook(){
        super.inputBook();
    }

    public String generateId () {
        Random random = new Random();

        StringBuilder idTengah = new StringBuilder();
        StringBuilder idAkhir = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            idTengah.append(random.nextInt(10));
            idAkhir.append(random.nextInt(10));

        }
        return ("UMM-" + idTengah + "-" + idAkhir);

    }


}
