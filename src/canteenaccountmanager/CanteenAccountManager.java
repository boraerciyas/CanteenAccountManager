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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author ybe
 */
public class CanteenAccountManager extends Application {

    Image imgBora = new Image("img//programmer_vs_hacker.jpg");
    Image imgKaan = new Image("img//chemistry-cat.jpg");
    Image imgDebtButton = new Image("img//debt_icon.jpg");
    Button debtButton = new Button();
        
    @Override
    public void start(Stage stage) {
        
        Scene scene = LoginDesign();
        scene.getStylesheets().add("canteenaccountmanager//Design.css");
        stage.setTitle("Canteen Account Manager");
        stage.setScene(scene);
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    private Scene LoginDesign() {        
        
        debtButton.setText("Hesaplar");
        debtButton.setGraphic(new ImageView(imgDebtButton));
        debtButton.setGraphicTextGap(5.0);
        debtButton.applyCss();
        debtButton.setId("shiny-orange");
        
        Button addAccountButton = new Button("Kullanıcı Ekle");
        debtButton.setTooltip(new Tooltip("Borç Hesaplarını Görüntülemek ve Düzenlemek için Tıklayınız"));
        addAccountButton.setTooltip(new Tooltip("Yeni Bir Kullanıcı Eklemek İçin Tıklayınız"));
        
        HBox hboxDept = new HBox(0);
        hboxDept.setAlignment(Pos.BASELINE_RIGHT);
        hboxDept.getChildren().add(debtButton);
        
        ImageView ivB = new ImageView(imgBora);
        ivB.setFitHeight(200);
        ivB.setFitWidth(200);
        
        ImageView ivK = new ImageView(imgKaan);
        ivK.setFitHeight(200);
        ivK.setFitWidth(200);
        
        Button boraButton = new Button(null, ivB);
        boraButton.applyCss();
        boraButton.setId("shiny-orange");
        Button kaanButton = new Button(null, ivK);
        kaanButton.applyCss();
        kaanButton.setId("shiny-orange");
        
        HBox hboxUser = new HBox(1);
        hboxUser.setSpacing(25);
        hboxUser.alignmentProperty().set(Pos.CENTER);
        hboxUser.getChildren().addAll(boraButton, kaanButton);
        
        VBox vbox = new VBox(2);
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hboxDept, hboxUser, addAccountButton);
        
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        
        Scene S1 = new Scene(root, 600, 450);
        return S1;
    }
}

