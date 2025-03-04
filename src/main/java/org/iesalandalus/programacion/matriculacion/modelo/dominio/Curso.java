package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Curso {
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    private String cadenaAMostrar;


    private Curso(String cadenaAMostrar){
        this.cadenaAMostrar =cadenaAMostrar;
    }

    public String imprimir(){
        int digito = this.ordinal();
        return digito + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Curso= " + cadenaAMostrar;
    }
}
