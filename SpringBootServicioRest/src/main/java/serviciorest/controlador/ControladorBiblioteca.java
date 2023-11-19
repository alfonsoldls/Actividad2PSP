package serviciorest.controlador;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import serviciorest.modelo.entidad.Libro;
import serviciorest.modelo.persistencia.BibliotecaDao;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class ControladorBiblioteca {

	@Autowired
	private BibliotecaDao biblioteca;
	
	
	//METODO GET - OBTENER LIBRO
	@GetMapping(path = "biblioteca/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> getLibro(@PathVariable ("id") int id){
		System.out.println("Hey");
		Libro l = biblioteca.getLibroById(id);
		
		if (l != null)
			return new ResponseEntity<Libro>(l,HttpStatus.OK);
		else
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
	}
	
	//METODO GET - LISTAR TODOS LOS LIBROS
	@GetMapping(path="biblioteca",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listarLibros(){
		List<Libro> libros;
		libros = biblioteca.getBiblioteca();
		return new ResponseEntity<List<Libro>>(libros,HttpStatus.OK);
	}
	
	//METODO PUT - MODIFICAR LIBRO
	@PutMapping(path = "biblioteca/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Libro> modificarLibro(@PathVariable("id") int id, @RequestBody Libro l){
		l.setId(id);
		Libro lUpdate = biblioteca.UpdateLibroById(l);
		if (lUpdate != null)
			return new ResponseEntity<Libro>(lUpdate, HttpStatus.OK);
		else
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
	}
	
	//METODO POST - DAR DE ALTA UN LIBRO
	@PostMapping(path = "biblioteca", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro l){
		if(biblioteca.altaLibro(l))
			return new ResponseEntity<Libro>(l, HttpStatus.CREATED);	
		else
			return new ResponseEntity<Libro>(HttpStatus.CONFLICT);	
	}
	
	//METODO DELETE - BORRAR UN LIBRO POR ID
	@DeleteMapping(path = "biblioteca/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> borrarLibro (@PathVariable ("id") int id){
		Libro l = biblioteca.deleteLibroById(id);
		if(l != null)
			return new ResponseEntity<Libro> (l,HttpStatus.OK);
		else 
			return new ResponseEntity<Libro> (HttpStatus.NOT_FOUND);
	}
}
