package it.gestionefilm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.gestionefilm.entity.Film;

public class FilmDaoImp implements FilmDao {
	
	private EntityManager em = null;

	public EntityManager getEm() {
		if(em==null) {
			em = EntityManagerHelper.getEntityManager();
		}
		return em;
	}

	public void salva(Film f) {
		String incassoCriptato = BCrypt.hashpw(f.getIncasso(), BCrypt.gensalt());
		f.setIncasso(incassoCriptato);
		getEm().getTransaction().begin();
		getEm().persist(f);
		getEm().getTransaction().commit();
		
	}

	public void modifica(Film f) {
		try {
			getEm().getTransaction().begin();
			Film film = getEm().find(Film.class, f.getId());
			if(film != null) {
				getEm().merge(f);
				getEm().getTransaction().commit();
			}
			else {
				return ;
			}
		} catch (Exception e) {
			getEm().getTransaction().rollback();
		}
		
	}

	public void cancella(int id) {

		try {
			getEm().getTransaction().begin();
			getEm().remove(getEm().find(Film.class, id));
			getEm().getTransaction().commit();
		} catch (Exception e) {
			getEm().getTransaction().rollback();
			e.printStackTrace();
		}



	}

	@SuppressWarnings("unchecked")
	public List<Film> trovaTutti(){

		return getEm().createNamedQuery("TrovaTutti").getResultList(); 

	}
	/**
	 *  Trova un film in gestionefilm per regista
	 * 
	 *  @author  Daniele Wei Hao Pan
	 *  @param<regista>   Stringa regista del film da cercare
	 *  @return  Film trovato in gestionefilm
	 */
	@SuppressWarnings("unchecked")
	public List<Film> trovaByRegista(String regista) {
		Query q = getEm().createNamedQuery("trovaByRegista");
		q.setParameter(1, regista);
		try {
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Film trovaById(int id) {
		return getEm().find(Film.class, id);
	}

}
