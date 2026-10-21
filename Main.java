import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RegistroNotas gestor = new RegistroNotas();

    public static void main(String[] args) {
        cargarDatosDePrueba();
        demostrarPolimorfismo();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                switch (opcion) {
                    case 1: crearEstudiante(); break;
                    case 2: listarEstudiantes(); break;
                    case 3: buscarEstudiante(); break;
                    case 4: actualizarEstudiante(); break;
                    case 5: eliminarEstudiante(); break;
                    case 6: crearMateria(); break;
                    case 7: listarMaterias(); break;
                    case 8: actualizarMateria(); break;
                    case 9: eliminarMateria(); break;
                    case 10: crearNota(); break;
                    case 11: listarNotas(); break;
                    case 12: demostrarPolimorfismo(); break;
                    case 0: System.out.println("Saliendo del sistema. Hasta pronto."); break;
                    default: System.out.println("Opcion no valida.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\\n========== BACKEND ACADEMICO | MOMENTO 2 ==========");
        System.out.println("1. Crear estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante por ID");
        System.out.println("4. Actualizar estudiante");
        System.out.println("5. Eliminar estudiante");
        System.out.println("6. Crear materia");
        System.out.println("7. Listar materias");
        System.out.println("8. Actualizar materia");
        System.out.println("9. Eliminar materia");
        System.out.println("10. Registrar nota");
        System.out.println("11. Listar notas");
        System.out.println("12. Demostrar polimorfismo (usuarios)");
        System.out.println("0. Salir");
    }

    private static void crearEstudiante() {
        int id = leerEntero("ID del estudiante: ");
        String nombre = leerTexto("Nombre: ");
        String documento = leerTexto("Documento: ");
        String correo = leerTexto("Correo: ");
        gestor.guardar(new Estudiantes(id, nombre, documento, correo));
        System.out.println("Estudiante guardado correctamente.");
    }

    private static void listarEstudiantes() {
        List<Estudiantes> lista = gestor.listarEstudiantes();
        if (lista.isEmpty()) { System.out.println("No hay estudiantes registrados."); return; }
        System.out.println("--- Estudiantes ---");
        for (Estudiantes e : lista) System.out.println(e);
    }

    private static void buscarEstudiante() {
        int id = leerEntero("ID que desea buscar: ");
        Estudiantes e = gestor.buscar(id);
        System.out.println(e == null ? "No se encontro el estudiante." : e);
    }

    private static void actualizarEstudiante() {
        int id = leerEntero("ID del estudiante que desea actualizar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        String documento = leerTexto("Nuevo documento: ");
        String correo = leerTexto("Nuevo correo: ");
        System.out.println(gestor.actualizarEstudiante(id, nombre, documento, correo)
                ? "Estudiante actualizado." : "No existe un estudiante con ese ID.");
    }

    private static void eliminarEstudiante() {
        int id = leerEntero("ID del estudiante que desea eliminar: ");
        System.out.println(gestor.eliminar(id) ? "Estudiante eliminado." : "No existe un estudiante con ese ID.");
    }

    private static void crearMateria() {
        int id = leerEntero("ID de la materia: ");
        String nombre = leerTexto("Nombre de la materia: ");
        int creditos = leerEntero("Numero de creditos: ");
        if (creditos <= 0) throw new IllegalArgumentException("Los creditos deben ser mayores que cero.");
        gestor.guardarMateria(new Materia(id, nombre, creditos));
        System.out.println("Materia guardada correctamente.");
    }

    private static void listarMaterias() {
        List<Materia> lista = gestor.listarMaterias();
        if (lista.isEmpty()) { System.out.println("No hay materias registradas."); return; }
        System.out.println("--- Materias ---");
        for (Materia m : lista) System.out.println(m);
    }

    private static void actualizarMateria() {
        int id = leerEntero("ID de la materia que desea actualizar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        int creditos = leerEntero("Nuevos creditos: ");
        if (creditos <= 0) throw new IllegalArgumentException("Los creditos deben ser mayores que cero.");
        System.out.println(gestor.actualizarMateria(id, nombre, creditos)
                ? "Materia actualizada." : "No existe una materia con ese ID.");
    }

    private static void eliminarMateria() {
        int id = leerEntero("ID de la materia que desea eliminar: ");
        System.out.println(gestor.eliminarMateria(id) ? "Materia eliminada." : "No existe una materia con ese ID.");
    }

    private static void crearNota() {
        int estudianteId = leerEntero("ID del estudiante: ");
        int materiaId = leerEntero("ID de la materia: ");
        double valor = leerDecimal("Nota (0.0 a 5.0): ");
        System.out.println(gestor.guardarNota(estudianteId, materiaId, valor)
                ? "Nota registrada." : "No se encontro el estudiante o la materia indicada.");
    }

    private static void listarNotas() {
        List<RegistroNotas.Nota> lista = gestor.listarNotas();
        if (lista.isEmpty()) { System.out.println("No hay notas registradas."); return; }
        System.out.println("--- Registros de notas ---");
        for (RegistroNotas.Nota nota : lista) System.out.println(nota);
    }

    private static void demostrarPolimorfismo() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Administrador(1, "Admin de prueba", "admin@institucion.edu", "Academica"));
        usuarios.add(new Cliente(2, "Cliente de prueba", "cliente@institucion.edu", "EST-002"));
        System.out.println("\\n--- Demostracion de polimorfismo ---");
        for (Usuario usuario : usuarios) {
            usuario.mostrarInformacion();
        }
    }

    private static void cargarDatosDePrueba() {
        gestor.guardar(new Estudiantes(101, "Ana Torres", "1001", "ana@correo.com"));
        gestor.guardar(new Estudiantes(102, "Luis Perez", "1002", "luis@correo.com"));
        gestor.guardarMateria(new Materia(201, "Programacion", 3));
        gestor.guardarMateria(new Materia(202, "Bases de datos", 4));
        gestor.guardarNota(101, 201, 4.5);
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try { return Integer.parseInt(entrada); }
            catch (NumberFormatException ex) { System.out.println("Ingrese un numero entero valido."); }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().replace(',', '.');
            try { return Double.parseDouble(entrada); }
            catch (NumberFormatException ex) { System.out.println("Ingrese un numero valido."); }
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("El campo no puede estar vacio.");
        }
    }
}