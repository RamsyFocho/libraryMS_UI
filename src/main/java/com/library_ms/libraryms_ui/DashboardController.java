package com.library_ms.libraryms_ui;

import com.sun.jdi.Value;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;

import java.util.Objects;

public class DashboardController {
//    add category
    @FXML
    private TextField txtCategory;
    @FXML private TableView<Value> tableCategory;
    @FXML private TableColumn<Value, String> columnCategoryId;
    @FXML private TableColumn<Value, String> columnCategory;
    private ObservableList<Value> data;

    @FXML public void initialize() {
        data = FXCollections.observableArrayList();
        columnCategoryId.setCellValueFactory(new PropertyValueFactory<>("firstValue"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("secondValue"));
        tableCategory.setItems(data);
    }
    int Id=0;
    @FXML
    protected void addCategory(){
        // Add new category to the table
        Id++;
        String category = txtCategory.getText();
        if (Id != 0 && category != null && !category.trim().isEmpty()) {
            data.add(new Value(Id, category));
            Objects.equals(txtCategory.getText(), "");
        }

    }
    public static class Value {
        private final int firstValue;
        private final String secondValue;
        public Value(int firstValue, String secondValue) {
            this.firstValue = firstValue;
            this.secondValue = secondValue;
        }
        public int getFirstValue() {
            return firstValue;
        }
        public String getSecondValue() {
            return secondValue;
        }
    }

    // add book

}
