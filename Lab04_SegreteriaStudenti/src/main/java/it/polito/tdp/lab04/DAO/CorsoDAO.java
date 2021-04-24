package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	
	//metodo che mi serve per popolare menu a tendina 
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			

			while (rs.next()) {

				Corso corso = new Corso() ;
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				corso.setCodins(codins);
				corso.setCrediti(numeroCrediti);
				corso.setNome(nome);
				corso.setPd(periodoDidattico);
				
				corsi.add(corso);
				
			}

			conn.close();
			
		
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		
		
		
		}

	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "SELECT * FROM iscrizione WHERE iscrizione.matricola=studente.matricola AND codins=?" ;
		List <Studente > studentiIscrittiACorso = new ArrayList<Studente> ();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				studentiIscrittiACorso.add(new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds")));
			
			}
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e); }
		
		return studentiIscrittiACorso;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
