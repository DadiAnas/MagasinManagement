package com.shop.personsManagement;

import com.shop.SceneController;
import com.shop.usersManagement.UserDao;
import com.shop.usersManagement.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class personController implements Initializable {
    SceneController sceneController=SceneController.getSceneController();

    PersonDao personDao= new PersonDaoImpl();
    UserDao userDao= new UserDaoImpl();

    ObservableList<String> observableListTypes = FXCollections.observableArrayList();
    ObservableList<Person> observableListPerson= FXCollections.observableArrayList();
    List<Person> personList = new ArrayList<>();

    //persons table
    @FXML TableView personTable;

    // columns
    @FXML TableColumn userCodeColumn;
    @FXML TableColumn firstNameColumn;
    @FXML TableColumn lastNameColumn;
    @FXML TableColumn roleColumn;
    // alerts
    Alert alert = null;
    // Text Field
    @FXML TextField fieldCode;
    @FXML TextField fieldFirstName;
    @FXML TextField fieldLastName;
    @FXML TextField  fieldEmail;
    @FXML TextField fieldPhone;
    @FXML TextField fieldSearch;
    //Buttons 
    @FXML Button buttonAdd;
    @FXML Button buttonSave;
    @FXML Button buttonDelete;
    @FXML Button buttonPurchase;
    // Combo Boxes
    @FXML ComboBox comboBoxRoleAdd;
    @FXML ComboBox comboBoxRoleSearch;

    public void alertError(String errorName){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Failer");
        alert.setHeaderText(null);
        alert.setContentText(errorName+" error");
        alert.showAndWait();
    }
    public boolean checkFields(){
        if (fieldFirstName.getText().isEmpty() || fieldLastName.getText().isEmpty() || fieldEmail.getText().isEmpty() || fieldPhone.getText().isEmpty() || comboBoxRoleAdd.getSelectionModel().isEmpty()){
            alertError("Filling all fields");
            return false;
        }
        else return true;
    }
    @FXML
    public void addUser(){
        String firstName = fieldFirstName.getText();
        String lastName = fieldLastName.getText();
        String email = fieldEmail.getText();
        String phone = fieldPhone.getText();
        String role =(String) comboBoxRoleAdd.getSelectionModel().getSelectedItem();

        Person newPerson = new Person(firstName,lastName,phone,email,role);
        System.out.println(newPerson.getType());
        if (checkFields()){
            if(personDao.add(newPerson) == null)   alertError("adding");
            else  refreshTable();
        }
    }
    @FXML
    public void saveUser(){
        if (checkFields()){
            long personCode =Long.parseLong(fieldCode.getText());
            String firstName = fieldFirstName.getText();
            String lastName = fieldLastName.getText();
            String email = fieldEmail.getText();
            String phone = fieldPhone.getText();
            String role =(String) comboBoxRoleAdd.getSelectionModel().getSelectedItem();
            Person editedPerson = new Person(personCode,firstName,lastName,phone,email,role);

            if(personDao.save(editedPerson) == null) alertError("Saving");
            else refreshTable();
        }
    }
    @FXML
    public void deleteUser(){
        if(personDao.delete((Person) personTable.getSelectionModel().getSelectedItem()) == 0){
            alertError("Deleting");
        }else {
            buttonDelete.setDisable(true);
            refreshTable();
        }
    }
    @FXML
    public void SearchByRole(){
        String type = comboBoxRoleSearch.getSelectionModel().getSelectedItem().toString();
        personList.clear();
        personList.addAll(personDao.getByType(type));
        observableListPerson.setAll(personList);
        personTable.setItems(observableListPerson);
    }

    @FXML
    public void onSelectTableItem(){
        try {
            buttonDelete.setDisable(false);
            //buttonPurchase.setDisable(false);
            Person selectedPerson = (Person) personTable.getSelectionModel().getSelectedItem();
            fieldCode.setText(String.valueOf(selectedPerson.getPersonCode()));
            fieldFirstName.setText(selectedPerson.getFirstName());
            fieldLastName.setText(selectedPerson.getLastName());
            fieldEmail.setText(selectedPerson.getEmail());
            fieldPhone.setText(String.valueOf(selectedPerson.getTelephone()));
            comboBoxRoleAdd.getSelectionModel().select(selectedPerson.getType());
        }catch (Exception e){
            buttonDelete.setDisable(true);
        }
    }
    @FXML
    public void search(){
        personList.clear();
        personList.addAll(personDao.getByCriteria(fieldSearch.getText()));
        observableListPerson.setAll(personList);
        personTable.setItems(observableListPerson);

    }
    public void initComboBoxes(){
        observableListTypes.setAll(personDao.getTypes());
        comboBoxRoleSearch.setItems(observableListTypes);
        comboBoxRoleAdd.setItems(observableListTypes);

    }

    public void initTable(){
        userCodeColumn.setCellValueFactory(new PropertyValueFactory<>("personCode"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        refreshTable();
    }
    
    public void refreshTable(){
        if(comboBoxRoleSearch.getSelectionModel().isEmpty()) {
            personList.clear();
            personList.addAll(personDao.getAll());
            observableListPerson.setAll(personList);
            personTable.setItems(observableListPerson);
        }
        else {
            SearchByRole();
        }
    }

    public void addPurchase() throws Exception {
        sceneController.show("purchaseItemScene");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initComboBoxes();
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
}
