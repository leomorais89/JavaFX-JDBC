package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Setor;
import model.entities.Vendedor;
import model.services.SetorService;
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
	
	@FXML
	public void onBtnCadastrarAction(ActionEvent evento) {
		Stage parentStage = Utils.currentStage(evento);
		Vendedor vendedor = new Vendedor();
		criarForm("/gui/VendedorFormView.fxml", parentStage, vendedor);
	}
	
	public void updateTableView() {
		if (servico == null) {
			throw new IllegalStateException("Serviço está vazio");
		}
		List<Vendedor> list = servico.todosVendedores();
		ObservableList<Vendedor> obsVendedor = FXCollections.observableArrayList(list);
		tvVendedor.setItems(obsVendedor);
	}
	
	public void criarForm(String endereco, Stage parentStage, Vendedor vendedor) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(endereco));
			Pane pane = loader.load();
			
			VendedorFormControler controle = loader.getController();
			controle.setVendedor(vendedor);
			controle.setServices(new VendedorService(), new SetorService());
			controle.updateFormDate(vendedor);
			
			Stage newStage = new Stage();
			newStage.setTitle("Formulário Vendedor");
			newStage.setScene(new Scene(pane));
			newStage.setResizable(false);
			newStage.initOwner(parentStage);
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IOException", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
		Utils.formatTableColumnDate(tcDataNascimento, "dd/MM/yyyy");
		tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tcSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
		tcSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tvVendedor.prefHeightProperty().bind(stage.heightProperty());
	}
}
