package serviciorest.controlador;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import serviciorest.modelo.entidad.Libro;
import serviciorest.modelo.persistencia.DaoBiblioteca;

@RestController
public class ControladorBiblioteca {

	@Autowired
	private DaoBiblioteca daoBiblioteca;
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros" y el metodo a usar seria POST
	//Pasandole la persona sin el ID dentro del body del HTTP request
	
	//DAR DE ALTA UN LIBRO NUEVO
	@PostMapping(path="libros",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro l) {
		System.out.println("Nuevo libro dado de alta: " + l);
		if (daoBiblioteca.add(l) == true) {
			return new ResponseEntity<Libro>(l,HttpStatus.CREATED);//201 CREATED
		}else {
			return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);//400 BAD REQUEST
		}
	}
	
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria DELETE
	
	//DAR DE BAJA UN LIBRO POR ID
	@DeleteMapping(path="libros/{id}")
	public ResponseEntity<Libro> borrarLibro(@PathVariable("id") int id) {
		System.out.println("ID a borrar: " + id);
		Libro l = daoBiblioteca.delete(id);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria PUT
	//Pasandole el libro sin el ID dentro del body del HTTP request
	
	//MODIFICAR UN LIBRO POR ID
	@PutMapping(path="libros/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> modificarLibro(
			@PathVariable("id") int id, 
			@RequestBody Libro l) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + l);
		l.setId(id);
		Libro lUpdate = daoBiblioteca.update(l);
		if(lUpdate != null) {
			return new ResponseEntity<Libro>(HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.BAD_REQUEST);//400 BAD REQUEST
		}
	}
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros/ID" y el metodo a usar seria GET
	//ID sería el identificador que queremos buscar
	
	//OBTENER UN LIBRO POR ID
	@GetMapping(path="/libros/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Libro> getLibro(@PathVariable("id") int id) {
		System.out.println("Buscando persona con id: " + id);
		Libro l = daoBiblioteca.findById(id);
		if(l != null) {
			return new ResponseEntity<Libro>(l,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8080/libros" y el metodo a usar sería GET
	
	//MOSTRAR LISTA DE LIBROS
	@GetMapping(path="libros",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listarLibros(){
		List<Libro> listaLibros = null;
			System.out.println("Listando las personas");
			listaLibros = daoBiblioteca.list();			
		return new ResponseEntity<List<Libro>>(listaLibros,HttpStatus.OK);
	}
}
