package gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import db.dbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Setor;
import model.services.SetorService;

public class SetorFormControler implements Initializable {

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;
	@FXML
	private Label lblMsgErro1;
	
	private Setor setor;
	private SetorService servico;
	
	Map<String, String> erros = new HashMap<>();
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public void setservico(SetorService servico) {
		this.servico = servico;
	}
	
	public void onBtnSalvarAction(ActionEvent evento) {
		if (setor == null) {
			throw new IllegalStateException("Setor estava vazio");
		}
		if (servico == null) {
			throw new IllegalStateException("Serviço estava vazio");
		}
		setor = getFormData();
		if (erros.size() < 1) {
			try {
				servico.saveOrUpdate(setor);
				Utils.currentStage(evento).close();
				MainViewControler controle = new MainViewControler();
				controle.onMiSetorAction();
			}
			catch (dbException e) {
				Alerts.showAlert("Erro", null, e.getMessage(), AlertType.ERROR);
			}
		}
		erros.clear();
	}
	
	private Setor getFormData() {
		erros.clear();
		Setor setor = new Setor();
		setor.setId(Utils.tryParseToInt(txtId.getText()));
		setor.setNome(txtNome.getText());
		if (txtNome.getText().equals("") || txtNome.getText().trim().equals("")) {
			erros.put("Nome", "Nome não pode ser em branco!");
		}
		
		if (erros.size() > 0) {
			lblMsgErro1.setText(erros.get("Nome"));
		}
		return setor;
	}

	public void onBtnCancelarAction(ActionEvent evento) {
		Utils.currentStage(evento).close();
	}
	
	public void updateFormDate() {
		if (setor == null) {
			throw new IllegalStateException("Setor está fazio");
		}
		if (setor.getId() != null) {
			txtId.setText(String.valueOf(setor.getId()));
			txtNome.setText(setor.getNome());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
}
