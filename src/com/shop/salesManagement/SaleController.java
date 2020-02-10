package com.shop.salesManagement;

import com.shop.SceneController;
import com.shop.SendData;
import com.shop.personsManagement.Person;
import com.shop.personsManagement.PersonDao;
import com.shop.personsManagement.PersonDaoImpl;
import com.shop.purchasesManagement.Purchase;
import com.shop.statesManagement.State;
import com.shop.statesManagement.StateDao;
import com.shop.statesManagement.StateDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SaleController implements Initializable {
    SceneController sceneController=SceneController.getSceneController();
    SendData sendData = SendData.getInstance();

    SaleItemDao saleItemDao= new SaleItemDaoImpl();
    StateDao stateDao=new StateDaoImpl();

    PersonDao personDao= new PersonDaoImpl();
    ObservableList<Person> observableListPerson= FXCollections.observableArrayList();
    List<Person> personList = new ArrayList<>();

    SaleDao<Sale> saleDao = new SaleDaoImpl();
    ObservableList<Sale> observableListSale= FXCollections.observableArrayList();
    List<Sale> saleList= new ArrayList<>();

    @FXML TableView personTable;
    @FXML TableView saleTable;

    @FXML TableColumn<Sale, Long> codeSaleColumn ;
    @FXML TableColumn<Sale, Double> totalColumn;
    @FXML TableColumn<Sale, String> dateColumn;
    @FXML TableColumn<Sale, String> personColumn;
    @FXML TableColumn<Sale, String> etatColumn;
    @FXML TableColumn<Sale, String> totalPaymentsColumn ;
    @FXML TableColumn<Sale, String> restPaymentsColumn ;

    @FXML TableColumn userCodeColumn;
    @FXML TableColumn firstNameColumn;
    @FXML TableColumn lastNameColumn;

    @FXML Button buttonSale;
    @FXML Button buttonDelete;
    @FXML Button buttonPay;

    @FXML DatePicker datePicker;

    public void initSalesTable(){
        codeSaleColumn.setCellValueFactory(new PropertyValueFactory("transactionCode"));
        totalColumn.setCellValueFactory(new PropertyValueFactory("total"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("dateFr"));
        personColumn.setCellValueFactory(new PropertyValueFactory("person"));
        etatColumn.setCellValueFactory(new PropertyValueFactory("state"));
        restPaymentsColumn.setCellValueFactory(new PropertyValueFactory("restPayments"));
    }
    public void refreshSaleTable(){
        saleList.clear();
        saleList.addAll(saleDao.getAll((Person) personTable.getSelectionModel().getSelectedItem()));
        observableListSale.setAll(saleList);
        saleTable.setItems(observableListSale);
    }

    public void initPersonTable(){
        userCodeColumn.setCellValueFactory(new PropertyValueFactory<>("personCode"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        refreshPersonTable();
    }

    public void refreshPersonTable(){
        personList.clear();
        personList.addAll(personDao.getAll());
        observableListPerson.setAll(personList);
        personTable.setItems(observableListPerson);
    }

    @FXML
    public void onSelectTablePerson(){
        try {
            buttonSale.setDisable(false);
            refreshSaleTable();
        }catch (Exception e){}
    }
    @FXML
    public void onSelectTableSale(){

        buttonDelete.setDisable(false);
        buttonPay.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initSalesTable();
        initPersonTable();
    }



    public void search(KeyEvent keyEvent) {
    }

    public void addSale(ActionEvent actionEvent) throws Exception {
        if (personTable.getSelectionModel().isEmpty() || datePicker.getValue().getYear() < 2000 ){
            System.out.println("error");
        }
        else{
            sendData.setPerson((Person) personTable.getSelectionModel().getSelectedItem());
            sendData.setDate(datePicker.getValue());
            sceneController.show("saleItemScene");
        }
    }

    public void saveSale(ActionEvent actionEvent) {
    }

    public void deleteSale(ActionEvent actionEvent) {
        Sale sale = (Sale)saleTable.getSelectionModel().getSelectedItem();
        saleDao.delete(sale.getTransactionCode());
        refreshSaleTable();
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

    public void settingManagement(ActionEvent actionEvent) {

    } public void overviewManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("overviewScene");
    }


    public void addPay(ActionEvent actionEvent) throws Exception {
        sendData.setPerson((Person) personTable.getSelectionModel().getSelectedItem());
        sendData.setSale((Sale) saleTable.getSelectionModel().getSelectedItem());
        sceneController.show("payScene");
    }
}
