package serviciorest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.Libro;

@Component
public class BibliotecaDao {

	private List<Libro> biblioteca;
	private int contador;
	
	
	public BibliotecaDao() {
		
		biblioteca = new ArrayList<>();
		Libro l1 = new Libro(contador++, "El Señor de los Anillos", "Minotauro", 9.6);
		Libro l2 = new Libro(contador++, "Cien años de soledad", "Editorial Sudamericana", 9.0);
		Libro l3 = new Libro(contador++, "1984", "Debolsillo", 9.4);
		Libro l4 = new Libro(contador++, "Orgullo y Prejuicio", "Penguin Clásicos", 9.2);
		Libro l5 = new Libro(contador++, "Harry Potter y la Piedra Filosofal", "Salamandra", 9.8);
		biblioteca.add(l1);
		biblioteca.add(l2);
		biblioteca.add(l3);
		biblioteca.add(l4);
		biblioteca.add(l5);
			
	}
	
	
	public boolean altaLibro(Libro l) {
		
		for(Libro ele: biblioteca) {
			if(ele.getId()== l.getId() || ele.getTitulo().equalsIgnoreCase(l.getTitulo())) 
				return false;
			}
		biblioteca.add(l);
		return true;
	}
	
	public Libro deleteLibroById(int id) {
		Libro l  = new Libro();
		l.setId(id);
		int pos = biblioteca.indexOf(l);
		
		if(pos == -1)
			return null;
		else {
			l = biblioteca.get(pos);
			biblioteca.remove(l);
			}
		return l;
	
	}
	
	public Libro UpdateLibroById(Libro l) {
		Libro lAux;
		int pos;
		if (biblioteca.contains(l)) {
			lAux = new Libro();
			lAux.setId(l.getId());
			lAux.setTitulo(l.getTitulo());
			lAux.setEditorial(l.getEditorial());
			lAux.setNota(l.getNota());
			pos = biblioteca.indexOf(lAux);
			biblioteca.set(pos, lAux);
		}else
			return null;
		return lAux;
		
	}
	
	public Libro getLibroById(int id) {
		Libro l = new Libro();
		int pos;
		l.setId(id);
		if (biblioteca.contains(l)){
			pos = biblioteca.indexOf(l);
			l = biblioteca.get(pos);
		} else 
			return null;
		return l;
	}
	
	public List<Libro> getBiblioteca(){
		return biblioteca;
	}
}
