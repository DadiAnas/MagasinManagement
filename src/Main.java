import com.shop.SceneController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    SceneController sceneController;
    @Override
    public void start(Stage primaryStage) throws Exception{
        sceneController = SceneController.getSceneController();
        sceneController.setStage(primaryStage);
        sceneController.addScene("loginScene","/com/shop/usersManagement/view/login.fxml");
        sceneController.addScene("productScene","/com/shop/productsManagement/view/Product.fxml");
        sceneController.addScene("categoryScene","/com/shop/productsManagement/categoriesManagement/view/category.fxml");
        sceneController.addScene("bankScene","/com/shop/banksManagement/view/banks.fxml");
        sceneController.addScene("personScene","/com/shop/personsManagement/view/user.fxml");
        sceneController.addScene("purchaseItemScene","/com/shop/purchasesManagement/view/boxitems.fxml");
        sceneController.addScene("saleItemScene","/com/shop/salesManagement/view/boxitems.fxml");
        sceneController.addScene("saleScene","/com/shop/salesManagement/view/sale.fxml");
        sceneController.addScene("app","/view/app.fxml");
        sceneController.addScene("overviewScene","/com/shop/overviewManagemenet/view/overview.fxml");
        sceneController.addScene("payScene","/com/shop/salesManagement/paymentsManagement/view/payment.fxml");
        sceneController.setScene("loginScene");
        //sceneController.setScene("productScene");
        //sceneController.setScene("categoryScene");
        //sceneController.setScene("purchaseItemScene");
        //sceneController.setScene("bankScene");
        //sceneController.setScene("saleItemScene");
        //sceneController.setScene("saleScene");
        //sceneController.setScene("personScene");
        //sceneController.setScene("app");
        //sceneController.setScene("overviewScene");
        sceneController.setStage(primaryStage);
        sceneController.getStage().setTitle("Magasin");
        sceneController.getStage().getIcons().add(new Image("images/window_icon.png"));
        sceneController.getStage().show();
        sceneController.stageList.put("1",primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
