package serviciorest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.Libro;

@Component
public class DaoBiblioteca {

	
	
	public List<Libro> listaLibros;
	public int contadorId;
	
	public DaoBiblioteca() {
		System.out.println("DaoBiblioteca -> Creando lista de libros...");
		listaLibros = new ArrayList<Libro>();
		Libro l1 = new Libro(contadorId++, "Harry Potter y la piedra filosofal", "Salamandra", 10);
		Libro l2 = new Libro(contadorId++, "Harry Potter y la camara secreta", "Salamandra", 8);
		Libro l3 = new Libro(contadorId++, "Harry Potter y el prisionero de Azkaban", "Salamandra", 7);
		Libro l4 = new Libro(contadorId++, "Harry Potter y el caliz de fuego", "Salamandra", 9);
		Libro l5 = new Libro(contadorId++, "Harry Potter y la orden del fenix", "Salamandra", 7);
		listaLibros.add(l1);
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
	}
	
	
	
	//AÑADIR UN LIBRO
	public boolean add(Libro l) {
		if (findByTitle(l.getTitulo()) == true){
			System.out.println ("Ese libro ya existe");
			return false;
		}else {
			l.setId(contadorId++);
			listaLibros.add(l);
			return true;
		}
		
	}

	//DAR DE BAJA UN LIBRO POR ID
	public Libro delete(int id) {
		try {
			return listaLibros.remove(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Persona fuera de rango");
			return null;
		}
	}
	
	//MODIFICAR UN LIBRO POR ID
	public Libro update(Libro l) {
		try {
			Libro pAux = null;
		
			if (findByTitle(l.getTitulo()) == true){
				System.out.println ("Ese libro ya existe");
			}else {	
			pAux = listaLibros.get(l.getId());
			pAux.setTitulo(l.getTitulo());
			pAux.setEditorial(l.getEditorial());
			pAux.setNota(l.getNota());
				}
			return pAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Persona fuera de rango");
			return null;
		}
	}
		//OBTENER UN LIBRO POR TÍTULO
		public boolean findByTitle (String titulo){
		for(Libro l : listaLibros) {
			if(l.getTitulo().equalsIgnoreCase(titulo)) {
				return true;
			}
		}
		return false;
	}
		
		//OBTENER UN LIBRO POR ID
				public Libro findById (int id){
				Libro encontrado = null;
				for(Libro l : listaLibros) {
					if(l.getId() == id) {
						encontrado = listaLibros.get(id);
					}
				}
				return encontrado;
			}
		
		//LISTAR TODOS LOS LIBROS
		public List<Libro> list() {
			return listaLibros;
		}
}
