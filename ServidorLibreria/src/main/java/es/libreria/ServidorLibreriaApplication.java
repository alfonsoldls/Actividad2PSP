package es.libreria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServidorLibreriaApplication {

	public static void main(String[] args) {
		System.out.println("Servico REST -> Cargando contexto de Spring...");
		/**
		 * Este es el método principal que inicia la aplicación Spring Boot.
		 * 
		 * SpringApplication.run() realiza las siguientes funciones clave:
		 * 
		 * 1. Configuración Automática: Configura automáticamente la aplicación basada en las dependencias añadidas.
		 * 2. Iniciar Contenedor de Spring: Crea e inicia un contexto de aplicación Spring, manejando la creación y gestión de beans, y la inyección de dependencias.
		 * 3. Manejo de Propiedades: Utiliza argumentos de línea de comandos para configurar propiedades y comportamientos de la aplicación.
		 * 4. Iniciar Servidor Web: Inicia un servidor web incorporado (en este caso Tomcat) y despliega la aplicación.
		 * 
		 * Este método es el punto de entrada estándar para la aplicación Spring Boot.
		 */
		SpringApplication.run(ServidorLibreriaApplication.class, args);
		System.out.println("Servico REST -> Contexto de Spring cargado con éxito.");
	}

}
