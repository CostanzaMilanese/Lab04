package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;	
	private List<Corso> corsi;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnInserisciDati;
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtCorsi;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtCorsi.clear();

		try {

			int matricola = Integer.parseInt(txtMatricola.getText());

			Studente studente = model.getStudente(matricola);
			if (studente == null) {
				txtCorsi.appendText("Nessun risultato: matricola inesistente");
				return;
			}

			List<Corso> corsi = model.cercaCorsiDatoStudente(studente);

			StringBuilder sb = new StringBuilder();

			for (Corso corso : corsi) {
				sb.append(String.format("%-8s ", corso.getCodins()));
				sb.append(String.format("%-4s ", corso.getCrediti()));
				sb.append(String.format("%-45s ", corso.getNome()));
				sb.append(String.format("%-4s ", corso.getPd()));
				sb.append("\n");
			}
			txtCorsi.appendText(sb.toString());

		} catch (NumberFormatException e) {
			txtCorsi.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtCorsi.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}


    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtCorsi.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	try {

			Corso corso = cmbCorsi.getValue();
			if (corso == null) {
				txtCorsi.setText("Selezionare un corso.");
				return;
			}

			List<Studente> studenti = model.studentiIscrittiAlCorso(corso);

			StringBuilder sb = new StringBuilder();

			for (Studente studente : studenti) {

				sb.append(String.format("%-10s ", studente.getMatricola()));
				sb.append(String.format("%-20s ", studente.getCognome()));
				sb.append(String.format("%-20s ", studente.getNome()));
				sb.append(String.format("%-10s ", studente.getCds()));
				sb.append("\n");
			}

			txtCorsi.appendText(sb.toString());

		} catch (RuntimeException e) {
			txtCorsi.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void scegliCorso(ActionEvent event) {

    }

    @FXML
    void inserisciDati(ActionEvent event) {
    	//inserimento automatico nome cognome data matricola
    	//chiama metodo confronto matricola
    	
    	txtCorsi.clear();
		txtNome.clear();
		txtCognome.clear();

		try {

			int matricola = Integer.parseInt(txtMatricola.getText());
			Studente studente = model.getStudente(matricola);

			if (studente == null) {
				txtCorsi.appendText("Nessun risultato: matricola inesistente");
				return;
			}

			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());

		} catch (NumberFormatException e) {
			txtCorsi.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtCorsi.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	cmbCorsi.setValue(null);
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtCorsi.clear();
    	
    	

    }
    
    private void setCmbCorsi() {
    	//ottieni tutti i corsi del model
    	corsi = model.getTuttiICorsi();
    	
    	//Aggiungi tutti i corsi alla ComboBox. 
    	 // per avere maggiore ordine li sorto alfabeticamente
    	cmbCorsi.getItems().addAll(corsi); // richiama il toString dell'oggetto Corso
    }
    
    
    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnInserisciDati != null : "fx:id=\"btnInserisciDati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorsi != null : "fx:id=\"txtCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    // a che serve ?
    public void setModel(Model model) {
    	this.model = model;
    	setCmbCorsi();
    }
}
