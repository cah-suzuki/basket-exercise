package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItemCompras;
	@FXML
	private MenuItem menuItemRegistro;
	
	@FXML
	public void onMenuItemComprasAction() {
		System.out.println("onMenuItemComprasAction");
	}
	
	@FXML
	public void onMenuItemRegistroAction() {
		System.out.println("onMenuItemregistroAction");
	}
	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	
		
	}

}
