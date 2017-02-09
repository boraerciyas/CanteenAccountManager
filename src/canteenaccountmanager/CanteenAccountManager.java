/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteenaccountmanager;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ybe
 */
public class CanteenAccountManager extends Application {

    @Override
    public void start(Stage stage) {
        
        Scene scene = LoginDesign();
        stage.setTitle("Canteen Account Manager");
        stage.setScene(scene);
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    private Scene LoginDesign() {        
        Image imgBora = new Image("img//programmer_vs_hacker.jpg");
        Image imgKaan = new Image("img//chemistry-cat.jpg");
        System.out.println(imgBora.getHeight());
        Button deptButton = new Button("Hesaplar");
        Button addAccountButton = new Button("Kullanıcı Ekle");
        deptButton.setTooltip(new Tooltip("Borç Hesaplarını Görüntülemek ve Düzenlemek için Tıklayınız"));
        addAccountButton.setTooltip(new Tooltip("Yeni Bir Kullanıcı Eklemek İçin Tıklayınız"));
        
        Button boraButton = new Button(null, new ImageView(imgBora));
        Button kaanButton = new Button(null, new ImageView(imgKaan));
        
        
        boraButton.setPrefWidth(175);
        kaanButton.setPrefHeight(175);
        kaanButton.setPrefWidth(175);
        
        HBox hbox = new HBox(1);
        hbox.setSpacing(15);
        hbox.alignmentProperty().set(Pos.CENTER);
        hbox.getChildren().addAll(boraButton, kaanButton);
        
        VBox vbox = new VBox(2);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(deptButton, hbox, addAccountButton);
        
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        
        Scene S1 = new Scene(root, 600, 400);
        return S1;
    }
}

