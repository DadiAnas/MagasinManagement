package com.shop.purchasesManagement;

import com.shop.SceneController;
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

public class PurchaseController implements Initializable {
    long transactionCode = 0;
    SceneController sceneController = SceneController.getSceneController();
    ProductDao<Product> productsDao = new ProductDaoImpl();

    PurchaseItemDao<PurchaseItem> itemsDao=new PurchaseItemDaoImpl();
    PurchaseDao purchaseDao = new PurchaseDaoImpl();

    StateDao stateDao=new StateDaoImpl();

    CategoryDaoImpl cDAO = new CategoryDaoImpl();
    List<Category> categories = cDAO.getAll();
    
    Collection<Product> productsList = null;


    Collection<PurchaseItem> itemsList = new ArrayList<>();

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
    TableColumn productSalePriceColumn;
    @FXML
    TableColumn productPurchasePriceColumn;
    @FXML
    TableColumn productAmountColumn;

    // Item Array
    @FXML
    TableView<PurchaseItem> itemTable =new TableView<>();
    ObservableList<PurchaseItem> itemObservableArray = FXCollections.observableArrayList();
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




    /******************************************************************* arrays **/
    void intitProudctsTable(){
        productCodeColumn.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        productDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        productSalePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        productPurchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
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
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseItemCode"));
        itemDesignationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        itemAmountColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
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
        for (PurchaseItem item:itemsList
             ) {
            if (item.getProduct().getDesignation().contains(itemSearchField.getText())){
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
        for (PurchaseItem item:itemsList
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
        double price = Double.valueOf(fieldPrice.getText());
        int amount = Integer.valueOf(fieldAmount.getText());
        double subTotal = amount * price;
        double purchasePrice = productTable.getSelectionModel().getSelectedItem().getPurchasePrice();
        int number = itemTable.getItems().size()+1;
        String designation= productTable.getSelectionModel().getSelectedItem().getDesignation();
        int amountRest= productTable.getSelectionModel().getSelectedItem().getStockquantity() - amount;
        Product product = productsDao.getOne(productTable.getSelectionModel().getSelectedItem().getProductCode());
 ;
        itemsList.add(new PurchaseItem(transactionCode,number,product,designation,amount,subTotal,purchasePrice,amountRest));
        refreshItemsTable();
    }
    @FXML
    public void deleteItem(){
        itemsList.remove(itemTable.getSelectionModel().getSelectedItem());
        buttonDelete.setDisable(true);
        refreshItemsTable();
    }
    @FXML
    public void saveItem(){
        for (PurchaseItem item:itemsList
        ) {
            itemsDao.add(item);
        }

        purchaseDao.add(new Purchase(transactionCode,(State) stateDao.getOne(1),total,LocalDate.now()));
        sceneController.getStage().close();
    }

    @FXML
    public void onSelectItem(){
        buttonDelete.setDisable(false);


    }
    /******************************************************* initialize ************************************/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transactionCode = 1;
        initItemsTable();
        intitProudctsTable();
        initCategoryComboBox();
    }
}
