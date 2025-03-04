package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA("INFORMATICA"),
    SISTEMAS("SISTEMAS"),
    FOL("FOL");


    private String cadenaAMostrar;

    private EspecialidadProfesorado(String cadenaAMostrar){
        this.cadenaAMostrar =cadenaAMostrar;
    }

    public String imprimir(){
        int digito = this.ordinal();
        return digito + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Especialidad= " + cadenaAMostrar;
    }


}
