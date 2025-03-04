package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Grado {

    GDCFGB("GDCFGB"),
    GDCFGM("GDCFGM"),
    GDCFGS("GDCFGS");

    private String cadenaAMostrar;


    private Grado(String cadenaAMostrar){
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
