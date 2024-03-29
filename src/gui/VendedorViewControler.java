package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.dbException;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
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
	@FXML
	private TableColumn<Vendedor, Vendedor> tbEdit;
	@FXML
	private TableColumn<Vendedor, Vendedor> tbRemove;
	
	private VendedorService servico;
	
	public void setVendedorService(VendedorService servico) {
		this.servico = servico;
	}
	
	@FXML
	public void onBtnCadastrarAction(ActionEvent evento) {
		Stage parentStage = Utils.currentStage(evento);
		Vendedor vendedor = new Vendedor();
		criarForm("/gui/VendedorFormView2.fxml", parentStage, vendedor);
	}
	
	public void updateTableView() {
		if (servico == null) {
			throw new IllegalStateException("Servi�o est� vazio");
		}
		List<Vendedor> list = new ArrayList<Vendedor>();
		try {
			list = servico.todosVendedores();
		}
		catch (dbException e) {
			Alerts.showAlert("Erro", null, e.getMessage(), AlertType.ERROR);
		}
		ObservableList<Vendedor> obsVendedor = FXCollections.observableArrayList(list);
		tvVendedor.setItems(obsVendedor);
		initEditButtons();
		initRemoveButtons();
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
			newStage.setTitle("Formul�rio Vendedor");
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
	
	private void initEditButtons() {
		tbEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tbEdit.setCellFactory(param -> new TableCell<Vendedor, Vendedor>() {
			private final Button button = new Button("Edit");

			@Override
			protected void updateItem(Vendedor vendedor, boolean empty) {
				super.updateItem(vendedor, empty);
				if (vendedor == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> criarForm("/gui/VendedorFormView2.fxml", Utils.currentStage(event), vendedor));
			}
		});
	}
	
	private void initRemoveButtons() {
		tbRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tbRemove.setCellFactory(param -> new TableCell<Vendedor, Vendedor>() {
			private final Button button = new Button("Remove");

			@Override
			protected void updateItem(Vendedor vendedor, boolean empty) {
				super.updateItem(vendedor, empty);
				if (vendedor == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(vendedor));
			}
		});
	}
	
	protected void removeEntity(Vendedor vendedor) {
		Optional<ButtonType> resultado = Alerts.showConfirmation("Confirma��o", "Voc� realmente deseja excluir o(a) vendedor(a) " + vendedor.getNome() + " ?");
		if (resultado.get() == ButtonType.OK) {
			if (servico == null) {
				throw new IllegalStateException("Servi�o est� vazio!");
			}
			servico.remover(vendedor);
			updateTableView();
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
		Utils.formatTableColumnDouble(tcSalario, 2);
		tcSetor.setCellValueFactory(new PropertyValueFactory<>("setor"));
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tvVendedor.prefHeightProperty().bind(stage.heightProperty());
	}
}
