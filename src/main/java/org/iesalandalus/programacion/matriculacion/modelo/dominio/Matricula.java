package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Matricula {

    public static final int MAXIMO_MESES_ANTERIOR_ANULACION=6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA=15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA=1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA=10;
    private static final String ER_CURSO_ACADEMICO = "\\d{2}-\\d{2}";
    public static final String FORMATO_FECHA = "dd-MM-yyyy";

    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private List<Asignatura> coleccionAsignaturas; // Modificado a ArrayList



    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, List<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);

    }

    public Matricula(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No es posible copiar una matrícula nula.");
        }

        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(matricula.getColeccionAsignaturas());

    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {

        if (idMatricula<=0) {
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }

        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {

        if (cursoAcademico==null){
            throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        }if (cursoAcademico.trim().isEmpty()){
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
        }if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null) {
            throw new NullPointerException("ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
        }
        if (fechaMatriculacion.isBefore(LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        this.fechaMatriculacion = LocalDate.parse(fechaMatriculacion.format(formatter), formatter);


    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion == null) {
            throw new NullPointerException("ERROR: La fecha de anulación no puede ser  nula.");
        }
        if (fechaMatriculacion == null) {
            throw new NullPointerException("ERROR: La fecha de matriculación no puede ser  nula.");
        }

        if (fechaAnulacion.isAfter(fechaMatriculacion.plusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede superar los 6 meses desde la matriculación.");
        }
        if (fechaAnulacion.isBefore(fechaMatriculacion)) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }
        if (fechaAnulacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }

        this.fechaAnulacion = fechaAnulacion;
    }
    public Alumno getAlumno() {
        return new Alumno(alumno);
    }

    public void setAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }


        this.alumno = alumno;
    }

    public List<Asignatura> getColeccionAsignaturas() {
        return new ArrayList<>(coleccionAsignaturas); // Devuelve una copia
    }

    public void setColeccionAsignaturas(List<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }

        // Validar el número máximo de asignaturas permitidas
        if (coleccionAsignaturas.size() > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de asignaturas permitidas ("+ MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + ").");
        }

        // Verificar si las horas totales superan el máximo permitido
        if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)) {
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas ("+ MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }



        // Copiar la colección de asignaturas
        this.coleccionAsignaturas = new ArrayList<>(coleccionAsignaturas);

    }



    private boolean superaMaximoNumeroHorasMatricula(List<Asignatura> asignaturasMatriculas) {
        int totalHoras = 0;

        // Recorremos el array de asignaturas y sumamos las horas de cada asignatura
        for (Asignatura asignatura : asignaturasMatriculas) {
            if (asignatura != null) {
                totalHoras = totalHoras + asignatura.getHorasAnuales();
            }
        }

        // Comparamos las horas totales con el máximo permitido
        return totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return idMatricula == matricula.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }

    private String asignaturasMatricula(){
        return ("cadena test");
    }

    public String imprimir(){

        return  "idMatricula=" + idMatricula +
                ", curso académico=" + cursoAcademico +
                ", fecha matriculación=" + fechaMatriculacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) +
                ", alumno={" + alumno.imprimir() +
                "}";
    }

    @Override
    public String toString() {
      /*  return  "idMatricula=" + idMatricula +
                ", curso académico=" + cursoAcademico +
                ", fecha matriculación=" + fechaMatriculacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) +
                ", alumno=" + alumno.imprimir() +
                //", fecha anulación=" + fechaAnulacion +
                ", Asignaturas={ }";

       */
        // Construir la parte de las asignaturas
        StringBuilder asignaturasStr = new StringBuilder();
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura != null) {
                // Agregar cada asignatura a la cadena
                asignaturasStr.append(asignatura.imprimir()).append(", ");
            }
        }


        // Comprobar si fechaAnulacion es null y formatearla si no lo es
        String fechaAnulacionStr = (fechaAnulacion != null) ?
                ", fecha anulación=" + fechaAnulacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) : "";

        // Crear la cadena final
        return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s%s, alumno=%s, Asignaturas={ %s}",
                idMatricula, cursoAcademico,
                fechaMatriculacion.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)),
                fechaAnulacionStr, alumno.imprimir(), asignaturasStr.toString().isEmpty() ? "" : asignaturasStr.toString());
    }
}
