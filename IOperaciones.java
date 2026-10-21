
public interface IOperaciones<T> {
    void guardar(T objeto);
    T buscar(int id);
    boolean eliminar(int id);
}