package serviciorest.cliente.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import serviciorest.modelo.entidad.Libro;

@Service
public class ServicioProxyLibro {
	
	
	//URL BASE AL SERVICIO REST
	final static String URL = "http://localhost:8080/biblioteca";
	
	//Se inyecta el objeto de tipo RestTemplate para hacer las peticiones HTTP al servicio REST
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 //METODO GET - OBTENER UN LIBRO
	 
	 public Libro getLibro(int id) {
		 try{
			 
		 ResponseEntity<Libro> re  = restTemplate.getForEntity(URL + "/" + id, Libro.class);
		 return re.getBody();
		 
		 }catch (HttpClientErrorException e) {
			 System.out.println("No se ha encontrado el libro con id " + id);
			 System.out.println("Codigo de respuesta: " + e.getStatusCode());
			 return null;
		 }
	 }
	
	//METODO POST - DAR DE ALTA UN LIBRO
	 public Libro postLibro(Libro l) {
		 try {
			 ResponseEntity<Libro> re = restTemplate.postForEntity(URL, l, Libro.class);
			 return re.getBody();
		 } catch (HttpClientErrorException e) {
			 System.out.println("No se ha podido dar de alta el libro");
			 System.out.println("Codigo de respuesta: " + e.getStatusCode());
			 return null;
		 }
		 
	 }
	 
	 //METODO PUT - MODIFICAR LIBRO POR ID
	 public boolean updateLibro(Libro l) {
		 try {
			 restTemplate.put(URL + "/" + l.getId(),l, Libro.class);
			 return true;
		 }catch (HttpClientErrorException e) {
			 System.out.println("No se ha podido modificar el libro");
			 System.out.println("Codigo de respuesta: " + e.getStatusCode());
			 return false;
			 
		 }
	 }
	 
	 //METODO GET - OBTENER LISTA DE LIBROS
	 public List<Libro> getListaLibros(){
		 try {
			 ResponseEntity<Libro[]> re = restTemplate.getForEntity(URL, Libro[].class);
			 Libro[] arrayLibro = re.getBody();
			 return Arrays.asList(arrayLibro);
			 
		 }catch (HttpClientErrorException e) {
			 System.out.println("No se ha podido obtener la lista de libros");
			 System.out.println("Codigo de respuesta: " + e.getStatusCode());
			 return null;
			 
		 }
	 }
	 
	 //METODO DELETE - BORRAR LIBRO POR ID
	 public boolean deleteLibro(int id) {
		 try {
			 restTemplate.delete(URL + "/"+ id);
			 return true;
			 
		 }catch (HttpClientErrorException e) {
			 System.out.println("No se ha podido borrar el libro.");
			 System.out.println("Codigo de respuesta: " + e.getStatusCode());
			 return false;
			 
		 }
	 }
}
