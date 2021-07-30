package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	private Department entity;
	
	private DepartmentService service;
	//permite obj se inscreverem na lista e receberem evento (LISTCONTROLLER = LISTENER)
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
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
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	//inscrição na lista de change listener ,outros obj implementando essa interface podem receber evento da classe
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			//notificar listeners que salvamento aconteceu com sucesso
			notifyDataChangeListeners();
			//FECHAR JANELA
			Utils.currentStage(event).close();
		}
		catch (ValidationException e) {
		  setErrorMessages(e.getErrors());
			
		}	
		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
		
	}
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener: dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	//pega os dados do form e instancia
	//validation nos dados
	private Department getFormData() {
	Department obj = new Department();
		
		ValidationException exception = new ValidationException ("Validation error");
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		
		if (txtName.getText()== null || txtName.getText().trim().equals("")) {
			exception.addError("name","Field can't be empty");
		}
		obj.setName(txtName.getText());
		
		if (exception.getErrors().size() >0) {
			throw exception;
		}
		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
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
	
	private void setErrorMessages(Map<String,String> errors) {
		Set<String> fields = errors.keySet();
		if (fields.contains("name")) {
			labelErrorName.setText(errors.get("name"));
		}
	}

}
