package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class CiclosFormativos {

    private List<CicloFormativo> coleccionCiclosFormativos; // Modificado a ArrayList

    public CiclosFormativos() {
        coleccionCiclosFormativos = new ArrayList<>();
    }

    public List<CicloFormativo> get(){
        return copiaProfundaCiclosFormativos();
    }

    private List<CicloFormativo>  copiaProfundaCiclosFormativos(){
        List<CicloFormativo> copia = new ArrayList<>();
        for (CicloFormativo cicloFormativo : coleccionCiclosFormativos) {
            copia.add(new CicloFormativo(cicloFormativo));
        }
        return copia;
    }

    // Método para obtener el tamaño
    public int getTamano() {
        return coleccionCiclosFormativos.size();
    }


    // Método para insertar
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }

        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
        coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo));
    }



    // Método para buscar un Ciclo Formativo
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un cicloFormativo nulo.");
        }
        int indice = coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice == -1) {
            return null;
        }
        return new CicloFormativo(coleccionCiclosFormativos.get(indice));
    }

    // Método para borrar un Ciclo Formativo
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        if (!coleccionCiclosFormativos.remove(cicloFormativo)) {
            throw new OperationNotSupportedException("El ciclo formativo a borrar no existe.");
        }
    }

}
