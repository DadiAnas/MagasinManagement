package com.shop.overviewManagemenet;

import com.shop.SceneController;
import com.shop.productsManagement.Product;
import com.shop.productsManagement.ProductDao;
import com.shop.productsManagement.ProductDaoImpl;
import com.shop.productsManagement.categoriesManagement.Category;
import com.shop.productsManagement.categoriesManagement.CategoryDao;
import com.shop.productsManagement.categoriesManagement.CategoryDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    SceneController sceneController= SceneController.getSceneController();
    CategoryDao categoryDao= new CategoryDaoImpl();
    ProductDao productDao= new ProductDaoImpl();

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    BarChart<String,Number>  chart=new BarChart<String,Number>(xAxis,yAxis);
    ObservableList<BarChart.Data> barChartData = FXCollections.observableArrayList();

    List<Product> products= new ArrayList<>();

    public void newSerie(){
        int amount;
        for (Category category:(List<Category>) categoryDao.getAll()
             ) {
            amount = 0;
            XYChart.Series series=new XYChart.Series();
            series.setName(category.getCategoryName());
            products = productDao.getByCategory(category);
            for (Product product:products
                 ) {
                amount += product.getStockquantity();
            }
            series.getData().add(new XYChart.Data(category.getCategoryName(), amount));
            chart.getData().add(series);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart.setTitle("Stock");
        xAxis.setLabel("Products");
        yAxis.setLabel("Amount");


        newSerie();
    }

    public void overviewManagement(ActionEvent actionEvent) throws IOException {
        sceneController.setScene("overviewScene");
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


}
