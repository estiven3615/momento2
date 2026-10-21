
public class Estudiantes {
    private int id;
    private String nombre;
    private String documento;
    private String correo;

    public Estudiantes(int id, String nombre, String documento, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getCorreo() { return correo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Documento: "
                + documento + " | Correo: " + correo;
    }
}