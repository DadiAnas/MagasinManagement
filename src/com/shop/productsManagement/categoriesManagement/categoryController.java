package com.shop.productsManagement.categoriesManagement;


import com.shop.SceneController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class categoryController implements Initializable {
    SceneController sceneController= SceneController.getSceneController();
    //buttons
    @FXML
    Button buttonDelete;
    // Text fields
    @FXML
    TextField fieldId;
    @FXML
    TextField fieldName;

    //Category comboBox
    @FXML
    ComboBox<String> categoryComboBox=new ComboBox<>();

    // Product Array
    @FXML
    TableView<Category> categoryTable;
    ObservableList<Category> observableArray = FXCollections.observableArrayList();
    @FXML
    TableColumn<Category, Long> categoryCodeColumn;
    @FXML
    TableColumn<Category, Long> categoryNameColumn;

    // search text field
    @FXML
    TextField textKeyWord;


    CategoryDaoImpl cDAO = new CategoryDaoImpl();
    List<Category> categories = cDAO.getAll();
    // label: Total
    @FXML
    Label labelTotal;

    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCategoryComboBox();
        initTable();
    }

    private void initCategoryComboBox(){
        for (Category category:categories){
            categoryComboBox.getItems().add(category.getCategoryName());
        }
    }
    private void initTable(){
        categoryCodeColumn.setCellValueFactory(new PropertyValueFactory<>("categoryCode"));
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        refreshcategoryTable();
    }




    private  void refreshcategoryTable(){
        fieldId.clear();
        fieldName.clear();

        List<Category> categoryList =cDAO.getAll();
        observableArray.setAll(categoryList);
        categoryTable.setItems(observableArray);

    }
    @FXML
    public void search(){
        List<Category> categoryList =cDAO.getAll(textKeyWord.getText());
        observableArray.setAll(categoryList);
        categoryTable.setItems(observableArray);
    }
    @FXML
    public void addCategory(){
        if(fieldId.getLength() !=0 && fieldName.getLength()!=0) {
            Category category= new Category(Long.parseLong(fieldId.getText()),fieldName.getText());
            cDAO.add(category);
            refreshcategoryTable();
        }
        else {
            alert.setTitle("Input Error");
            alert.setHeaderText("Veuillez remplir tous les champs");
            //alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        }
    }
    @FXML
    public void updateCategory(){
        long categoryCode = Long.valueOf(fieldId.getText());
        String categoryName = fieldName.getText();

        Category category= new Category(categoryCode,categoryName);

        if(cDAO.save(category)!=null) {
            refreshcategoryTable();
        }
    }
    @FXML
    public void deleteCategory() {
        if(fieldId.getLength() != 0) {
            cDAO.delete(Long.valueOf(fieldId.getText()));
            buttonDelete.setDisable(true);
            refreshcategoryTable();
        }
    }


    @FXML
    public void FromListToFields(){
        try {
            buttonDelete.setDisable(false);
            Category category = categoryTable.getSelectionModel().getSelectedItem();
            fieldId.clear();
            fieldName.clear();
            fieldId.insertText(0, String.valueOf(category.getCategoryCode()));
            fieldName.insertText(0, String.valueOf(category.getCategoryName()));
        }catch(Exception exp){}
    }

    public void userManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("personScene");
    }

    public void categoryManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("categoryScene");
    }

    public void productManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("productScene");
    }

    public void bankManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("bankScene");
    }

    public void Signout(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("loginScene");
    }

    public void saleManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("saleScene");
    }

    public void overviewManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("overviewScene");
    }public void settingManagement(ActionEvent actionEvent) {

    }

}
