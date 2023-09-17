package it.polito.tdp.baseball;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.baseball.model.Grado;
//import it.polito.tdp.baseball.model.Grado;
import it.polito.tdp.baseball.model.Model;
import it.polito.tdp.baseball.model.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConnesse;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnDreamTeam;

    @FXML
    private Button btnGradoMassimo;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtYear;

    
    
    @FXML
    void doCalcolaConnesse(ActionEvent event) {
    	this.txtResult.appendText("Numero di componenti connesse: "+this.model.getNumTotConnesse().size()+"\n\n");
    }

    
    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	String inputAnno=this.txtYear.getText();
    	String inputSalario=this.txtSalary.getText();
    	if(inputAnno=="" || inputSalario=="") {
    		this.txtResult.setText("Iserire un mumero in entrambi i campi!");
    		return;
    	}
    	int anno=0;
    	try {
    		anno=Integer.parseInt(inputAnno);
    	}catch (NumberFormatException nfe) {
    		this.txtResult.setText("Iserire un numero!");
    		return;
    	}
    	if(!this.model.getAllYears().contains(anno)) {
    		this.txtResult.setText("Iserire un anno valido!");
    		return;
    	}
    	int salario=0;
    	try {
    		salario=Integer.parseInt(inputSalario)*1000000;
    	}catch (NumberFormatException nfe) {
    		this.txtResult.setText("Iserire un numero!");
    		return;
    	}
    	this.model.buildGraph(anno,salario);
    	this.txtResult.appendText("Grafo creato con #V= "+this.model.getVsize()+" e #A= "+this.model.getEsize()+"\n\n");
    	this.btnGradoMassimo.setDisable(false);
    	this.btnConnesse.setDisable(false);
    }

    
    @FXML
    void doDreamTeam(ActionEvent event) {

    }

    
    @FXML
    void doGradoMassimo(ActionEvent event) {
    	Grado g=this.model.getGradoMassimo();
    	this.txtResult.appendText("Nodo di grado max: "+g.getP().getPlayerID()+", grado= "+g.getG()+"\n\n");
    }

    
    @FXML
    void initialize() {
        assert btnConnesse != null : "fx:id=\"btnConnesse\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGradoMassimo != null : "fx:id=\"btnGradoMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
