package gui;

import java.io.IOException;
import java.net.URL;
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
import model.services.SetorService;

public class SetorViewControler implements Initializable {

	@FXML
	private Button btnCadastrar;
	@FXML
	private TableView<Setor> tvSetor;
	@FXML
	private TableColumn<Setor, Integer> tcId;
	@FXML
	private TableColumn<Setor, String> tcNome;
	
	private SetorService servico;

	public void onBtnCadastrarAction(ActionEvent evento) {
		Stage parentStage = Utils.currentStage(evento);
		Setor setor = new Setor();
		criarForm("/gui/SetorFormView.fxml", parentStage, setor);
	}
	
	public void criarForm(String endereco, Stage parentStage, Setor setor) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(endereco));
			Pane pane = loader.load();
			
			SetorFormControler controler = loader.getController();
			controler.setSetor(setor);
			controler.setservico(new SetorService());
			controler.updateFormDate();
			
			Stage newStage  = new Stage();
			newStage.setTitle("Formulario Setor");
			newStage.setScene(new Scene(pane));
			newStage.setResizable(false);
			newStage.initOwner(parentStage);
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.showAndWait();
		}
		catch (IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar", e.getMessage(), AlertType.ERROR);
		}
	}
	
	public void setSetorService(SetorService servico) {
		this.servico = servico;
	}
	
	public void updateTableView() {
		if (servico == null) {
			throw new IllegalStateException("Serviço estava vazio");
		}
		List<Setor> list = servico.todosSetores();
		ObservableList<Setor> obsSetor = FXCollections.observableArrayList(list);
		tvSetor.setItems(obsSetor);
	}
	
	@Override
	public void initialize(URL urs, ResourceBundle bd) {
		tcId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tvSetor.prefHeightProperty().bind(stage.heightProperty());
	}
}
