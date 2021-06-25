
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.PlayerAndPeso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGiocatoreMigliore"
    private Button btnGiocatoreMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMatch"
    private ComboBox<Match> cmbMatch; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	if(cmbMatch.getValue() == null) {
    		txtResult.setText("Selezione un match");
    		return;
    	}
    	model.creaGrafo(cmbMatch.getValue().getMatchID());
    	txtResult.appendText("Grafo creato:");
    	txtResult.appendText('\n'+"Vertici: " + model.getNVertici());
    	txtResult.appendText('\n'+"Adiacenze: " + model.getNAdiacenze());

    
    }

    @FXML
    void doGiocatoreMigliore(ActionEvent event) {  
    	if(model.getNVertici() == 0) {
    		txtResult.appendText("crea il grafo");
    		return;	
    	}
    	PlayerAndPeso best = model.getBestPlayer();
    	txtResult.setText("miglior giocatore:  " + best.getPlayer().getPlayerID() + ", "+ best.getPlayer().getName() + "  punteggio:  " + best.getPeso());
    	
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	Integer k = 0;

    	if(txtN.getText().compareTo("") == 0) {

    		txtN.setText("inserisci un numero di azioni salienti");

    		return;

    	}

    	try {

    		k =Integer.parseInt(txtN.getText());

    	} catch (NumberFormatException n) {

    		txtResult.setText("il numero di azioni salienti deve essere indicato con delle cifre  e deve essere intero");

    		return;

    	}
    	if(cmbMatch.getValue() == null) {
    		txtResult.setText("Selezione un match");
    		return;
    	}
    	model.sim(k, cmbMatch.getValue().getMatchID());
    	txtResult.setText("PunteggioAway: " + model.getPunteggioAway() + '\n');
    	txtResult.appendText("PunteggioHome: " + model.getPunteggioHome() + '\n' );
    	txtResult.appendText("espulsioneAway: " + model.getEspulsioneAway() + '\n' );
    	txtResult.appendText("espulsioneHome: " + model.getEspulsioneHome() + '\n');
    	model.azzeraContatore();

    	    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGiocatoreMigliore != null : "fx:id=\"btnGiocatoreMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMatch != null : "fx:id=\"cmbMatch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbMatch.getItems().addAll(this.model.getMatch());


    }
}
