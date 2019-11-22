package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Setor;
import model.entities.Vendedor;
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

	private Vendedor vendedor;
	private VendedorService servico;
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	public void setVendedorService(VendedorService servico) {
		this.servico = servico;
	}
	
	public void updateFormDate(Vendedor vendedor) {
		if (vendedor == null) {
			throw new IllegalStateException("Vendedor está vazio");
		}
		if (vendedor.getId() != null) {
			txtId.setText(String.valueOf(vendedor.getId()));
			txtNome.setText(vendedor.getNome());
			if (vendedor.getDataNascimento() != null) {
				dpDataNascimento.setValue(LocalDate.ofInstant(vendedor.getDataNascimento().toInstant(), ZoneId.systemDefault()));
			}
			txtEmail.setText(vendedor.getEmail());
			Locale.setDefault(Locale.US);
			txtSalario.setText(String.format("%.2f", vendedor.getSalario()));
			if (vendedor.getSetor() == null) {
				cbSetor.getSelectionModel().selectFirst();
			}
			else {
				cbSetor.setValue(vendedor.getSetor());
			}
		}
	}
	
	@Override
	public void initialize(URL urs, ResourceBundle rb) {
		
	}

}
