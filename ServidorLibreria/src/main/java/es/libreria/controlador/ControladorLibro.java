package es.libreria.controlador;

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

import es.libreria.libro.entidad.Libro;
import es.libreria.libro.persistencia.DaoLibro;

@RestController
public class ControladorLibro {
	
	@Autowired
	private DaoLibro daoLibro;

	/**
	 * Método para obtener un libro por su ID.
	 *
	 * @GetMapping: Es una anotación que indica que este método se invocará cuando se realice una petición HTTP GET.
	 * path="libros/{id}": Define la URL del servicio. En este caso, se espera un ID como parte de la URL (ej: /libros/3).
	 * produces = MediaType.APPLICATION_JSON_VALUE: Indica que la respuesta será en formato JSON.
	 *
	 * @param id El ID del libro que se quiere buscar. Se obtiene de la URL gracias a @PathVariable.
	 * @return ResponseEntity: Una entidad de respuesta que incluye el libro encontrado y el código de estado HTTP.
	 *
	 * Dentro del método:
	 * - Se imprime un mensaje en la consola indicando que se está buscando un libro con un ID específico.
	 * - Se intenta buscar el libro utilizando el ID proporcionado.
	 * - Si el libro se encuentra (libro != null), se devuelve con un código de estado HTTP 200 (OK).
	 * - Si el libro no se encuentra, se devuelve una respuesta sin cuerpo con un código de estado HTTP 404 (NOT FOUND).
	 */
	
	@GetMapping(path="libros/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Libro> getLibro(@PathVariable("id") int id) {
		System.out.println("Buscando libro con id: " + id);
		Libro libro = daoLibro.buscarLibroPorId(id);
		if(libro != null) {
			return new ResponseEntity<Libro>(libro,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	/**
	 * Método para agregar un nuevo libro.
	 *
	 * @PostMapping: Esta anotación indica que este método se invocará en respuesta a una solicitud HTTP POST.
	 * path="libros": Define la URL del servicio. En este caso, es simplemente '/libros'.
	 * consumes = MediaType.APPLICATION_JSON_VALUE: Indica que este método espera recibir datos en formato JSON.
	 * produces = MediaType.APPLICATION_JSON_VALUE: Indica que la respuesta será también en formato JSON.
	 *
	 * @param libro El objeto Libro que se quiere agregar. Se recibe en el cuerpo de la solicitud HTTP, indicado por @RequestBody.
	 * @return ResponseEntity: Una entidad de respuesta que incluye el libro agregado y el código de estado HTTP.
	 *
	 * Dentro del método:
	 * - Se imprime un mensaje en la consola indicando el objeto libro que se está agregando.
	 * - Se procede a agregar el libro utilizando el método 'agregarLibro' del objeto 'daoLibro'.
	 * - Se devuelve una ResponseEntity que incluye el libro agregado y un código de estado HTTP 201 CREATED.
	 *   Esto indica que el recurso (Libro) fue creado exitosamente.
	 */
	@PostMapping(path="libros",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro libro) {
		System.out.println("agregarLibro: objeto libro: " + libro);
		boolean duplicate = daoLibro.agregarLibro(libro);
		if(duplicate == false) {
			return new ResponseEntity<Libro>(libro,HttpStatus.CREATED);//201 CREATED
		}else {
			return new ResponseEntity<Libro>(HttpStatus.CONFLICT);//409 CONFLICT
		}
	}
	
	/**
	 * Método para listar libros.
	 * 
	 * Este método se activa cuando se hace una solicitud HTTP GET a la URL "/libros".
	 * Utiliza la anotación @GetMapping, indicando que es un método de tipo GET.
	 * La ruta es "/libros", lo que significa que se accede a este método a través de [tu dominio]/libros.
	 * 
	 * 'produces = MediaType.APPLICATION_JSON_VALUE' especifica que la respuesta de este método será en formato JSON,
	 * que es un formato estándar para intercambiar datos, fácil de entender tanto para humanos como para máquinas.
	 * 
	 * El método devuelve un objeto ResponseEntity, que representa toda la respuesta HTTP, incluyendo el cuerpo, el estado y los encabezados.
	 * En este caso, el cuerpo de la respuesta es una lista de objetos de tipo 'Libro'.
	 * 
	 * Dentro del método:
	 * - Primero, imprime un mensaje en la consola diciendo "Listando los libros". Esto es útil para fines de depuración.
	 * - Luego, obtiene una lista de libros utilizando 'daoLibro.listarTodosLibros()'. 'daoLibro' es probablemente un objeto que accede a la base de datos para obtener los libros.
	 * - Después imprime la lista de libros en la consola, nuevamente para depuración.
	 * - Finalmente, devuelve la lista de libros con un estado HTTP de OK (200), lo que significa que la solicitud fue exitosa y los datos se envían en formato JSON.
	 */
	@GetMapping(path="/libros",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> listarLibros() {
		System.out.println("Listando los libros");
		List<Libro> listaDeLibros = daoLibro.listarTodosLibros();
		System.out.println(listaDeLibros);
		return new ResponseEntity<List<Libro>>(listaDeLibros,HttpStatus.OK);
	}

	
	/**
	 * Método para modificar la información de un libro.
	 * 
	 * Este método se activa mediante una solicitud HTTP PUT a la URL "/libros/{id}".
	 * La anotación @PutMapping indica que es un método de tipo PUT.
	 * La ruta "libros/{id}" significa que el ID del libro a modificar se pasará en la URL, como por ejemplo [tu dominio]/libros/1 para modificar el libro con ID 1.
	 * 
	 * 'consumes = MediaType.APPLICATION_JSON_VALUE' especifica que este método espera recibir datos en formato JSON.
	 * 
	 * El método toma dos parámetros:
	 * - @PathVariable("id") int id: Esto captura el 'id' de la URL y lo asigna al parámetro 'id'. Es el ID del libro que queremos modificar.
	 * - @RequestBody Libro libro: Esto indica que el cuerpo de la solicitud PUT debe contener los datos del libro (en formato JSON) que queremos actualizar.
	 * 
	 * Dentro del método:
	 * - Primero, se imprime en la consola el ID del libro a modificar, útil para depuración.
	 * - Luego, se imprime en la consola la información del libro recibido para modificar.
	 * - El método establece el ID del libro recibido en el cuerpo de la solicitud al ID extraído de la URL para asegurar que se modifica el libro correcto.
	 * - Después, llama a 'daoLibro.modificarLibroPorId(libro)', que presumiblemente actualiza la información del libro en la base de datos.
	 * 
	 * Si 'libroUpdate' no es nulo, significa que la actualización fue exitosa, y el método devuelve una respuesta HTTP con el estado OK (200).
	 * Si 'libroUpdate' es nulo, significa que no se encontró un libro con ese ID, y el método devuelve una respuesta HTTP con el estado NOT FOUND (404).
	 */
	@PutMapping(path="libros/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> modificarLibro(
			@PathVariable("id") int id, 
			@RequestBody Libro libro) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + libro);
		libro.setId(id);
		Libro libroUpdate = daoLibro.modificarLibroPorId(libro);
		
		if(libroUpdate != null) {
			if (libroUpdate.getTitulo().equalsIgnoreCase("duplicate")) {
				return new ResponseEntity<Libro>(HttpStatus.CONFLICT);//409 CONFLICT
			}else {
				return new ResponseEntity<Libro>(HttpStatus.OK);//200 OK
			}
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	/**
	 * Método para borrar un libro por su ID.
	 * 
	 * Activado mediante una solicitud HTTP DELETE a la URL "/libros/{id}".
	 * La anotación @DeleteMapping indica que es un método de tipo DELETE.
	 * La ruta "libros/{id}" significa que el ID del libro a borrar se pasará en la URL, como por ejemplo [tu dominio]/libros/1 para borrar el libro con ID 1.
	 * 
	 * El método acepta un solo parámetro:
	 * - @PathVariable("id") int id: Captura el 'id' proporcionado en la URL y lo asigna al parámetro 'id'. Es el ID del libro que se va a borrar.
	 * 
	 * Dentro del método:
	 * - Primero, se imprime en la consola el ID del libro a borrar, lo cual es útil para fines de depuración.
	 * - Luego, se llama a 'daoLibro.borrarLibroPorId(id)', que presumiblemente elimina el libro con ese ID de la base de datos.
	 * 
	 * Si el objeto 'libro' devuelto no es nulo, significa que la eliminación fue exitosa, y el método devuelve una respuesta HTTP con el estado OK (200) y el libro borrado.
	 * Si 'libro' es nulo, significa que no se encontró un libro con ese ID, y el método devuelve una respuesta HTTP con el estado NOT FOUND (404).
     */
	@DeleteMapping(path="libros/{id}")
	public ResponseEntity<Libro> borrarLibro(@PathVariable("id") int id) {
		System.out.println("ID a borrar: " + id);
		Libro libro = daoLibro.borrarLibroPorId(id);
		if(libro != null) {
			return new ResponseEntity<Libro>(libro,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
}
