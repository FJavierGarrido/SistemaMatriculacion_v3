package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Vista {

    private Controlador controlador;


    // Añadimos constructor y llamada a setvista de opcion
    public Vista() {
        Opcion.setVista(this);
    }


    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            //ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("¡Hasta luego!");
    }


    public void insertarAlumno()  {

        try {
            Alumno alumno = Consola.leerAlumno();
            controlador.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAlumno(){
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumno = controlador.buscar(alumno);

            if (alumno != null) {
                System.out.println("El alumno es: " + alumno);
            } else {
                System.out.println("No existe ningún alumno con ese dni.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarAlumno(){
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            controlador.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAlumnos(){

        try {
            List<Alumno> alumnos = controlador.getAlumnos();

            if (alumnos.isEmpty()) {
                System.out.println("No hay alumnos.");
                return;
            }

            // Ordenar alfabéticamente por nombre
            Collections.sort(alumnos, Comparator.comparing(Alumno::getNombre));

            System.out.println("\n--- Listado de Alumnos ---");
            for (Alumno a : alumnos) {
                System.out.println(a);
            }
        } catch (Exception e) {
        System.out.println(e.getMessage());
        }

    }

    public void insertarAsignatura(){
        try {
            Asignatura asignatura = Consola.leerAsignatura();
            controlador.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAsignatura(){
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignatura = controlador.buscar(asignatura);

            if (asignatura != null) {
                System.out.println("La asignatura es: " + asignatura);
            } else {
                System.out.println("No existe ninguna asignatura con ese código.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarAsignatura(){
        try {
            Asignatura asignatura = Consola.leerAsignatura();
            controlador.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAsignaturas(){

        try {

            List<Asignatura> asignaturas = controlador.getAsignaturas();

            if (asignaturas.isEmpty()) {
                System.out.println("No hay asignaturas.");
                return;
            }

            // Ordenar alfabéticamente por nombre
            Collections.sort(asignaturas, Comparator.comparing(Asignatura::getNombre));

            System.out.println("\n--- Listado de Asignaturas ---");
            for (Asignatura a : asignaturas) {
                System.out.println(a);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public void insertarCicloFormativo(){
        try {
            CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
            controlador.insertar(cicloFormativo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarCicloFormativo(){
        try {

            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            cicloFormativo = controlador.buscar(cicloFormativo);

            if (cicloFormativo != null) {
                System.out.println("El Ciclo Formativo es: " + cicloFormativo);
            } else {
                System.out.println("No existe ningún ciclo formativo con ese código.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarCicloFormativo(){
        try {
            mostrarCiclosFormativos();
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            controlador.borrar(cicloFormativo);
            System.out.println("Ciclo Formativo borrado correctamente.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarCiclosFormativos(){

        try {
            List<CicloFormativo> ciclosFormativos = controlador.getCiclosFormativos();

            if (ciclosFormativos.isEmpty()) {
                System.out.println("No hay ciclos formativos.");
                return;
            }

            // Ordenar alfabéticamente por nombre
            Collections.sort(ciclosFormativos, Comparator.comparing(CicloFormativo::getNombre));

            System.out.println("\n--- Listado de Ciclos Formativos ---");
            for (CicloFormativo c : ciclosFormativos) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarMatricula(){
        try {
            Matricula matricula = Consola.leerMatricula();
            controlador.insertar(matricula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarMatricula(){
        try {
            List<Matricula> matriculas = controlador.getMatriculas();
            Matricula matriculaBuscada = Consola.getMatriculaPorIdentificador();

            if (matriculaBuscada != null) {
                boolean encontrada = false;
                for (Matricula matricula : matriculas) {
                    if (matricula.getIdMatricula() == matriculaBuscada.getIdMatricula()) {
                        System.out.println("Matrícula encontrada: " + matricula);
                        encontrada = true;
                        break;
                    }
                }
                if (!encontrada) {
                    System.out.println("No existe ninguna matrícula con ese identificador.");
                }
            } else {
                System.out.println("No existe ninguna matrícula con ese identificador.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void anularMatricula(){
        try {
            Matricula matricula = Consola.leerMatricula();
            controlador.borrar(matricula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMatriculas() {

        try{
            List<Matricula> matriculas = controlador.getMatriculas();

            if (matriculas.isEmpty()) {
                System.out.println("No hay matriculas.");
                return;
            }

            // Ordenar por fecha de matriculación descendente y luego por nombre del alumno
            Collections.sort(matriculas, Comparator
                    .comparing(Matricula::getFechaMatriculacion, Collections.reverseOrder())
                    .thenComparing(m -> m.getAlumno().getNombre()));


            System.out.println("\n--- Listado de Matrículas ---");
            for (Matricula m : matriculas) {
                System.out.println(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void mostrarMatriculasPorAlumno() {

        try {
            Alumno alumno = Consola.leerAlumno();
            List<Matricula> matriculas = controlador.getMatriculas(alumno);

            if (matriculas == null || matriculas.isEmpty()) {
                System.out.println("No hay matriculas para el alumno: " + alumno);
                return;
            }

            // Ordenar por fecha de matriculación descendente y luego por nombre del alumno
            Collections.sort(matriculas, Comparator
                    .comparing(Matricula::getFechaMatriculacion, Collections.reverseOrder())
                    .thenComparing(m -> m.getAlumno().getNombre()));

            System.out.println("\n--- Listado de Matrículas para el alumno " + alumno + "---");
            for (Matricula m : matriculas) {
                System.out.println(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void mostrarMatriculasPorCicloFormativo()  {

        try {
            CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
            List<Matricula> matriculas = controlador.getMatriculas(cicloFormativo);

            if (matriculas == null || matriculas.isEmpty()) {
                System.out.println("No hay matriculas para el ciclo: " + cicloFormativo);
                return;
            }

            // Ordenar por fecha de matriculación descendente y luego por nombre del alumno
            Collections.sort(matriculas, Comparator
                    .comparing(Matricula::getFechaMatriculacion, Collections.reverseOrder())
                    .thenComparing(m -> m.getAlumno().getNombre()));

            System.out.println("\n--- Listado de Matrículas para el ciclo formativo " + cicloFormativo + "---");
            for (Matricula m : matriculas) {
                System.out.println(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void mostrarMatriculasPorCursoAcademico()  {

        try {
            String curso = String.valueOf(Consola.leerCurso());
            List<Matricula> matriculas = controlador.getMatriculas(curso);

            if (matriculas == null || matriculas.isEmpty()) {
                System.out.println("No hay matriculas para el curso: " + curso);
                return;
            }

            // Ordenar por fecha de matriculación descendente y luego por nombre del alumno
            Collections.sort(matriculas, Comparator
                    .comparing(Matricula::getFechaMatriculacion, Collections.reverseOrder())
                    .thenComparing(m -> m.getAlumno().getNombre()));

            System.out.println("\n--- Listado de Matrículas para el curso académico " + curso + "---");
            for (Matricula m : matriculas) {
                System.out.println(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}