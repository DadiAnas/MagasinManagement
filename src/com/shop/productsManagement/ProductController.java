package com.shop.productsManagement;


import com.shop.SceneController;
import com.shop.productsManagement.Product;
import com.shop.productsManagement.categoriesManagement.Category;
import com.shop.productsManagement.categoriesManagement.CategoryDaoImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.shop.productsManagement.ProductDaoImpl;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;


public class ProductController implements Initializable {
    SceneController sceneController= SceneController.getSceneController();
    //buttons
    @FXML
    Button buttonDelete;
    // Text fields
    @FXML
    TextField fieldId;
    @FXML
    TextField fieldDesignation;
    @FXML
    TextField fieldSalePrice;
    @FXML
    TextField fieldPurchasePrice;
    @FXML
    TextField fieldAmount;
    //Category comboBox
    @FXML
    ComboBox<String> categoryComboBox0=new ComboBox<>();
    @FXML
    ComboBox<String> categoryComboBox1=new ComboBox<>();
    // Product Array
    @FXML
    TableView<Product> productTable =new TableView<>();
    ObservableList<Product> observableArray = FXCollections.observableArrayList();
    @FXML
    TableColumn<Product, Long> productCodeColumn;
    @FXML
    TableColumn<Product, Long> productDesignationColumn;
    @FXML
    TableColumn<Product, Long> productSalePriceColumn;
    @FXML
    TableColumn<Product, Long> productPurchasePriceColumn;
    @FXML
    TableColumn<Product, Long> productAmountColumn;
    // search text field
    @FXML
    TextField textKeyWord;

    // Get Data
    ProductDaoImpl pDAO = new ProductDaoImpl();
    List<Product> products = pDAO.getAll();

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
    @FXML
    private void SearchByCategory(){
        Category category=cDAO.getByName(categoryComboBox1.getSelectionModel().getSelectedItem());
        observableArray.setAll(pDAO.getByCategory(category));
        productTable.setItems(observableArray);
    }
    private void initCategoryComboBox(){
        for (Category category:categories){
            categoryComboBox0.getItems().add(category.getCategoryName());
            categoryComboBox1.getItems().add(category.getCategoryName());
        }
    }
    private void initTable(){
        productCodeColumn.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        productDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        productSalePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        productPurchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        productAmountColumn.setCellValueFactory(new PropertyValueFactory<>("stockquantity"));
        refreshProductTable();
    }



    private  void refreshProductTable(){
        fieldId.clear();
        fieldSalePrice.clear();
        fieldPurchasePrice.clear();
        fieldDesignation.clear();
        fieldAmount.clear();
        if (categoryComboBox1.getSelectionModel().isEmpty()){
            List<Product> productList =pDAO.getAll();
            observableArray.setAll(productList);
            productTable.setItems(observableArray);
        }
        else SearchByCategory();
    }
    @FXML
    public void search(){
        products =pDAO.getAll(textKeyWord.getText());
        observableArray.setAll(products);
        productTable.setItems(observableArray);
    }
    @FXML
    public void addProduct(){
        if(fieldDesignation.getLength() !=0 && fieldSalePrice.getLength() !=0 && fieldPurchasePrice.getLength() !=0) {
            Category category=cDAO.getByName(categoryComboBox0.getSelectionModel().getSelectedItem());
            Product Prod = new Product(fieldDesignation.getText(), Double.valueOf(fieldPurchasePrice.getText()),Double.valueOf(fieldSalePrice.getText()),category,Integer.parseInt(fieldAmount.getText()));
            pDAO.add(Prod);
            refreshProductTable();
        }
        else {
            alert.setTitle("Input Error");
            alert.setHeaderText("Veuillez remplir tous les champs");
            //alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        }
    }
    @FXML
    public void updateProduct(){
        long id = Long.valueOf(fieldId.getText());
        String designation = fieldDesignation.getText();
        double salePrice = Double.valueOf(fieldSalePrice.getText());
        double purchasePrice = Double.valueOf(fieldSalePrice.getText());
        Category category= cDAO.getByName(categoryComboBox0.getSelectionModel().getSelectedItem());
        int stock = Integer.parseInt(fieldAmount.getText());
        Product product = new Product(id,designation ,purchasePrice,salePrice,category,stock);

        if(pDAO.save(product)!=null) {
            refreshProductTable();
        }
    }
    @FXML
    public void deleteProduct() {
        if(fieldId.getLength() != 0) {
            pDAO.delete(Long.valueOf(fieldId.getText()));
            buttonDelete.setDisable(true);
            refreshProductTable();
        }
    }


    @FXML
    public void FromListToFields(){
        try {
            buttonDelete.setDisable(false);
            fieldId.clear();
            fieldSalePrice.clear();
            fieldPurchasePrice.clear();
            fieldDesignation.clear();
            fieldAmount.clear();
            Product product = productTable.getSelectionModel().getSelectedItem();
            fieldId.setText(String.valueOf(product.getProductCode()));
            fieldDesignation.setText(String.valueOf(product.getDesignation()));
            fieldSalePrice.setText( String.valueOf(product.getSalePrice()));
            fieldPurchasePrice.setText( String.valueOf(product.getPurchasePrice()));
            categoryComboBox0.getSelectionModel().select(product.getCategory().getCategoryName());
            fieldAmount.setText(String.valueOf(product.getStockquantity()));
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
    }

    public void settingManagement(ActionEvent actionEvent) {
    }
}
