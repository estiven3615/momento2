
public class Cliente extends Usuario {
    private String codigo;

    public Cliente(int id, String nombre, String correo, String codigo) {
        super(id, nombre, correo);
        this.codigo = codigo;
    }

    public String getCodigo() { return codigo; }

    @Override
    public void mostrarInformacion() {
        System.out.println("Cliente #" + getId() + " | " + getNombre()
                + " | " + getCorreo() + " | Codigo: " + codigo);
    }
}