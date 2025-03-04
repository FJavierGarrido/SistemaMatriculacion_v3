package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;

import java.util.Objects;

public class CicloFormativo {
    public static final int MAXIMO_NUMERO_HORAS=2000;
    private int codigo=0;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;


    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas){
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo(CicloFormativo cicloFormativo){

        if (cicloFormativo==null){
            throw new NullPointerException("ERROR: No es posible copiar un ciclo formativo nulo.");
        }
        setNombre(cicloFormativo.getNombre());
        setCodigo(cicloFormativo.getCodigo());
        setFamiliaProfesional(cicloFormativo.getFamiliaProfesional());
        setGrado(cicloFormativo.getGrado());
        setHoras(cicloFormativo.getHoras());

    }

    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo){
        if (codigo <1000 || codigo>9999) {
            throw new IllegalArgumentException("ERROR: Código no válido, debe tener 4 dígitos.");
        }
        this.codigo=codigo;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional == null) {
            throw new NullPointerException("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        }
        if (familiaProfesional.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: La familia profesional no puede estar vacía.");
        }
        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado == null) {
            throw new NullPointerException("ERROR: El grado de un ciclo formativo no puede ser nulo.");
        }
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas > MAXIMO_NUMERO_HORAS || horas <=0) {
            throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a 2000.");
        }
        this.horas = horas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String imprimir(){

        String imprime="Código ciclo formativo="+ getCodigo()+ ", nombre ciclo formativo="+getNombre();
        return imprime;
    }

    @Override
    public String toString() {
        return "Código ciclo formativo=" + codigo +
                ", familia profesional=" + familiaProfesional +
                ", grado=" + grado +
                ", nombre ciclo formativo=" + nombre +
                ", horas=" + horas ;
    }
}
