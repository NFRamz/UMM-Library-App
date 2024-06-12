module Tugas_6_JavaFX{

        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.web;
        requires javafx.media;
        requires org.controlsfx.controls;
        requires com.dlsc.formsfx;
        requires net.synedra.validatorfx;
        requires org.kordamp.ikonli.javafx;
        requires org.kordamp.bootstrapfx.core;
        requires eu.hansolo.tilesfx;
        requires com.almasb.fxgl.all;
        requires java.mail;
        requires activation;


    opens books to javafx.base;
        exports books;

        opens sound to javafx.media;
        exports sound;

        opens com.main to javafx.fxml;
        exports com.main;

        }