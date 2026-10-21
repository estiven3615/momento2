
public class Administrador extends Usuario {
    private String area;

    public Administrador(int id, String nombre, String correo, String area) {
        super(id, nombre, correo);
        this.area = area;
    }

    public String getArea() { return area; }

    @Override
    public void mostrarInformacion() {
        System.out.println("Administrador #" + getId() + " | " + getNombre()
                + " | " + getCorreo() + " | Area: " + area);
    }
}