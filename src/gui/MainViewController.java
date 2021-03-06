package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItemDepartamento;
	@FXML
	private MenuItem menuItemProduto;
	@FXML
	private MenuItem menuItemCompras;
	
	@FXML
	private MenuItem menuItemListaProdutos;
	@FXML 
	private MenuItem menuItemCarrinho;
	
	
	
	
	@FXML
	public void onMenuItemListaProdutosAction() {
		loadView("/gui/Compras.fxml", x -> {});
	}
	
	@FXML
	public void onMenuItemRegistroDepartamentoAction() {
		
		loadView("/gui/Registro.fxml",(DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemRegistroProdutoAction() {
		System.out.println("onMenuItemRegistroProdutoAction");
	}
	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	
		
	}
	
	private synchronized <T> void loadView(String absoluteName,Consumer <T> initializingAction) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		try {
			VBox newVBox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVBox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			//ativar função lambda
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception","Error loading view" ,e.getMessage(),AlertType.ERROR);
		}
	}
	
	
}
