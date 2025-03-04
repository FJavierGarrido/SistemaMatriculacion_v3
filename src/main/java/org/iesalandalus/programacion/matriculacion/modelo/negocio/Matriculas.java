package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;


public class Matriculas {

    private List<Matricula> coleccionMatriculas;


    public Matriculas(){
        coleccionMatriculas = new ArrayList<>();
    }

    public List<Matricula> get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private List<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {
        List<Matricula> copia = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            copia.add(new Matricula(matricula));
        }
        return copia;
    }


    // Método para insertar
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }

        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }

        coleccionMatriculas.add(new Matricula(matricula));

    }
    // Método para obtener el tamaño
    public int getTamano() {
        return coleccionMatriculas.size();
    }

    // Método para buscar una Matrícula
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar un matrícula nula.");
        }
        int indice = coleccionMatriculas.indexOf(matricula);
        return (indice != -1) ? new Matricula(coleccionMatriculas.get(indice)) : null;
    }

    // Método para borrar un matrícula
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        if (!coleccionMatriculas.remove(matricula)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }

    }


    // Método para obtener las matrículas de un alumno específico
    public List<Matricula> get(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");

        }

        List<Matricula> resultado = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getAlumno().equals(alumno)) {
                resultado.add(new Matricula(matricula));
            }
        }

        return resultado;
    }

    // Método para obtener las matrículas de un curso académico específico
    public List<Matricula> get(String cursoAcademico) throws OperationNotSupportedException {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico no puede ser nulo.");
        }

        List<Matricula> resultado = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                resultado.add(new Matricula(matricula));
            }
        }

        return resultado;
    }

    // Método para obtener las matrículas de un ciclo formativo específico
    public List<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }

        List<Matricula> resultado = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {
                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    resultado.add(new Matricula(matricula));
                    break;  // Si encontramos una asignatura del ciclo, no necesitamos seguir buscando en esta matrícula
                }
            }
        }
        return resultado;
    }


}
