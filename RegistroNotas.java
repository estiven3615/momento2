import java.util.ArrayList;
import java.util.List;

public class RegistroNotas implements IOperaciones<Estudiantes> {
    private final List<Estudiantes> estudiantes = new ArrayList<>();
    private final List<Materia> materias = new ArrayList<>();
    private final List<Nota> notas = new ArrayList<>();

    @Override
    public void guardar(Estudiantes estudiante) {
        if (estudiante == null) throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        if (buscar(estudiante.getId()) != null)
            throw new IllegalArgumentException("Ya existe un estudiante con ese ID.");
        estudiantes.add(estudiante);
    }

    @Override
    public Estudiantes buscar(int id) {
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getId() == id) return estudiante;
        }
        return null;
    }

    @Override
    public boolean eliminar(int id) {
        Estudiantes estudiante = buscar(id);
        if (estudiante == null) return false;
        estudiantes.remove(estudiante);
        notas.removeIf(nota -> nota.getEstudianteId() == id);
        return true;
    }

    public boolean actualizarEstudiante(int id, String nombre, String documento, String correo) {
        Estudiantes estudiante = buscar(id);
        if (estudiante == null) return false;
        estudiante.setNombre(nombre);
        estudiante.setDocumento(documento);
        estudiante.setCorreo(correo);
        return true;
    }

    public List<Estudiantes> listarEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public void guardarMateria(Materia materia) {
        if (materia == null) throw new IllegalArgumentException("La materia no puede ser nula.");
        if (buscarMateria(materia.getId()) != null)
            throw new IllegalArgumentException("Ya existe una materia con ese ID.");
        materias.add(materia);
    }

    public Materia buscarMateria(int id) {
        for (Materia materia : materias) {
            if (materia.getId() == id) return materia;
        }
        return null;
    }

    public boolean eliminarMateria(int id) {
        Materia materia = buscarMateria(id);
        if (materia == null) return false;
        materias.remove(materia);
        notas.removeIf(nota -> nota.getMateriaId() == id);
        return true;
    }

    public boolean actualizarMateria(int id, String nombre, int creditos) {
        Materia materia = buscarMateria(id);
        if (materia == null) return false;
        materia.setNombre(nombre);
        materia.setCreditos(creditos);
        return true;
    }

    public List<Materia> listarMaterias() {
        return new ArrayList<>(materias);
    }

    public boolean guardarNota(int estudianteId, int materiaId, double valor) {
        if (buscar(estudianteId) == null || buscarMateria(materiaId) == null) return false;
        if (valor < 0 || valor > 5) throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0.");
        notas.add(new Nota(estudianteId, materiaId, valor));
        return true;
    }

    public List<Nota> listarNotas() {
        return new ArrayList<>(notas);
    }

    public static class Nota {
        private final int estudianteId;
        private final int materiaId;
        private final double valor;

        public Nota(int estudianteId, int materiaId, double valor) {
            this.estudianteId = estudianteId;
            this.materiaId = materiaId;
            this.valor = valor;
        }

        public int getEstudianteId() { return estudianteId; }
        public int getMateriaId() { return materiaId; }
        public double getValor() { return valor; }

        @Override
        public String toString() {
            return "Estudiante ID: " + estudianteId + " | Materia ID: "
                    + materiaId + " | Nota: " + String.format("%.2f", valor);
        }
    }
}