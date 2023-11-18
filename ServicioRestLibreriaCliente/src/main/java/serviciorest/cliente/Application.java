package serviciorest.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.Libro;
import serviciorest.cliente.servicio.ServicioProxyBiblioteca;
import serviciorest.cliente.servicio.ServicioProxyMensaje;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	//Primero inyectamos los objetos que necesitamos para
	//acceder a nuestro ServicioRest, el ServicioProxyBiblioteca y el ServicioProxyMensaje.
	
	@Autowired
	private ServicioProxyBiblioteca spb;
	
	@Autowired
	private ServicioProxyMensaje spm;
	
	//También necesitaremos acceder al contexto de Spring para parar
	//la aplicación, ya que esta app al ser una aplicación web se
	//lanzará en un Tomcat. De esta manera le decimos a Spring que
	//nos inyecte su propio contexto.
	
	@Autowired
	private ApplicationContext context;
		
	//En este método daremos de alta un objeto de tipo RestTemplate que será
	//el objeto más importante de esta aplicación. Será usado por los 
	//objetos ServicioProxy para hacer las peticiones HTTP a nuestro
	//servicio REST. 
	//Como no podemos anotar la clase RestTemplate para dar un objeto
	//de este tipo, porque no la hemos creado nosotros, usaremos la anotación 
	//@Bean para decirle a Spring que cuando arranque la app ejecute este 
	//método y meta el objeto devuelto dentro del contexto de Spring con ID 
	//"restTemplate" (el nombre del método)
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}
	
	//MÉTODO MAIN QUE LANZA LA APLICACIÓN
		public static void main(String[] args) {
			System.out.println("Cliente -> Cargando el contexto de Spring");
			SpringApplication.run(Application.class, args);

	//Como este método es estático no podemos acceder a los métodos dinámicos de la clase, como el "spb"
	//Para solucionar esto, haremos que nuestra clase implemente
	//"CommandLineRunner" e implementaremos el método "run"
	//Cuando se acabe de arrancar el contexto, se llamará automáticamente al método run
		}
		
	//Este método es dinámico por la tanto ya podemos acceder a los atributos dinámicos(spb)
		
		@Override
		public void run(String... args) throws Exception {
			System.out.println("****** Arrancando el cliente REST ******");
			System.out.println("*************  MENSAJE *****************");
			String mensaje = spm.obtener("libro");
			System.out.println("run -> Mensaje: " + mensaje);
			
			System.out.println("***********  MENSAJE HTML **************");
			String mensajeHTML = spm.obtener("libroHTML");
			System.out.println("run -> Mensaje: " + mensajeHTML);
			
			System.out.println("*********** ALTA LIBRO ***************");
			Libro libro = new Libro();
			libro.setTitulo("El juego del Ángel");
			libro.setEditorial("Planeta");
			libro.setNota(10);
			
			Libro lAlta = spb.alta(libro);
			System.out.println("run -> Nuevo libro dado de alta " + lAlta);
			
			System.out.println("************ GET LIBRO ***************");
			libro = spb.obtener(lAlta.getId());
			System.out.println("run -> Libro con id 5: " + libro);
			
			System.out.println("************ GET LIBRO ERRONEO ***************");
			libro = spb.obtener(10);
			System.out.println("run -> Libro con id 10: " + libro);
			
			System.out.println("********* MODIFICAR LIBRO *************");	
			Libro pModificar = new Libro();
			pModificar.setId(lAlta.getId());
			pModificar.setTitulo("La sombra del viento");
			pModificar.setEditorial("Planeta");
			pModificar.setNota(9);
			boolean modificado = spb.modificar(pModificar);
			System.out.println("run -> Libro modificado? " + modificado);
			
			System.out.println("********* MODIFICAR LIBRO ERRONEO*************");			
			pModificar.setTitulo("El prisionero del cielo");
			pModificar.setEditorial("Planeta");
			pModificar.setNota(8);
			modificado = spb.modificar(pModificar);
			System.out.println("run -> Libro modificado? " + modificado);
			
			System.out.println("********** BORRAR LIBRO **************");
			boolean borrado = spb.borrar(lAlta.getId());
			System.out.println("run -> Libro con id 5 borrado? " + borrado);	
			
			System.out.println("******** BORRAR LIBRO ERRONEO *******");
			borrado = spb.borrar(10);
			System.out.println("run -> Libro con id 20 borrado? " + borrado);		
			
			System.out.println("********** LISTAR LIBROS ***************");
			List<Libro> listaLibros = spb.findbyTitle(null);
			//Recorremos la lista y la imprimimos con funciones lambda
			//Tambien podríamos haber usado un for-each clásico de java
			listaLibros.forEach((v) -> System.out.println(v));
			
			System.out.println("******* LISTAR LIBROS POR TITULO *******");
			listaLibros = spb.findbyTitle("HARRY");
			listaLibros.forEach((v) -> System.out.println(v));
			
			System.out.println("******************************************");		
			System.out.println("******** Parando el cliente REST *********");	
			//Mandamos parar nuestra aplicación Spring Boot
			pararAplicacion();
		}
		
		public void pararAplicacion() {
			//Esta aplicacion levanta un servidor web, por lo que tenemos que dar 
			//la orden de pararlo cuando acabemos. Para ello usamos el método exit, 
			//de la clase SpringApplication, que necesita tanto el contexto de 
			//Spring como un objeto que implemente la interfaz ExitCodeGenerator. 
			//Podemos usar la función lambda "() -> 0" para simplificar 
			
			SpringApplication.exit(context, () -> 0);
			
			
		}
	}