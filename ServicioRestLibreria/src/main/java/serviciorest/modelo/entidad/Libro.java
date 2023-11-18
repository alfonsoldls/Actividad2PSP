package serviciorest.modelo.entidad;

public class Libro {
	
	private int id;
	private String titulo;
	private String editorial;
	private int nota;
	
	public Libro() {
		super();
	}

	
	public Libro(int id, String titulo, String editorial, int nota) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.editorial = editorial;
		this.nota = nota;
	
	}


	public int getId() {
		return id;
	}


	public String getTitulo() {
		return titulo;
	}


	public String getEditorial() {
		return editorial;
	}


	public int getNota() {
		return nota;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public void setNota(int nota) {
		this.nota = nota;
	}


	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", editorial=" + editorial + ", nota=" + nota + "]";
	}
	
}
	