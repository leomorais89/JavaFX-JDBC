package gui.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

	public static void showAlert(String titulo, String cabecalho, String conteudo, AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.show();
	}
	
	public static Optional<ButtonType> showConfirmation(String titulo, String conteudo) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(conteudo);
		return alert.showAndWait();
	}
}
