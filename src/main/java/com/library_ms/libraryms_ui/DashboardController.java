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
    @FXML private TableColumn<bookValue, Integer> columnQty;
    @FXML private TableColumn<bookValue, String> columnBookId;

    @FXML
    private ComboBox<String> cmbCategory;
    @FXML
    private Button btnUpdate;
    @FXML private Button btnInsertCategory;

    private Integer categoryIdForUpdate = 0;
    private Integer bookIdForUpdate = 0;
//    private Integer categoryIdForUpdate = 0;
//    private Integer categoryIdForUpdate = 0;


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

        // Add a listener for row selection
        toggleBookButtons(true);
        onSelectRow();
    }

    private void onSelectRow() {
        tableBook.setRowFactory(tv -> {
            TableRow<bookValue> bookRow = new TableRow<>();
            bookRow.setOnMouseClicked(event -> {
                if (!bookRow.isEmpty()) {
                    bookValue rowData = bookRow.getItem();
                    txtBookName.setText(rowData.getSecondValue());
                    bookIdForUpdate = rowData.getFirstValue();
                    categoryIdForUpdate = rowData.getThirdValue();
                    displayCategoryById(categoryIdForUpdate);
                    txtQty.setText(String.valueOf(rowData.getFourthValue()));
                    System.out.println("The ID is " + categoryIdForUpdate);
                    toggleBookButtons(false);
                }
            });
            return bookRow; // Return the row instead of null
        });
    }


    //    -----------------------FOR CATEGORY TAB------------------------------------
    int categoryId=0;
    CategoryStorage cs = new CategoryStorage();
    @FXML
    protected void addCategory(){
        // Add new category to the table
        String category = txtCategory.getText();
        categoryId++;
        if (categoryId != 0 && category != null && !category.trim().isEmpty()) {
            Category newCategory = new Category(categoryId,category);
            boolean isPresent = cs.addCategory(newCategory);
            if (isPresent){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Library_UI");
                alert.setHeaderText("Category already existing");
                alert.setContentText("Please change category");
                categoryId--;
                alert.showAndWait();
                return;
            }
            categoryData.add(new categoryValue(categoryId, category));
            txtCategory.setText("");
//            add in the combo box
            refreshCategoryComboBox();
        }else{
            categoryId--;
        }

    }
    private void refreshCategoryComboBox() {
        cmbCategory.getItems().clear(); // Clear existing items
        for (categoryValue value : categoryData) {
            cmbCategory.getItems().add(value.getSecondValue()); // Add updated values
        }
    }
    @FXML
    private ComboBox<String> cmbBookName;
    private void refreshBookComboBox() {
        cmbBookName.getItems().clear(); // Clear existing items
        for (bookValue value : bookData) {
            cmbBookName.getItems().add(value.getSecondValue()); // Add updated values
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
        System.out.println("The category is "+cmbCategory.getValue());
        Category category = CategoryStorage.findCategoryByName(cmbCategory.getValue());
        if(category!=null){
            bookId++;
            if(isInteger(txtQty.getText()) || txtBookName.getText().isEmpty()){
                Book bk= new Book(bookId,txtBookName.getText(),category,Integer.parseInt(txtQty.getText()));
                BookStorage.addBook(bk);
                bookData.add(new bookValue(bookId,txtBookName.getText(),category.getId(),Integer.parseInt(txtQty.getText())));
                resetBooksValue();
                refreshBookComboBox();
            }else {
                bookId--;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Library_UI");
                alert.setHeaderText("Wrong Input format");
                alert.setContentText("Please check your input type. Make sure they're valid");

                alert.showAndWait();
                return;
            }

        }
//        System.out.println("found category");
    }
    @FXML protected void onClickUpdateBook(){
        Category category = CategoryStorage.findCategoryByName(cmbCategory.getValue());
        if(isInteger(txtQty.getText()) || txtBookName.getText().isEmpty()){
            boolean updated = BookStorage.updateBook(bookIdForUpdate,txtBookName.getText(), category, Integer.parseInt(txtQty.getText()));
            if(!updated){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Library_UI");
                alert.setHeaderText("Error with updating");
                alert.setContentText("An error occurred while updating... \n Check the Logs!!!");

                alert.showAndWait();
                return;
            }else{
                toggleBookButtons(true);
                for (bookValue value : bookData) {
                    if (value.getFirstValue() == bookIdForUpdate) {
                        bookData.remove(value); // Remove the old entry
                        bookData.add(new bookValue(bookIdForUpdate, txtBookName.getText(),categoryIdForUpdate,Integer.parseInt(txtQty.getText()))); // Add the updated entry
                        break;
                    }
                }

                // Sort the categoryData list by category ID (firstValue)
                bookData.sort((v1, v2) -> Integer.compare(v1.getFirstValue(), v2.getFirstValue()));
                // Refresh the TableView to reflect changes
                tableBook.refresh();
                refreshBookComboBox();
                resetBooksValue();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Library_UI");
            alert.setHeaderText("Wrong Input format");
            alert.setContentText("Please check your input type. Make sure they're valid");

            alert.showAndWait();
            return;
        }
    }

    private void resetBooksValue() {
        txtBookName.setText("");
        txtQty.setText("");
        cmbCategory.setValue("");
        cmbCategory.setPromptText("choose category");
    }
    @FXML Button btnUpdateBook;
    @FXML Button btnInsertBook;

    public void toggleBookButtons(Boolean add){
        if(add){
            btnUpdateBook.setVisible(false);
            btnUpdateBook.setDisable(true);

            btnInsertBook.setVisible(true);
            btnInsertBook.setDisable(false);
        }else {
            btnUpdateBook.setVisible(true);
            btnUpdateBook.setDisable(false);

            btnInsertBook.setVisible(false);
            btnInsertBook.setDisable(true);
        }
    }

    public boolean isInteger(String Input){
        try{
            Integer.parseInt(Input);
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }
    private void displayCategoryById(Integer categoryIdForUpdate) {
        Category cs = CategoryStorage.findCategorybyId(categoryIdForUpdate);
        if(cs != null) {
            cmbCategory.setValue(cs.getCategory());
        }
    }


}
