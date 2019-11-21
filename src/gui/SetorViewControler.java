package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Setor;
import model.services.SetorService;

public class SetorViewControler implements Initializable {

	@FXML
	private Button btnCadastrar;

	public void onBtnCadastrarAction(ActionEvent evento) {
		Stage parentStage = (Stage) ((Node) evento.getSource()).getScene().getWindow();
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
	
	@Override
	public void initialize(URL urs, ResourceBundle bd) {
		
	}
}
