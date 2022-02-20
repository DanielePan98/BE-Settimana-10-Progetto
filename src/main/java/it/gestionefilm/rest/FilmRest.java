package it.gestionefilm.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.gestionefilm.dao.FilmDao;
import it.gestionefilm.dao.FilmDaoImp;
import it.gestionefilm.entity.Film;

@RestController
@RequestMapping("/film")
@Api(value="FilmRest", tags="Gestione film")
public class FilmRest {


	FilmDao fd = new FilmDaoImp();

	@PostMapping
	@ApiOperation(value = "Inserimento film", notes = "Permette di inserire un film nel database")
	public ResponseEntity<String> aggiungi(@RequestBody Film f) {
		try {
			fd.salva(f);
			return new ResponseEntity<String>("Film inserito correttamente!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Film non inserito!", HttpStatus.BAD_REQUEST);
		}

	}
	@PutMapping("/{id}")
	@ApiOperation(value = "Aggiorna film", notes = "Permette di aggiornare un film nel database")
	public ResponseEntity<String> aggiorna(@RequestParam int id, @RequestBody Film f) {
		try {
			f.setId(id);
			fd.modifica(f);
			return new ResponseEntity<String>("Film aggiornato correttamente!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Film non aggiornato!", HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/{id}")
	@ApiOperation (value = "Cancella film", notes = "Permette di cancellare un film dal database")
	public ResponseEntity<String> cancella (@RequestParam int id) {
		try {
			fd.cancella(id);
			return new ResponseEntity<String>("Film cancellato correttamente!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Film non cancellato!", HttpStatus.BAD_REQUEST);

		}
	}
	@GetMapping
	@ApiOperation(value = "Trova tutti i film", notes = "Permette di visualizzare tutti i film presenti nel database")
	public ResponseEntity<List<Film>> trovaTutti() {
		try {
			return new ResponseEntity<List<Film>>((List<Film>)fd.trovaTutti(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Film>>((List<Film>)null, HttpStatus.BAD_REQUEST);

		}

	}
	@GetMapping("/byregista/{regista}")
	@ApiOperation(value = "Ricerca per regista", notes = "Permette di ricercare tutti i film di un determinato regista presenti nel database")
	public ResponseEntity<List<Film>> trovaByRegista(@RequestParam String regista) {
		try {
			return new ResponseEntity<List<Film>>(fd.trovaByRegista(regista), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Film>>((List<Film>)null, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/{id}")
	@ApiOperation(value = "trova by id", notes = "Permette di ricercare un film tramite id")
	public ResponseEntity<Film> trovaById(@RequestParam int id) {
		try {
			return new ResponseEntity<Film>(fd.trovaById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Film>((Film)null, HttpStatus.BAD_REQUEST);

		}

	}


}
