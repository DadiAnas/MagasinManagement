package com.shop.salesManagement.paymentsManagement;

import com.shop.SceneController;
import com.shop.SendData;
import com.shop.banksManagement.BankDao;
import com.shop.banksManagement.BankDaoImpl;
import com.shop.personsManagement.Person;
import com.shop.salesManagement.Sale;
import com.shop.statesManagement.State;
import com.shop.statesManagement.StateDao;
import com.shop.statesManagement.StateDaoImpl;
import com.shop.transactionsManagement.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    PaymentDao paymentDao=new PaymentDaoImpl();
    SendData sendData= SendData.getInstance();
    StateDao stateDao = new StateDaoImpl();
    Person person=null;
    Sale sale=null;
    ObservableList<Payment> observableList= FXCollections.observableArrayList();
    List<Payment> payments= new ArrayList<>();
BankDao bankDao= new BankDaoImpl();
    @FXML
    TextField fieldAmount;
    @FXML
    TextField fieldCheque;
    @FXML TableColumn codeColumn;
    @FXML TableColumn paymentDateColumn;
    @FXML TableColumn bankColumn;
    @FXML TableColumn amountColumn;
    @FXML TableColumn etatColumn;
    @FXML TableColumn typeColumn;

    @FXML
    TableView payTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    void initTable(){
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("paymentCode"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("daate"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("stateName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        refreshTable();
    }

    void refreshTable(){
        payments =(List<Payment>) paymentDao.getAll();
        observableList.setAll(payments);
        payTable.setItems(observableList);

    }
    SceneController sceneController= SceneController.getSceneController();
    @FXML
    void save() throws IOException {
        sceneController.getStage().close();
        sceneController.setStage(sceneController.stageList.get("1"));
    }
    @FXML
    void addPay() {
        if(fieldAmount.getText().isEmpty() || fieldCheque.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.show();
        }
        else {
            LocalDate date = LocalDate.now();
            sale = sendData.getSale();
            Double total = Double.parseDouble(fieldAmount.getText());
            State state = (State) stateDao.getOne(1);
            Payment payment = new Payment(0, 0, total, date, "offline", state.getStateName());
            payment.setBank(bankDao.getByCode(1));
            payment.setChequeNumber(fieldCheque.getText());
            payment.setDueDate(date);
            payment.setDaate(date.toString());
            payment.setBankName("BANKPOP");
            payment.setFirstNameCheque("dadi");
            payment.setLastNameCheque("anasdadf");
            paymentDao.add(payment);
            refreshTable();
        }
    }
}
