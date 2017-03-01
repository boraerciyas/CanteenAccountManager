/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteenaccountmanager;

/**
 *
 * @author yunusbora
 */
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFXMultiColumnChart {

    public class Record{
        private final SimpleStringProperty fieldPersons;
        private final SimpleDoubleProperty fieldPersonalTotal;
        private final SimpleDoubleProperty fieldEnterance;
        
        Record(String fPersons, double fPersonalTotal, double fEnterance){
            this.fieldPersons = new SimpleStringProperty(fPersons);
            this.fieldPersonalTotal = new SimpleDoubleProperty(fPersonalTotal);
            this.fieldEnterance = new SimpleDoubleProperty(fEnterance);
        }
        
        public String getFieldMonth() {
            return fieldPersons.get();
        }
        
        public double getFieldValue1() {
            return fieldPersonalTotal.get();
        }
        
        public double getFieldValue2() {
            return fieldEnterance.get();
        }
        
        public void setFieldMonth(String fPersons) {
            fieldPersons.set(fPersons);
        }
     
        public void setFieldValue1(Double fPersonalTotal) {
            fieldPersonalTotal.set(fPersonalTotal);
        }
        
        public void setFieldValue2(Double fEnterance) {
            fieldEnterance.set(fEnterance);
        }
    }
    
    private final TableView<Record> tableView = new TableView<>();
    
    private final ObservableList<Record> dataList =
        FXCollections.observableArrayList(
            new Record("Tarık Bey", 100, 120),
            new Record("Jankiz", 200, 210),
            new Record("Evrim", 50, 70),
            new Record("Eda Abla", 20, 0));
    
    public TableView getTables() {
        
        tableView.setEditable(true);

        Callback<TableColumn, TableCell> cellFactory = 
                (TableColumn p) -> new EditingCell();
        
        TableColumn columnPersons = new TableColumn("Kişiler");
        columnPersons.setCellValueFactory(
                new PropertyValueFactory<>("fieldPersons"));
        columnPersons.setMinWidth(60);
        
        TableColumn columnPersonalTotal = new TableColumn("Toplam");
        columnPersonalTotal.setCellValueFactory(
                new PropertyValueFactory<>("fieldPersonalTotal"));
        columnPersonalTotal.setMinWidth(60);
        
        TableColumn columnEnterance = new TableColumn("Giriş");
        columnEnterance.setCellValueFactory(
                new PropertyValueFactory<>("fieldEnterance"));
        columnEnterance.setMinWidth(60);
     
        //--- Add for Editable Cell of Value field, in Double
        columnPersonalTotal.setCellFactory(cellFactory);
        columnPersonalTotal.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Record, Double>>() {
                    
                    @Override public void handle(TableColumn.CellEditEvent<Record, Double> t) {
                        ((Record)t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setFieldValue1(t.getNewValue());
                    }
                });
        
        columnEnterance.setCellFactory(cellFactory);
        columnEnterance.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Record, Double>>() {
                    
                    @Override public void handle(TableColumn.CellEditEvent<Record, Double> t) {
                        ((Record)t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setFieldValue2(t.getNewValue());
                    }
                });
      
        //---
        tableView.setItems(dataList);
        tableView.getColumns().addAll(columnPersons, columnPersonalTotal, columnEnterance);
        
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(tableView);
 
        return tableView;
    }

    class EditingCell extends TableCell<Record, Double> {
        private TextField textField;
        
        public EditingCell() {}
        
        @Override
        public void startEdit() {
            super.startEdit();
            
            if (textField == null) {
                createTextField();
            }
            
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }
        
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            
            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        
        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);
         
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }
        
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(Double.parseDouble(textField.getText()));
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });
        }
     
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    
}
