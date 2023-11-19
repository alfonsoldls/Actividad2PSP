package es.cliente.entidad;

public class Libro {
    // Atributos de la clase
    private int id;
    private String titulo;
    private String editorial;
    private String nota;

    public Libro() {
    	super();
    }
    
    // Constructor
    public Libro(int id, String titulo, String editorial, String nota) {
        this.id = id;
        this.titulo = titulo;
        this.editorial = editorial;
        this.nota = nota;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    // Método toString
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }
    
}