package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.SetorService;
import model.services.VendedorService;

public class MainViewControler implements Initializable {

	@FXML
	private MenuItem miSetor;
	@FXML
	private MenuItem miVendedor;
	@FXML
	private MenuItem miSobre;
	
	public void onMiSetorAction() {
		loadView("/gui/SetorView.fxml", (SetorViewControler controle) -> {controle.setSetorService(new SetorService()); controle.updateTableView();});
	}
	
	public void onMiVendedorAction() {
		loadView("/gui/VendedorView.fxml", (VendedorViewControler controle) -> {controle.setVendedorService(new VendedorService()); controle.updateTableView();});
	}
	
	public void onMiSobreAction() {
		System.out.println("miSobre");
	}
	
	public synchronized <T> void loadView(String endereco, Consumer<T> acaoInicial) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(endereco));
			VBox vbox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			Node mainMenu = mainVbox.getChildren().get(0);
			
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(vbox.getChildren());
			
			T controle = loader.getController();
			acaoInicial.accept(controle);
		}
		catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IOException", "Erro ao carregar", e.getMessage(), AlertType.ERROR);
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
}
