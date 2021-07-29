package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
		loadView("/gui/Compras.fxml");
	}
	
	@FXML
	public void onMenuItemRegistroDepartamentoAction() {
		
		loadView("/gui/Registro.fxml");
	}
	
	@FXML
	public void onMenuItemRegistroProdutoAction() {
		System.out.println("onMenuItemRegistroProdutoAction");
	}
	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	
		
	}
	
	private void loadView(String absoluteName) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		try {
			VBox newVBox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVBox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception","Error loading view" ,e.getMessage(),AlertType.ERROR);
		}
	}

}
