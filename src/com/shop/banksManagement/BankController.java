package com.shop.banksManagement;

import com.shop.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BankController implements Initializable {
    SceneController sceneController= SceneController.getSceneController();
    //buttons
    @FXML
    Button buttonDelete;
    // Text fields
    @FXML
    TextField fieldId;
    @FXML
    TextField fieldName;

    //Bank comboBox
    @FXML
    ComboBox<String> bankComboBox=new ComboBox<>();

    // Product Array
    @FXML
    TableView<Bank> bankTable;
    ObservableList<Bank> observableArray = FXCollections.observableArrayList();
    @FXML
    TableColumn bankCodeColumn;
    @FXML
    TableColumn bankNameColumn;

    // search text field
    @FXML
    TextField textKeyWord;


    BankDaoImpl bankDAO = new BankDaoImpl();
    List<Bank> banks =(List<Bank>) bankDAO.getAll();
    // label: Total
    @FXML
    Label labelTotal;

    // Error msg
    Alert alert = new Alert(Alert.AlertType.ERROR);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBankComboBox();
        initTable();
    }

    private void initBankComboBox(){
        for (Bank bank:banks){
            bankComboBox.getItems().add(bank.getBankName());
        }
    }
    private void initTable(){
        bankCodeColumn.setCellValueFactory(new PropertyValueFactory<>("bankCode"));
        bankNameColumn.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        refreshbankTable();
    }




    private  void refreshbankTable(){
        fieldId.clear();
        fieldName.clear();

        List<Bank> bankList =(List<Bank>) bankDAO.getAll();
        observableArray.setAll(bankList);
        bankTable.setItems(observableArray);

    }
    @FXML
    public void search(){
        List<Bank> bankList =(List<Bank>) bankDAO.getAll(textKeyWord.getText());
        observableArray.setAll(bankList);
        bankTable.setItems(observableArray);
    }
    @FXML
    public void addBank(){
        if(fieldId.getLength() !=0 && fieldName.getLength()!=0) {
            Bank bank= new Bank(Long.parseLong(fieldId.getText()),fieldName.getText());
            bankDAO.add(bank);
            refreshbankTable();
        }
        else {
            alert.setTitle("Input Error");
            alert.setHeaderText("Veuillez remplir tous les champs");
            //alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        }
    }
    @FXML
    public void updateBank(){
        long bankCode = Long.valueOf(fieldId.getText());
        String bankName = fieldName.getText();

        Bank bank= new Bank(bankCode,bankName);

        if(bankDAO.save(bank)!=null) {
            refreshbankTable();
        }
    }
    @FXML
    public void deleteBank() {
        if(fieldId.getLength() != 0) {
            bankDAO.delete(Long.valueOf(fieldId.getText()));
            buttonDelete.setDisable(true);
            refreshbankTable();
        }
    }


    @FXML
    public void FromListToFields(){
        try {
            buttonDelete.setDisable(false);
            Bank bank = bankTable.getSelectionModel().getSelectedItem();
            fieldId.clear();
            fieldName.clear();
            fieldId.insertText(0, String.valueOf(bank.getBankCode()));
            fieldName.insertText(0, String.valueOf(bank.getBankName()));
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

    public void settingManagement(ActionEvent actionEvent) {

    }
    public void overviewManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("overviewScene");
    }
}
