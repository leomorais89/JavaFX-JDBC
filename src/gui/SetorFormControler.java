package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private Label lblMsgErro;
	
	private Setor setor;
	private SetorService servico;
	
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
		servico.saveOrUpdate(setor);
		Utils.currentStage(evento).close();
	}
	
	private Setor getFormData() {
		Setor setor = new Setor();
		setor.setId(Utils.tryParseToInt(txtId.getText()));
		setor.setNome(txtNome.getText());
		return setor;
	}

	public void onBtnCancelarAction() {
		System.out.println("onBtnCancelarAction");
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
