package es.libreria.libro.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.libreria.libro.entidad.Libro;

@Component
public class DaoLibro {
	
	public List<Libro> listaLibros;
	
	public DaoLibro() {
		System.out.println("DaoLibro -> Creando la lista de libros...");
		listaLibros = new ArrayList<Libro>();
		Libro libro1 = new Libro(1, "La Sombra del Viento", "Editorial Planeta", "Fascinante novela de misterio");
        Libro libro2 = new Libro(2, "El Juego del Ángel", "Editorial Anagrama", "Secuela emocionante y misteriosa");
        Libro libro3 = new Libro(3, "El Laberinto de los Espíritus", "Editorial Seix Barral", "Final épico de la serie");
        Libro libro4 = new Libro(4, "Marina", "Editorial Alfaguara", "Una historia de amor y misterio");
        Libro libro5 = new Libro(5, "El Prisionero del Cielo", "Editorial Penguin", "Una trama intrigante y bien tejida");
        listaLibros.add(libro1);
        listaLibros.add(libro2);
        listaLibros.add(libro3);
        listaLibros.add(libro4);
        listaLibros.add(libro5);
	}
	
	
	/**
     * Método para encontrar un libro por su ID.
     *
     * @param id El ID del libro que se busca.
     * @return El libro con el ID dado, o null si no se encuentra.
     */
	public Libro buscarLibroPorId(int id) {
		try {
			for (Libro libro : listaLibros) {
				if (id == libro.getId()) {
					return libro;
				}
			}
			return null;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("No se ha encontrado ningún libro con ese ID.");
			return null;
		}
	}
	
	/**
     * Método que devuelve todos los libros del array
     * 
     * @return una lista con todos los libros del array
     */
	public List<Libro> listarTodosLibros(){
		return listaLibros;
	}
	
	/**
     * Método que agrega un libro al final del array
     * 
     * @param libro el libro que queremos añadir
     */
	public boolean agregarLibro(Libro libro) {
		for (Libro lb: listaLibros) {
			if (libro.getId() == lb.getId() || libro.getTitulo().equalsIgnoreCase(lb.getTitulo())) {
				return true;
			}
		}
		listaLibros.add(libro);
		return false;
	}
	
	/**
     * Método que elimina del array el libro que tenga el id que le indiquemos
     * 
     * @param id el id del libro que queremos eliminar
     * @return devolvemos el libro que acabamos de eliminar 
     * o null en caso de que ese id no pertenezca a ningun libro
     */
	public Libro borrarLibroPorId(int id) {
		try {
			for (Libro libro : listaLibros) {
				if (id == libro.getId()) {
					return listaLibros.remove(listaLibros.indexOf(libro));
				}
			}
			return null;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("No se ha encontrado ningún libro con ese ID.");
			return null;
		}
	}
	
	/**
     * Método que modifica el libro que tenga el id que le indiquemos
     * 
     * @param id el id del libro que queremos modificar
     * @return devolvemos el libro que acabamos de modificar 
     * o null en caso de que ese id no pertenezca a ningun libro
     */
	public Libro modificarLibroPorId(Libro libro) {
		try {
			Libro libroDuplicado = new Libro(0, "duplicate", null, null);
			for (Libro lb: listaLibros) {
				if (libro.getTitulo().equalsIgnoreCase(lb.getTitulo())) {
					return libroDuplicado;
				}
			}	
			Libro libroAux = listaLibros.get(libro.getId());
			libroAux.setTitulo(libro.getTitulo());
			libroAux.setEditorial(libro.getEditorial());
			libroAux.setNota(libro.getNota());
			
			return libroAux;
		}catch(IndexOutOfBoundsException iobe) {
			System.out.println("No se ha encontrado ningún libro con ese ID.");
			return null;
		}
	}
	
	
}
