package com.library_ms.libraryms_ui;

import com.library_ms.libraryms_ui.domain.Category;
import com.library_ms.libraryms_ui.storage.CategoryStorage;
import com.sun.jdi.Value;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private ComboBox<String> cmbCategory;
    @FXML
    private Button btnUpdate;
    @FXML private Button btnInsertCategory;

    private Integer categoryIdForUpdate = 0;


    @FXML public void initialize() {
        data = FXCollections.observableArrayList();
        columnCategoryId.setCellValueFactory(new PropertyValueFactory<>("firstValue"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("secondValue"));
        tableCategory.setItems(data);

        // Add a listener for row selection
        tableCategory.setRowFactory(tv -> {
            TableRow<Value> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Value rowData = row.getItem();
                    txtCategory.setText(rowData.getSecondValue());
                    categoryIdForUpdate = rowData.getFirstValue();
                    System.out.println("The ID is " + categoryIdForUpdate);
                    btnUpdate.setDisable(false);
                    btnUpdate.setVisible(true);

                    btnInsertCategory.setDisable(true);
                    btnInsertCategory.setVisible(false);

                }
            });
            return row; // Return the row instead of null
        });

    }
    int Id=0;
    CategoryStorage cs = new CategoryStorage();
    @FXML
    protected void addCategory(){
        // Add new category to the table
        Id++;
        String category = txtCategory.getText();
        if (Id != 0 && category != null && !category.trim().isEmpty()) {
            Category newCategory = new Category(Id,category);
            cs.addCategory(newCategory);
            data.add(new Value(Id, category));
            txtCategory.setText("");
//            add in the combo box
            refreshCategoryComboBox();
        }

    }
    private void refreshCategoryComboBox() {
        cmbCategory.getItems().clear(); // Clear existing items
        for (Value value : data) {
            cmbCategory.getItems().add(value.getSecondValue()); // Add updated values
        }
    }

    @FXML
    protected void onClickUpdate(){
       boolean updated = cs.updateCategory(categoryIdForUpdate,txtCategory.getText());
       if(updated){
           for (Value value : data) {
               if (value.getFirstValue() == categoryIdForUpdate) {
                   data.remove(value); // Remove the old entry
                   data.add(new Value(categoryIdForUpdate, txtCategory.getText())); // Add the updated entry
                   break;
               }
           }

           // Sort the data list by category ID (firstValue)
           data.sort((v1, v2) -> Integer.compare(v1.getFirstValue(), v2.getFirstValue()));
           // Refresh the TableView to reflect changes
           tableCategory.refresh();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Updated Successful");
           alert.setHeaderText("Welcome");
           alert.setContentText("You have successfully updated");
           alert.showAndWait();

           btnUpdate.setDisable(true);
           btnUpdate.setVisible(false);

           btnInsertCategory.setDisable(false);
           btnInsertCategory.setVisible(true);
           refreshCategoryComboBox();
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Not Successful");
           alert.setHeaderText("Error");
           alert.setContentText("some issues with updating...");
           alert.showAndWait();
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
