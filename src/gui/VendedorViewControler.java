package gui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Setor;
import model.entities.Vendedor;
import model.services.VendedorService;

public class VendedorViewControler implements Initializable {

	@FXML
	private Button btnCadastrar;
	@FXML
	private TableView<Vendedor> tvVendedor;
	@FXML
	private TableColumn<Vendedor, Integer> tcId;
	@FXML
	private TableColumn<Vendedor, String> tcNome;
	@FXML
	private TableColumn<Vendedor, Date> tcDataNascimento;
	@FXML
	private TableColumn<Vendedor, String> tcEmail;
	@FXML
	private TableColumn<Vendedor, Double> tcSalario;
	@FXML
	private TableColumn<Vendedor, Setor> tcSetor;
	
	private VendedorService servico;
	
	public void setVendedorService(VendedorService servico) {
		this.servico = servico;
	}
	
	public void onBtnCadastrarAction() {
		
	}
	
	public void updateTableView() {
		if (servico == null) {
			throw new IllegalStateException("Serviço está vazio");
		}
		List<Vendedor> list = servico.todosVendedores();
		ObservableList<Vendedor> obsVendedor = FXCollections.observableArrayList(list);
		tvVendedor.setItems(obsVendedor);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
		tcSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tvVendedor.prefHeightProperty().bind(stage.heightProperty());
	}
}
