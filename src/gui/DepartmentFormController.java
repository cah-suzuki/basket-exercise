package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {
	private Department entity;
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	@FXML
	private Label labelErrorName;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("Save");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("Cancel");
	}

	
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	    initializeNodes();
		
	}

	//restrições de inicialização
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	//jogar nas caixas os dados do entity
	//caixa de txt trabalha com string
	public void updateFormData() {
		if (entity == null ) {
			throw new IllegalStateException ("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
