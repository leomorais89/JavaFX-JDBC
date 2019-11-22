package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Setor;
import model.entities.Vendedor;
import model.services.SetorService;
import model.services.VendedorService;

public class VendedorFormControler implements Initializable {
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private DatePicker dpDataNascimento;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtSalario;
	@FXML
	private ComboBox<Setor> cbSetor;
	@FXML
	private Button btnSalvar;

	private Vendedor vendedor;
	private VendedorService servicoService;
	private SetorService setorService;
	
	@FXML
	public void onBtnSalvarAction(ActionEvent evento) {
		if (vendedor == null) {
			throw new IllegalStateException("Vendedor está vazio!");
		}
		if (servicoService == null) {
			throw new IllegalStateException("Serviço Vendedor está vazio!");
		}
		vendedor = getFormDate();
		servicoService.saveOrUpdate(vendedor);
		Utils.currentStage(evento).close();
		MainViewControler controle = new MainViewControler();
		controle.onMiVendedorAction();
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public void setServices(VendedorService servicoService, SetorService setorService) {
		this.servicoService = servicoService;
		this.setorService = setorService;
	}
	
	public void updateFormDate(Vendedor vendedor) {
		if (vendedor == null) {
			throw new IllegalStateException("Vendedor está vazio");
		}
		
		List<Setor> setores = setorService.todosSetores();
		cbSetor.getItems().addAll(setores);
		
		if (vendedor.getId() != null) {
			txtId.setText(String.valueOf(vendedor.getId()));
			Locale.setDefault(Locale.US);
			txtSalario.setText(String.format("%.2f", vendedor.getSalario()));
		}
		txtNome.setText(vendedor.getNome());
		if (vendedor.getDataNascimento() != null) {
			dpDataNascimento.setValue(LocalDate.ofInstant(vendedor.getDataNascimento().toInstant(), ZoneId.systemDefault()));
		}
		txtEmail.setText(vendedor.getEmail());
		if (vendedor.getSetor() == null) {
			cbSetor.getSelectionModel().selectFirst();
		}
		else {
			cbSetor.setValue(vendedor.getSetor());
		}
	}
	
	private Vendedor getFormDate() {
		Vendedor vendedor = new Vendedor();
		vendedor.setId(Utils.tryParseToInt(txtId.getText()));
		vendedor.setNome(txtNome.getText());
		vendedor.setDataNascimento(Date.from(Instant.from(dpDataNascimento.getValue().atStartOfDay(ZoneId.systemDefault()))));
		vendedor.setEmail(txtEmail.getText());
		vendedor.setSalario(Double.parseDouble(txtSalario.getText()));
		vendedor.setSetor(cbSetor.getValue());
		return vendedor;
	}
	
	@Override
	public void initialize(URL urs, ResourceBundle rb) {
		
	}

}
