/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteenaccountmanager;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author ybe
 */
public class CanteenAccountManager extends Application {

    private final Image imgBora = new Image("img//programmer_vs_hacker.jpg");
    private final Image imgKaan = new Image("img//chemistry-cat.jpg");
    private final Image imgDebtButton = new Image("img//debt_icon.jpg");
    private final Image backPage = new Image("img//back_icon.png");
    private final Button debtButton = new Button();

    /**
     *
     */
    public final static ObservableList account_ObservableList = FXCollections.observableArrayList();
    public final static ObservableList debts_ObservableList = FXCollections.observableArrayList();
    private ListView debts_listView = new ListView();

    
        
    @Override
    public void start(Stage stage) {
        
        Scene scene = DebtPageDesign();
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
        addAccountButton.applyCss();
        addAccountButton.setId("shiny-orange");
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
        
        Text titleUser = new Text("Kullanıcılar");
        titleUser.setFill(Color.RED);
        titleUser.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        HBox hboxUser = new HBox(1);
        hboxUser.setSpacing(25);
        hboxUser.alignmentProperty().set(Pos.CENTER);
        hboxUser.getChildren().addAll(boraButton, kaanButton);
        
        VBox vbox = new VBox(3);
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hboxDept, titleUser, hboxUser, addAccountButton);
        
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        
        Scene scene = new Scene(root, 600, 500);
        return scene;
    }
    
    private Scene DebtPageDesign()  {
    	ImageView back = new ImageView(backPage);
    	back.setFitHeight(50);
    	back.setFitWidth(50);
    	Button backPageButton = new Button(null, back);
    	
    	Label title = new Label("Hesap Listesi");
    	title.fontProperty().setValue(Font.font("Arial", FontWeight.BOLD, 24));
    	title.setTextFill(Color.BLUE);
    	
    	GridPane gridTop = new GridPane();
    	gridTop.add(backPageButton, 0, 0);
    	gridTop.add(title, 1, 0);
    	gridTop.setPadding(new Insets(10, 5, 10, 5));
    	gridTop.setHgap(10);
    	title.setAlignment(Pos.CENTER_RIGHT);
    	backPageButton.setAlignment(Pos.BASELINE_LEFT);
        
        /******
        *
        *   ListView of the Accounts
        * 
        ******/        
        
        String[] test_array = new String[20];
        test_array[0] = "Evrim";
        test_array[1] = "Eda Abla";
        test_array[2] = "Tarık Bey";
        
        String[] test_debts = new String[20];
        test_debts[0] = "20.50";
        test_debts[1] = "74.25";
        test_debts[2] = "64.00";
        
        ListView<String> accounts_listView = new ListView<>();
        accounts_listView.setEditable(true);
        
        account_ObservableList.addAll(test_array);
        accounts_listView.setItems(account_ObservableList);
        
        debts_ObservableList.addAll(test_debts);
        debts_listView.setItems(debts_ObservableList);
        
        debts_listView.setCellFactory(new Callback<ListView,
                ListCell>(){
                @Override
                public ListCell<String> call(ListView p) {
                    return new TextFieldRectCell();
                } 
            });
        HBox box = new HBox(accounts_listView, debts_listView);

        BorderPane root = new BorderPane();
        root.setTop(gridTop);
        root.setCenter(box);
        Scene scene = new Scene(root, 500, 500);
        return scene;
    }

    //Özge Camel-Stationary Travaller
    
    private static class TextFieldRectCell extends ListCell<String> {

        @Override
        public void updateItem(String item, boolean empty)  {
            super.updateItem(item, empty);
                TextField tf = new TextField();
                if(item != null)   {
                tf.setText(item);
                    setGraphic(tf);
            }
        }                
    }
}

