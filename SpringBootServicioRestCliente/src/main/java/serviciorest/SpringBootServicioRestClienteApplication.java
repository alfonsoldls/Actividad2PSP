package serviciorest;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.servicio.ServicioProxyLibro;
import serviciorest.modelo.entidad.Libro;

@SpringBootApplication
public class SpringBootServicioRestClienteApplication implements CommandLineRunner{

	@Autowired
	private ServicioProxyLibro spl;
	
	@Autowired
	private ApplicationContext context;
	

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicioRestClienteApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Scanner sc =  new Scanner(System.in);
		boolean continuar = true;
		
		while (continuar) {
		
			System.out.println("\n------------------------------");
			System.out.println("1.Dar de alta un Libro");
			System.out.println("2.Dar de baja un Libro");
			System.out.println("3.Modificar un libro por su ID");
			System.out.println("4.Obtener un libro por ID");
			System.out.println("5.Listar todos los libros");
			System.out.println("6. Salir");
			System.out.println("------------------------------");
			
			
			String opcion = sc.nextLine();
			
			switch (opcion) {
				case "1": 
					if(spl.postLibro(pideLibro(sc)) != null)
						System.out.println("El libro se añadio correctamente.");
					break;
				case "2":
					System.out.println("Introduzca el ID del libro a eliminar");
					if(spl.deleteLibro(Integer.parseInt(sc.nextLine())))
						System.out.println("Libro eliminado correctamente");
					else
						System.out.println("El ID del libro no existe");
					break;
				case "3":
					System.out.println("Introduzca el libro a modificar");
					if(spl.updateLibro(pideLibro(sc)))
						System.out.println("El libro se modifico correctamente.");
					break;
				case "4":
					System.out.println("Introduzca el ID del libro a obtener");
					System.out.println(spl.getLibro(Integer.parseInt(sc.nextLine())));
					break;
				case "5":
					for (Libro e : spl.getListaLibros())
						System.out.println(e);
					break;
				case "6":
					continuar = false;
					System.out.println("Saliendo del sistema...");
					SpringApplication.exit(context, () -> 0);
					break;
			}
		
		
		
	}
		
	
	}
	
	
	
	public Libro pideLibro(Scanner sc) {
		Libro l = new Libro();
		System.out.println("Introduzca el ID del Libro");
		l.setId(Integer.parseInt(sc.nextLine()));
		System.out.println("Introduzca el titulo del Libro");
		l.setTitulo(sc.nextLine());
		System.out.println("Introduzca el titulo del Libro");
		l.setEditorial(sc.nextLine());
		System.out.println("Introduzca el titulo del Libro");
		l.setNota(Double.parseDouble(sc.nextLine()));
		return l;
	}
}
	