package com.library_ms.libraryms_ui;

import com.library_ms.libraryms_ui.domain.Book;
import com.library_ms.libraryms_ui.domain.Category;
import com.library_ms.libraryms_ui.storage.BookStorage;
import com.library_ms.libraryms_ui.storage.CategoryStorage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;

public class DashboardController {
//    add category
    @FXML
    private TextField txtCategory;
    @FXML private TableView<categoryValue> tableCategory;
    @FXML private TableColumn<categoryValue, String> columnCategoryId;
    @FXML private TableColumn<categoryValue, String> columnCategory;
    private ObservableList<categoryValue> categoryData;

    private ObservableList<bookValue> bookData;
    @FXML private TableView<bookValue> tableBook;
    @FXML private TableColumn<bookValue, String> columnBookCategoryId;
    @FXML private TableColumn<bookValue, String> columnBookName;
    @FXML private TableColumn<bookValue, String> columnQty;
    @FXML private TableColumn<bookValue, String> columnBookId;

    @FXML
    private ComboBox<String> cmbCategory;
    @FXML
    private Button btnUpdate;
    @FXML private Button btnInsertCategory;

    private Integer categoryIdForUpdate = 0;


    @FXML public void initialize() {
        categoryData = FXCollections.observableArrayList();
        columnCategoryId.setCellValueFactory(new PropertyValueFactory<>("firstValue"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("secondValue"));
        tableCategory.setItems(categoryData);

        // Add a listener for row selection
        tableCategory.setRowFactory(tv -> {
            TableRow<categoryValue> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    categoryValue rowData = row.getItem();
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

//        book tab
        bookData = FXCollections.observableArrayList();

        columnBookId.setCellValueFactory(new PropertyValueFactory<>("firstValue"));
        columnBookName.setCellValueFactory(new PropertyValueFactory<>("secondValue"));
        columnBookCategoryId.setCellValueFactory(new PropertyValueFactory<>("thirdValue"));
        columnQty.setCellValueFactory(new PropertyValueFactory<>("fourthValue"));
        tableBook.setItems(bookData);
    }
//    -----------------------FOR CATEGORY TAB------------------------------------
    int categoryId=0;
    CategoryStorage cs = new CategoryStorage();
    @FXML
    protected void addCategory(){
        // Add new category to the table
        categoryId++;
        String category = txtCategory.getText();
        if (categoryId != 0 && category != null && !category.trim().isEmpty()) {
            Category newCategory = new Category(categoryId,category);
            boolean isPresent = cs.addCategory(newCategory);
            if (isPresent){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Library_UI");
                alert.setHeaderText("Category already existing");
                alert.setContentText("Please change category");

                alert.showAndWait();
                return;
            }
            categoryData.add(new categoryValue(categoryId, category));
            txtCategory.setText("");
//            add in the combo box
            refreshCategoryComboBox();
        }

    }
    private void refreshCategoryComboBox() {
        cmbCategory.getItems().clear(); // Clear existing items
        for (categoryValue value : categoryData) {
            cmbCategory.getItems().add(value.getSecondValue()); // Add updated values
        }
    }

    @FXML
    protected void onClickUpdate(){
       boolean updated = cs.updateCategory(categoryIdForUpdate,txtCategory.getText());
       if(updated){
           for (categoryValue value : categoryData) {
               if (value.getFirstValue() == categoryIdForUpdate) {
                   categoryData.remove(value); // Remove the old entry
                   categoryData.add(new categoryValue(categoryIdForUpdate, txtCategory.getText())); // Add the updated entry
                   break;
               }
           }

           // Sort the categoryData list by category ID (firstValue)
           categoryData.sort((v1, v2) -> Integer.compare(v1.getFirstValue(), v2.getFirstValue()));
           // Refresh the TableView to reflect changes
           tableCategory.refresh();
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
    @FXML
    protected void onClickDelete(){
//        TODO
    }
    public static class categoryValue {
        private final int firstValue;
        private final String secondValue;
        public categoryValue(int firstValue, String secondValue) {
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

//    -------------------------BOOK TAB--------------------------------------
    public static class bookValue {
        private final int firstValue;
        private final String secondValue;
        private final int thirdValue;
        private final int fourthValue;

        public bookValue(int firstValue, String secondValue, int thirdValue, int fourthValue) {
            this.firstValue = firstValue;
            this.secondValue = secondValue;
            this.thirdValue = thirdValue;
            this.fourthValue = fourthValue;
        }
        public int getFirstValue() {
            return firstValue;
        }
        public String getSecondValue() {
            return secondValue;
        }

        public int getThirdValue() {
            return thirdValue;
        }

        public int getFourthValue() {
            return fourthValue;
        }
    }
    @FXML private TextField txtBookName;
    @FXML private TextField txtQty;
    int bookId;
    @FXML protected void onClickAddBook(){
//        TODO: check if qty is an integer or not
//        find the category by name first
        bookId++;
        System.out.println("The tt is "+cmbCategory.getValue());
        Category category = CategoryStorage.findCategoryByName(cmbCategory.getValue());
        if(category!=null){
            Book bk= new Book(bookId,txtBookName.getText(),category,Integer.parseInt(txtQty.getText()));
            BookStorage.addBook(bk);
            bookData.add(new bookValue(bookId,txtBookName.getText(),category.getId(),Integer.parseInt(txtQty.getText())));

        }
//        System.out.println("found category");
    }


}
