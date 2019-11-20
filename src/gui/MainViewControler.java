package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewControler implements Initializable {

	@FXML
	private MenuItem miSetor;
	@FXML
	private MenuItem miVendedor;
	@FXML
	private MenuItem miSobre;
	
	public void miSetorAction() {
		System.out.println("miSetorAction");
	}
	
	public void miVendedorAction() {
		System.out.println("miVendedorAction");
	}
	
	public void miSobreAction() {
		System.out.println("miSobre");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
}
