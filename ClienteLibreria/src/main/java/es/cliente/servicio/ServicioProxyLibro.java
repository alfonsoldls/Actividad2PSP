package es.cliente.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.cliente.entidad.Libro;

// Anotación para indicar que esta clase es un servicio en el contexto de Spring
@Service
public class ServicioProxyLibro {
	
	// URL base para las operaciones del servicio
	public static final String URL = "http://localhost:8080/libros";
	
	// Inyecta automáticamente una instancia de RestTemplate
	@Autowired
	private RestTemplate restTemplate;

	// Método para obtener un libro por su ID
	public Libro obtener(int id){
		try {
			// Realiza una solicitud GET al servicio REST
			ResponseEntity<Libro> re = restTemplate.getForEntity(URL + "/" + id, Libro.class);
			HttpStatus hs = (HttpStatus) re.getStatusCode();
			// Comprueba si la respuesta es OK y devuelve el libro
			if(hs == HttpStatus.OK) {	
				return re.getBody();
			}else {
				// Manejo de cualquier otra respuesta HTTP
				System.out.println("obtener -> Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {
			// Manejo de errores en caso de que el libro no se encuentre
			System.out.println("obtener -> El libro no se ha encontrado, id: " + id);
		    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	// Método para dar de alta un nuevo libro
	public Libro alta(Libro libro){
		try {
			// Realiza una solicitud POST al servicio REST
			ResponseEntity<Libro> re = restTemplate.postForEntity(URL, libro, Libro.class);
			System.out.println("alta -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			// Manejo de errores en caso de fallo al dar de alta
			System.out.println("alta -> El libro no se ha dado de alta, id: " + libro);
		    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	// Método para modificar un libro existente
	public boolean modificar(Libro libro){
		try {
			// Realiza una solicitud PUT al servicio REST
			restTemplate.put(URL + "/" + libro.getId(), libro, Libro.class);
			return true;
		} catch (HttpClientErrorException e) {
			// Manejo de errores en caso de fallo al modificar
			System.out.println("modificar -> El libro no se ha modificado, id: " + libro.getId());
		    System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	// Método para borrar un libro por su ID
	public boolean borrar(int id){
		try {
			// Realiza una solicitud DELETE al servicio REST
			restTemplate.delete(URL + "/" + id);
			return true;
		} catch (HttpClientErrorException e) {
			// Manejo de errores en caso de fallo al borrar
			System.out.println("borrar -> El libro no se ha borrado, id: " + id);
		    System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	// Método para listar todos los libros
	public List<Libro> listar(){
		try {
			// Realiza una solicitud GET para obtener todos los libros
			ResponseEntity<Libro[]> response =
					  restTemplate.getForEntity(URL, Libro[].class);
			Libro[] arrayLibros = response.getBody();
			// Convierte el array a una lista y la devuelve
			return Arrays.asList(arrayLibros);
		} catch (HttpClientErrorException e) {
			// Manejo de errores en caso de fallo al obtener la lista
			System.out.println("listar -> Error al obtener la lista de libros");
		    System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
}

