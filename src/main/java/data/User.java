package data;

import Features.DragAndDrop_table;
import books.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Features.SendEmail;


public class User {

    //===================================== ATRIBUT ======================================





    //====================================== METHOD =======================================

    //Method yang digunakan untuk meminjam buku
    public void choiceBooks() {
        Book bookObj = new Book();
        Student studentObj = new Student();

        //Window Label
        Stage choiceBooksStage = new Stage();
        choiceBooksStage.setTitle("UMM library - Pilih Buku");

        //Label
        Label headerTitle   = new Label("PINJAM BUKU");
        Label bookIdLabel   = new Label("Input ID buku yang ingin dipinjam:");
        Label durationLabel = new Label("Berapa hari ingin meminjam buku?");

        //Notification Label
        Label borrowBookFailedLabel  = new Label("Max 14 hari");
        Label borrowBookSuccesLabel  = new Label("Buku berhasil dipinjam");
        Label bookStockEmptyLabel    = new Label("Stok buku habis");
        Label idNotFoundLabel        = new Label("ID buku tidak ditemukan");
        Label waitLabel              = new Label("Mohon tunggu");

        //Field
        TextField bookIdField = new TextField();
        TextField durationField = new TextField();

        //Field fill text
        durationField.setPromptText("Max 14 hari");

        //Font Style
        headerTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        borrowBookFailedLabel.setFont(Font.font("Calibri Body", FontWeight.BOLD, 15));
        borrowBookSuccesLabel.setFont(Font.font("Calibri Body", FontWeight.BOLD, 15));
        bookStockEmptyLabel.setFont(Font.font("Calibri Body", FontWeight.BOLD, 15));
        idNotFoundLabel.setFont(Font.font("Calibri Body", FontWeight.BOLD, 15));
        waitLabel.setFont(Font.font("Calibri Body", FontWeight.BOLD, 15));

        //Font Color
        headerTitle.setStyle("-fx-text-fill: #A91D3A;");
        borrowBookSuccesLabel.setStyle("-fx-text-fill: #1A4D2E;");
        borrowBookFailedLabel.setStyle("-fx-text-fill: #FF1E1E;");
        bookStockEmptyLabel.setStyle("-fx-text-fill: #FF1E1E;");
        idNotFoundLabel.setStyle("-fx-text-fill: #FF1E1E;");
        waitLabel.setStyle("-fx-text-fill: #FF8F00;");

        //Button
        Button submitButton = new Button("Submit");
        Button returnButton = new Button("Kembali");

        //Table
        TableView<Book> tableView = new TableView<>();

        //Table column label
        TableColumn<Book, String> idColumn       = new TableColumn<>("ID Buku");
        TableColumn<Book, String> titleColumn    = new TableColumn<>("Nama Buku");
        TableColumn<Book, String> authorColumn   = new TableColumn<>("Penulis");
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        TableColumn<Book, Integer> stockColumn   = new TableColumn<>("Stok");

        //Table header fill
        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(titleColumn);
        tableView.getColumns().add(authorColumn);
        tableView.getColumns().add(categoryColumn);
        tableView.getColumns().add(stockColumn);

        //Table column fill
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //Table acces array book
        for (Book i : Book.arr_bookList) {
            tableView.getItems().add(i);

        }

        //Notification settings
        borrowBookFailedLabel.setVisible(false);
        borrowBookSuccesLabel.setVisible(false);
        bookStockEmptyLabel.setVisible(false);
        idNotFoundLabel.setVisible(false);
        waitLabel.setVisible(false);

        //Grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(headerTitle,0,0);
        grid.add(tableView,0,1);

        grid.add(bookIdLabel,  0, 2);
        grid.add(bookIdField, 0, 3);

        grid.add(durationLabel,0,4);

        grid.add(durationField, 0,5);

        grid.add(submitButton,0,6);
        grid.add(returnButton,0,6);

        grid.add(borrowBookFailedLabel,0,6);
        grid.add(borrowBookSuccesLabel,0,6);
        grid.add(bookStockEmptyLabel, 0,6);
        grid.add(idNotFoundLabel,0,6);
        grid.add(waitLabel,0,6);

        grid.setHgap(5);
        grid.setVgap(10);

        headerTitle.setTranslateX(131);
        submitButton.setTranslateX(348);
        borrowBookFailedLabel.setTranslateX(151);
        borrowBookSuccesLabel.setTranslateX(120);
        bookStockEmptyLabel.setTranslateX(135);
        idNotFoundLabel.setTranslateX(120);
        waitLabel.setTranslateX(140);

        Scene scene = new Scene(grid);
        choiceBooksStage.setFullScreen(true);
        choiceBooksStage.setFullScreenExitHint("");
        choiceBooksStage.setScene(scene);
        choiceBooksStage.show();


        //Action
        idColumn.setCellFactory(event -> new DragAndDrop_table(bookIdField));

        submitButton.setOnAction(event -> {
            for (Book i : Book.arr_bookList) {
                if (i.getBookId().equals(bookIdField.getText())) {
                    if (i.getStock() != 0) {
                        int inputwaktuPinjaman = Integer.parseInt(durationField.getText());

                        if(inputwaktuPinjaman != 0 && inputwaktuPinjaman <= 14 ) {
                            int a = i.getStock();
                            a--;
                            i.setStock(a);
                            bookObj.setDuration(inputwaktuPinjaman);
                            Book.arr_borrowedBook.add(new Book(bookIdField.getText(), i.getStock(), bookObj.getDuration()));


                            SendEmail snobj = new SendEmail();
                            String recipientEmail = "nframzi051@gmail.com"; // Replace with the recipient's email
                            String subject = "Peminjaman Buku Berhasil!";
                            String body ="Terimakasih telah berkunjung ke perpustakaan pusat UMM.\n"
                                        + "Berikut lampiran tentang buku yang telah dipinjam :\n"
                                        + "\nBook ID    : " + i.getBookId()  + "\n"
                                        + "Title          : " + i.getTitle() + "\n"
                                        + "Category  : " + i.getCategory() + "\n"
                                        + "Duration of borrowing : " + inputwaktuPinjaman + " days\n"+"\n"
                                        +snobj.dateinfo();


                             waitLabel.setVisible(true);
                            borrowBookFailedLabel.setVisible(false);
                            borrowBookSuccesLabel.setVisible(false);
                            bookStockEmptyLabel.setVisible(false);
                            idNotFoundLabel.setVisible(false);

                            // Hide the wait label and show the success label when the email task is complete
                            Platform.runLater(() -> {
                                snobj.sendEmail(recipientEmail, subject, body);
                                tableView.refresh();
                                waitLabel.setVisible(false);
                                borrowBookFailedLabel.setVisible(false);
                                borrowBookSuccesLabel.setVisible(true);
                                bookStockEmptyLabel.setVisible(false);
                                idNotFoundLabel.setVisible(false);

                            });

                        break;
                        }else{
                            borrowBookFailedLabel.setVisible(true);
                            borrowBookSuccesLabel.setVisible(false);
                            bookStockEmptyLabel.setVisible(false);
                            idNotFoundLabel.setVisible(false);
                            break;
                        }
                    }else{
                        borrowBookFailedLabel.setVisible(false);
                        borrowBookSuccesLabel.setVisible(false);
                        bookStockEmptyLabel.setVisible(true);
                        idNotFoundLabel.setVisible(false);
                        break;
                    }
                }else {
                    borrowBookFailedLabel.setVisible(false);
                    borrowBookSuccesLabel.setVisible(false);
                    bookStockEmptyLabel.setVisible(false);
                    idNotFoundLabel.setVisible(true);
                }

            }
        });
        returnButton.setOnAction(event -> {
            studentObj.menu();
            choiceBooksStage.close();
        });
    }

    public void inputBook() {
        Book  textBookObj    = new TextBook();
        Book  storyBookObj   = new StoryBook();
        Book  historyBookObj = new HistoryBook();

        Stage inputBookStage = new Stage();
        inputBookStage.setTitle("UMM Library - Input Book");

        //Label
        Label sceneTitle = new Label("Tambah Buku");
        Label chooseBook = new Label("Pilih kategori buku:");

        //Font Label style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        chooseBook.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        //Font label color
        sceneTitle.setStyle("-fx-text-fill: #A91D3A;");

        //Button
        Button historyBookButton  = new Button("History Book");
        Button storyBookButton    = new Button("Story Book");
        Button textBookButton     = new Button("Text Book");

        //Grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 0,0);
        grid.add(chooseBook, 0,1);
        grid.add(historyBookButton, 2, 0 );
        grid.add(storyBookButton, 2, 1 );
        grid.add(textBookButton, 2, 2 );

        grid.setVgap(10);
        grid.setHgap(5);

        Scene scene = new Scene(grid);
        inputBookStage.setFullScreen(true);
        inputBookStage.setFullScreenExitHint("");
        inputBookStage.setScene(scene);
        inputBookStage.show();


        //Action Button
        historyBookButton.setOnAction(event -> {
            addBook(historyBookObj, "UMM Library - Add History Book ", "History Book");
            inputBookStage.close();
        });

        storyBookButton.setOnAction(event -> {
            addBook(storyBookObj, "UMM Library - Add Story Book ", "Story Book");
            inputBookStage.close();
        });

        textBookButton.setOnAction(event -> {
            addBook(textBookObj, "UMM Library - Add Text Book", "Text Book");
            inputBookStage.close();
        });

    }
    public void addBook(Book genreBook, String addBookStageTitle, String addBookSceneTitle) {
        Admin adminObj       = new Admin();
        Book  bookObj        = new Book();

        Stage addbookStage = new Stage();
        addbookStage.setTitle(addBookStageTitle);

        //Label
        Label sceneTitleLabel= new Label(addBookSceneTitle);
        Label bookIdLabel    = new Label("ID Buku    :");
        Label bookTitleLabel = new Label("Judul Buku :");
        Label authorLabel    = new Label("Penulis    :");
        Label stockLabel     = new Label("Stok       :");

        //Notification Label
        Label errorMessageLabel = new Label("Stok harus berupa angka");

        //Field
        TextField bookIdField    = new TextField(adminObj.generateId());
        TextField bookTitleField =  new TextField();
        TextField authorField    = new TextField();
        TextField stockField     = new TextField();

        //Font label style
        sceneTitleLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        bookIdLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        bookTitleLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        authorLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        stockLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        //Font label color
        sceneTitleLabel.setStyle("-fx-text-fill: #A91D3A;");
        errorMessageLabel.setStyle("-fx-text-fill: #FF1E1E;");

        //Font visible settings
        errorMessageLabel.setVisible(false);

        //Button
        Button submitButton = new Button("Submit");
        Button returnButton = new Button("Kembali");

        //Grid layout
        GridPane gridAddBook = new GridPane();
        gridAddBook.setAlignment(Pos.CENTER);

        gridAddBook.add(sceneTitleLabel, 1,0);
        gridAddBook.add(bookIdLabel, 0,1);
        gridAddBook.add(bookTitleLabel,0,2);
        gridAddBook.add(authorLabel, 0,3);
        gridAddBook.add(stockLabel, 0,4);
        gridAddBook.add(errorMessageLabel, 0, 5);

        gridAddBook.add(bookIdField,2,1);
        gridAddBook.add(bookTitleField, 2,2);
        gridAddBook.add(authorField, 2,3);
        gridAddBook.add(stockField,2,4);

        gridAddBook.add(submitButton,2,5);


        Scene addbookScene = new Scene(gridAddBook,1360,720);
        addbookStage.setScene(addbookScene);
        addbookStage.show();

        //Action button
        submitButton.setOnAction(event -> {
            //add book to array
            try {
                errorMessageLabel.setVisible(false);

                bookObj.setBookId(bookIdField.getText());
                bookObj.setTitle(bookTitleField.getText());
                genreBook.setCategory(sceneTitleLabel.getText());
                bookObj.setAuthor(authorField.getText());
                bookObj.setStock(Integer.parseInt(stockField.getText()));

                Book.arr_bookList.add(new Book(bookObj.getBookId(), bookObj.getTitle(), bookObj.getAuthor(), genreBook.getCategory(), bookObj.getStock()));

                adminObj.menu();
                addbookStage.close();
            }catch (NumberFormatException message){
                errorMessageLabel.setVisible(true);
                addbookStage.show();
            }
        });

    }
}


