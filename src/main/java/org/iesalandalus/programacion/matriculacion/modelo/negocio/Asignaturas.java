package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Asignaturas {

    private List<Asignatura> coleccionAsignaturas;

    public Asignaturas() {
        coleccionAsignaturas = new ArrayList<>();
    }

    public List<Asignatura> get() {
        return copiaProfundaAsignaturas();
    }
    private List<Asignatura>  copiaProfundaAsignaturas(){
        List<Asignatura> copia = new ArrayList<>();
        for (Asignatura asignatura : coleccionAsignaturas) {
            copia.add(new Asignatura(asignatura));
        }
        return copia;
    }
    // Método para obtener el tamaño
    public int getTamano() {
        return coleccionAsignaturas.size();
    }

    // Método para insertar
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }

        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }

        coleccionAsignaturas.add(new Asignatura(asignatura));
    }


    // Método para buscar una Asignatura
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        int indice = coleccionAsignaturas.indexOf(asignatura);
        if (indice == -1) {
            return null;
        }
        return new Asignatura(coleccionAsignaturas.get(indice));
    }

    // Método para borrar un asignatura
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        if (!coleccionAsignaturas.remove(asignatura)) {
            throw new OperationNotSupportedException("La asignatura a borrar no existe.");
        }
    }

}
