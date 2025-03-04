package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Alumnos {

    private List<Alumno> coleccionAlumnos;


    // Constructor sin parámetros (ya no necesitamos la capacidad)
    public Alumnos() {
        this.coleccionAlumnos = new ArrayList<>();
    }

    // Método get para obtener una copia profunda de la colección
    public List<Alumno> get() {
        return copiaProfundaAlumnos();
    }

    // Método que realiza una copia profunda de la colección de alumnos
    private List<Alumno> copiaProfundaAlumnos() {
        List<Alumno> copia = new ArrayList<>();
        for (Alumno alumno : coleccionAlumnos) {
            copia.add(new Alumno(alumno)); // Usando el constructor de copia de Alumno
        }
        return copia;
    }

    // Método para insertar un alumno
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }

        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }

        coleccionAlumnos.add(new Alumno(alumno));  //Añado el objeto
    }


    // Método para buscar un alumno
    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        int indice = coleccionAlumnos.indexOf(alumno);
        if (indice != -1){
            return new Alumno(coleccionAlumnos.get(indice)); //Devuelvo la copia profunda
        }else{
            return null;
        }
    }

    // Método para borrar un alumno
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        if (!coleccionAlumnos.remove(alumno)){
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
    }

}
