package com.shop.salesManagement;

import com.shop.SceneController;
import com.shop.SendData;
import com.shop.personsManagement.Person;
import com.shop.productsManagement.Product;
import com.shop.productsManagement.ProductDao;
import com.shop.productsManagement.ProductDaoImpl;
import com.shop.productsManagement.categoriesManagement.Category;
import com.shop.productsManagement.categoriesManagement.CategoryDaoImpl;
import com.shop.statesManagement.State;
import com.shop.statesManagement.StateDao;
import com.shop.statesManagement.StateDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ItemBoxController implements Initializable {
    private long saleCode = 0;
    Product product=null;
    Sale sale=null;
    SaleItem saleItem;
    private Person person=null;
    SceneController sceneController = SceneController.getSceneController();
    SendData sendData = SendData.getInstance();
    ProductDao<Product> productsDao = new ProductDaoImpl();

    SaleItemDao<SaleItem> itemsDao=new SaleItemDaoImpl();
    SaleDao saleDao = new SaleDaoImpl();

    StateDao stateDao=new StateDaoImpl();

    CategoryDaoImpl cDAO = new CategoryDaoImpl();
    List<Category> categories = cDAO.getAll();

    Collection<Product> productsList = new ArrayList<>();


    Collection<SaleItem> itemsList = new ArrayList<>();

    Alert alert = new Alert(Alert.AlertType.WARNING);
    public double total=0;
    @FXML
    Label totalLabel;
    @FXML
    TextField fieldPrice;
    @FXML TextField fieldAmount;
    @FXML
    TextField itemSearchField;
    @FXML TextField productSearchField;

    @FXML ComboBox<String> categoryComboBox;

    //Buttons
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonSave;
    @FXML
    Button buttonDelete;

    // Product Array
    @FXML
    TableView<Product> productTable =new TableView<>();
    ObservableList<Product> productObservableArray = FXCollections.observableArrayList();
    @FXML
    TableColumn productCodeColumn;
    @FXML
    TableColumn productDesignationColumn;
    @FXML
    TableColumn productPurchasePriceColumn;
    @FXML
    TableColumn productSalePriceColumn;
    @FXML
    TableColumn productAmountColumn;

    // Item Array
    @FXML
    TableView<SaleItem> itemTable =new TableView<>();
    ObservableList<SaleItem> itemObservableArray = FXCollections.observableArrayList();
    @FXML
    TableColumn itemCodeColumn;
    @FXML
    TableColumn itemDesignationColumn;
    @FXML
    TableColumn itemPriceColumn;
    @FXML
    TableColumn itemAmountColumn;
    @FXML
    TableColumn itemSubTotalColumn;

    public void alertError(String errorName){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Magasin");
        alert.setHeaderText(null);
        alert.setContentText(errorName+" error");
        alert.showAndWait();
    }



    /******************************************************************* arrays **/
    void intitProudctsTable(){
        productCodeColumn.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        productDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        productSalePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        productSalePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        productAmountColumn.setCellValueFactory(new PropertyValueFactory<>("stockquantity"));
        refreshProductTable();
    }
    void refreshProductTable(){

        if (categoryComboBox.getSelectionModel().isEmpty()){
            productsList = productsDao.getAll();
            productObservableArray.setAll(productsList);
            productTable.setItems(productObservableArray);
        }
        else SearchByCategory();
    }
    void initItemsTable(){
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        itemDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        itemAmountColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        itemSubTotalColumn.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        refreshItemsTable();
    }
    void refreshItemsTable(){
        calculTotal();
        if (itemSearchField.getText().isEmpty()){
            itemObservableArray.setAll(itemsList);
            itemTable.setItems(itemObservableArray);
        }
        else {
            searchItems();
        }

    }
    @FXML
    void searchProduct(){
        productsList =productsDao.getAll(productSearchField.getText());
        productObservableArray.setAll(productsList);
        productTable.setItems(productObservableArray);
    }
    @FXML
    void searchItems(){
        itemObservableArray.clear();
        for (SaleItem item:itemsList
        ) {
            if (item.getDesignation().contains(itemSearchField.getText())){
                itemObservableArray.add(item);
            }
            if(itemSearchField.getText().isEmpty()) {
                itemObservableArray.add(item);
            }
        }
        itemTable.setItems(itemObservableArray);
    }
    @FXML
    private void SearchByCategory(){
        Category category=cDAO.getByName(categoryComboBox.getSelectionModel().getSelectedItem());
        productObservableArray.setAll(productsDao.getByCategory(category));
        productTable.setItems(productObservableArray);
    }

    /*********************************************************** items ***********************************/
    public void calculTotal(){
        total = 0;
        for (SaleItem item:itemsList
        ) {
            total += item.getSubTotal();
        }
        totalLabel.setText(String.valueOf(total));
    }
    private void initCategoryComboBox(){
        for (Category category:categories){
            categoryComboBox.getItems().add(category.getCategoryName());
        }
    }
    @FXML
    public void addItem(){
        if (fieldAmount.getText().isEmpty()) alertError("Filling Amount field");
        else if (productTable.getSelectionModel().isEmpty()) alertError("selecting product");
        else{
            int amount = Integer.parseInt(fieldAmount.getText());
            double salePrice = productTable.getSelectionModel().getSelectedItem().getSalePrice();
            int number = itemTable.getItems().size()+1;
            String designation= productTable.getSelectionModel().getSelectedItem().getDesignation();
            int amountRest= productTable.getSelectionModel().getSelectedItem().getStockquantity() - amount;
            System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+productTable.getSelectionModel().getSelectedItem().getProductCode());
            product = productsDao.getOne(productTable.getSelectionModel().getSelectedItem().getProductCode());
            boolean isExist = false;
            for (SaleItem item:itemsList
                 ) {

                if (productTable.getSelectionModel().getSelectedItem().getProductCode() == item.getProductCode()){
                    item.setQuantite(item.getQuantite()+amount);
                    item.setSubTotal(item.getQuantite()*item.getSalePrice());
                    isExist = true;
                }
            }
            if(isExist == false){
                itemsList.add(new SaleItem(number,productTable.getSelectionModel().getSelectedItem().getProductCode(), designation, amount, salePrice, amountRest));
            }
            refreshItemsTable();
        }
    }
    @FXML
    public void deleteItem(){
        total -= itemTable.getSelectionModel().getSelectedItem().getSubTotal();
        itemsList.remove(itemTable.getSelectionModel().getSelectedItem());
        buttonDelete.setDisable(true);
        refreshItemsTable();
    }
    @FXML
    public void saveItem(){
        sale = new Sale(saleCode,(State) stateDao.getOne(1),total,sendData.getDate());
        sale.setPerson(sendData.getPerson());
        saleCode = saleDao.add(sale);

        for (SaleItem item:itemsList
        ) {
            item.setSale(sale);
            //item.getSale().setTransactionCode(saleCode);
            itemsDao.add(item);
        }
        sceneController.getStage().close();
        sceneController.setStage(sceneController.stageList.get("1"));
    }

    @FXML
    public void onSelectItem(){
        buttonDelete.setDisable(false);
    }
    /******************************************************* initialize ************************************/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saleCode = 1;
        initItemsTable();
        intitProudctsTable();
        initCategoryComboBox();
    }
}
