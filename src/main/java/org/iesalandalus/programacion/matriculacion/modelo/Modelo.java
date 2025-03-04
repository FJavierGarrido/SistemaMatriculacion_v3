package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;
import javax.naming.OperationNotSupportedException;
import java.util.List;


public class Modelo {

    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;



    public void comenzar(){
        //Instancias de las clases de negocio
        this.alumnos = new Alumnos();
        this.asignaturas = new Asignaturas();
        this.ciclosFormativos = new CiclosFormativos();
        this.matriculas = new Matriculas();
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void insertar (Alumno alumno) throws Exception {
        if(alumno == null)
        {
            throw new Exception("ERROR: No se puede insertar un alumno nulo.");
        }
        alumnos.insertar(new Alumno(alumno)); //Copia profunda
    }

    public void insertar (Asignatura asignatura) throws Exception {
        if(asignatura == null)
        {
            throw new Exception("ERROR: No se puede insertar una asignatura nula.");
        }
        asignaturas.insertar(new Asignatura(asignatura)); //Copia profunda
    }

    public void insertar (CicloFormativo cicloFormativo) throws Exception {
        if(cicloFormativo == null)
        {
            throw new Exception("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        ciclosFormativos.insertar(new CicloFormativo(cicloFormativo)); //Copia profunda
    }

    public void insertar (Matricula matricula) throws Exception {
        if(matricula == null)
        {
            throw new Exception("ERROR: No se puede insertar una matricula nula.");
        }
        matriculas.insertar(new Matricula(matricula));  //Copia profunda
    }

    public Alumno buscar(Alumno alumno){
        Alumno alumnoEncontrado = alumnos.buscar(alumno);
        return (alumnoEncontrado != null) ? new Alumno(alumnoEncontrado) : null; //Copia profunda si existe, sino null
    }

    public Asignatura buscar(Asignatura asignatura) throws OperationNotSupportedException {
        if(asignatura == null)
        {
            throw new OperationNotSupportedException("ERROR: No se puede buscar una asignatura nula.");
        }
        Asignatura asignaturaEncontrada = asignaturas.buscar(asignatura);
        return (asignaturaEncontrada != null) ? new Asignatura(asignaturaEncontrada) : null; //Copia profunda si existe, sino null
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {

        if(cicloFormativo == null)
        {
            throw new OperationNotSupportedException("ERROR: No se puede buscar un ciclo formativo nula.");
        }
        CicloFormativo cicloFormativoEncontrado = ciclosFormativos.buscar(cicloFormativo);
        return (cicloFormativoEncontrado != null) ? new CicloFormativo(cicloFormativoEncontrado) : null; //Copia profunda si existe, sino null
    }

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        if(matricula == null)
        {
            throw new OperationNotSupportedException("ERROR: No se puede buscar una matricula nula.");
        }
        Matricula matriculaEncontrada = matriculas.buscar(matricula);
        return (matriculaEncontrada != null) ? new Matricula(matriculaEncontrada) : null; //Copia profunda si existe, sino null
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if(alumno == null)
        {
            throw new OperationNotSupportedException("ERROR: No se puede borrar un alumno nulo.");
        }
        alumnos.borrar(alumno);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if(asignatura == null)
        {
            throw new OperationNotSupportedException("ERROR: No se puede borrar una asignatura nula.");
        }
        asignaturas.borrar(asignatura);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if(cicloFormativo == null)
        {
            throw new OperationNotSupportedException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        ciclosFormativos.borrar(cicloFormativo);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if(matricula == null)
        {
            throw new OperationNotSupportedException("ERROR: No se puede borrar una matricula nula.");
        }
        matriculas.borrar(matricula);
    }

    public List<Alumno> getAlumnos() {
        return alumnos.get();
    }

    public List<Asignatura> getAsignaturas(){
        return asignaturas.get();
    }

    public List<CicloFormativo> getCiclosFormativos(){
        return ciclosFormativos.get();
    }

    public List<Matricula> getMatriculas() throws OperationNotSupportedException {
        return matriculas.get();
    }

    public List<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }

        return matriculas.get(alumno);
    }

    public List<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }

        return matriculas.get(cicloFormativo);
    }

    public List<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico no puede ser nulo.");
        }

        return matriculas.get(cursoAcademico);
    }
}
