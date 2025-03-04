package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Consola {


    // Constructor privado para evitar instanciación
    private Consola() {
    }

    public static void mostrarMenu() {
        System.out.println("\n----------  MENÚ  ----------");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion() {
        int opcion = 0;
        try {
            do {
                System.out.println("\nIntroduce el número de opción: ");
                opcion = Entrada.entero();
            } while (opcion < 0 || opcion >= Opcion.values().length);
        } catch (Exception e) {
            System.out.println("No ha introducido un número correcto del menú: " + e.getMessage());
        }

        // Devuelve la instancia de Opcion correspondiente al valor entero
        return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno(){
        System.out.println("\n----  Ingresar datos del Alumno  ----");


        System.out.println("Ingrese el nombre: ");
        String nombre = Entrada.cadena();
        System.out.println("Ingrese el DNI: ");
        String dni = Entrada.cadena();
        System.out.println("Ingrese el email: ");
        String correo = Entrada.cadena();
        System.out.println("Ingrese el teléfono: ");
        String telefono = Entrada.cadena();
        System.out.println("Ingrese la fecha de nacimiento: (dd/mm/yyyy)");
        String mensaje = Entrada.cadena();

        LocalDate fecha = leerFecha(mensaje);

        return new Alumno(nombre, dni, correo, telefono, fecha);
    }

    public static Alumno getAlumnoPorDni() {

        System.out.println("\nIngrese el DNI: ");
        String dni = Entrada.cadena();


        // Crear un alumno con los datos ficticios y el DNI proporcionado
        return new Alumno("nombre", dni, "correo@correo.es", "950303030", LocalDate.of(1986,4, 29));
    }

    public static LocalDate leerFecha(String mensaje) {
        if (mensaje==null){
            throw new NullPointerException("Error: La fecha no puede ser nula");
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(mensaje, formato);

        return fecha;
    }

    public static Grado leerGrado(){
        System.out.println("\n--- Grados disponibles ---");
        Grado[] grados = Grado.values();
        for (int i = 0; i < grados.length; i++) {
            System.out.println(i + ". " + grados[i].toString());
        }

        do {
            System.out.print("\nElige el número del grado: ");
            int opcion = Entrada.entero();

            if (opcion < 0 || opcion >= grados.length) {
                    System.out.println("ERROR: Opción no válida.");
            } else {
                    return grados[opcion];
            }
        } while (true);
    }


    public static CicloFormativo leerCicloFormativo(){

        System.out.println("\n--- Introduce los datos del ciclo formativo ---");

        System.out.println("Introduce el código del ciclo formativo: (Número de cuatro dígitos)");
        int codigo = Entrada.entero();

        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();
        String familiaProfesional = especialidadProfesorado.toString();

        Grado grado = leerGrado();
        String nombre = grado.toString();

        System.out.print("\nNúmero de horas del ciclo formativo: ");
        int horas = Integer.parseInt(Entrada.cadena());


        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    public static void mostrarCiclosFormativos(List<CicloFormativo> ciclosFormativos) {
        if (ciclosFormativos == null) {
            throw new IllegalArgumentException("Los ciclos formativos no pueden ser nulos.");
        }

        System.out.println("\n--- Ciclos Formativos Registrados ---");

        // Obtenemos la copia profunda del array de ciclos formativos
        if (!ciclosFormativos.isEmpty()) {
            for (CicloFormativo ciclo : ciclosFormativos) {
                if (ciclo != null) {
                    System.out.println(ciclo.toString());
                }
            }
        } else {
            System.out.println("No hay ciclos formativos registrados en el sistema.");
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo()  {

        System.out.println("\nIntroduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();

        return new CicloFormativo(codigo, "Desarrollo de Aplicaciones Web", Grado.GDCFGB,"DAW", 2000);

    }

    public static Curso leerCurso() {
        System.out.println("\n----  Lista de cursos existentes   ----");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }

        System.out.println("Introduce el número correspondiente al curso:");
        int opcion;
        do {
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Curso.values().length);
        return Curso.values()[opcion];

    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("\n---  Lista de especialidades existentes  ---");

        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.imprimir());
        }

        System.out.println("Introduce el número correspondiente a la especialidad:");
        int opcion;
        do {
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= EspecialidadProfesorado.values().length);
        return EspecialidadProfesorado.values()[opcion];
    }

    public static Asignatura leerAsignatura() {

        System.out.println("Introduce el código de la asignatura:");
        String codigo = Entrada.cadena();
        System.out.println("Introduce el nombre de la asignatura:");
        String nombre = Entrada.cadena();
        System.out.println("Introduce las horas anuales de la asignatura:");
        int horasAnuales = Entrada.entero();
        System.out.println("Introduce las horas de desdoble de la asignatura:");
        int horasDesdoble = Entrada.entero();

        Curso curso = leerCurso();
        EspecialidadProfesorado especialidad = leerEspecialidadProfesorado();
        CicloFormativo cicloFormativo = getCicloFormativoPorCodigo();

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidad, cicloFormativo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        System.out.println("\nIntroduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        //Datos ficticios de cicloFormativo
        CicloFormativo cicloFormativo = new CicloFormativo(1234, "Familia Ficticia", Grado.GDCFGB, "Ciclo Ficticio", 2000);

        //Datos ficticios de todo excepto código
        return new Asignatura(codigo, "Asignatura Ficticia", 100, Curso.PRIMERO, 10, EspecialidadProfesorado.INFORMATICA, cicloFormativo);


        }

    private static void mostrarAsignaturas(List<Asignatura> asignaturas) {
        if (asignaturas == null || asignaturas.isEmpty()) {
            System.out.println("No hay asignaturas disponibles.");
        } else {
            System.out.println("\n----  Listado de Asignaturas  ----");
            for (Asignatura asignatura : asignaturas) {
                System.out.println(asignatura.imprimir());
            }
        }
    }

    protected static boolean asignaturaYaMatriculada(List<Asignatura> asignaturasMatricula, Asignatura asignatura) {
        if (asignaturasMatricula == null) {
            throw new NullPointerException("ERROR: Asignatura matricula es nula.");
        }

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede comprobar la matrícula de una asignatura nula.");
        }

        for (Asignatura asignaturaActual : asignaturasMatricula) {
            if (asignaturaActual != null && asignaturaActual.getCodigo().equals(asignatura.getCodigo())) {
                return true;
            }
        }

        return false;
    }



    public static Matricula leerMatricula() throws Exception {
        System.out.println("\n----  Introduce los datos de la matrícula  ----");

        System.out.println("Identificador de la matrícula:");
        int idMatricula = Entrada.entero();

        System.out.println("Curso académico (formato XX-XX):");
        String cursoAcademico = Entrada.cadena();

        System.out.println("Fecha de matriculación (formato dd/MM/yyyy):");
        String fecha = Entrada.cadena();
        LocalDate fechaMatriculacion = leerFecha(fecha);

        System.out.println("\n--- Datos del alumno ---");
        Alumno alumno = leerAlumno();

        System.out.println("\nIntroduce las asignaturas (máximo " + Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + "):");
        List<Asignatura> asignaturas = new ArrayList<>();
        int numAsignaturas = 0;

        while (numAsignaturas < Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            System.out.println("¿Desea añadir una asignatura? (S/N):");
            String respuesta = Entrada.cadena();
            if (respuesta.equalsIgnoreCase("N")) {
                break;
            }

            Asignatura nuevaAsignatura = leerAsignatura();
            if (!asignaturaYaMatriculada(asignaturas, nuevaAsignatura)) {
                asignaturas.add(nuevaAsignatura);
                numAsignaturas++;
            } else {
                System.out.println("ERROR: La asignatura ya está incluida en la matrícula.");
            }
        }

        // Crear array final solo con las asignaturas añadidas
        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturas);

    }

    public static Matricula getMatriculaPorIdentificador() throws Exception {
        System.out.println("\nIntroduce el identificador de la matrícula:");
        int idMatricula = Entrada.entero();

        /*// Creamos datos ficticios que cumplan las restricciones
        LocalDate fechaMatriculacion = LocalDate.now().minusDays(1);
        Alumno alumno = new Alumno("Alumno Ficticio", "11111111A", "alumno@ejemplo.com", "666666666", LocalDate.now().minusYears(20));

        // Crear una asignatura ficticia que cumpla las restricciones
        CicloFormativo cicloFicticio = new CicloFormativo(1234, "Familia Ficticia", Grado.GDCFGB, "Ciclo Ficticio", 2000);
        Asignatura asignaturaFicticia = new Asignatura("1234", "Asignatura Ficticia", 100, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFicticio);

        List<Asignatura> asignaturas = new ArrayList<>();
        asignaturas.add(asignaturaFicticia);

        return new Matricula(idMatricula, "23-24", fechaMatriculacion, alumno, asignaturas);*/

        // Crear una Matricula "ficticia" SÓLO con el ID para la búsqueda
        return new Matricula(idMatricula, null, null, null, null);
    }
}
