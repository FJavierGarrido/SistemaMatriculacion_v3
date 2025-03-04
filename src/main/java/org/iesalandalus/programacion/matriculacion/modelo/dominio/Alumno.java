package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
    private static final String ER_TELEFONO= "[6|7|8|9][0-9]{8}";
    private static final String ER_CORREO="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String ER_DNI= "([0-9]{8})([a-zA-Z])";
    public static final String FORMATO_FECHA="\\d{2}/\\d{2}/\\d{4}";
    private static final String ER_NIA= "([a-z]{4})([0-9]{2})([a-zA-Z]{1})";
    private static final int MIN_EDAD_ALUMNADO = 16;

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;


    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento){
        setNombre(nombre);
        setDni(dni);
        setNia(); //una vez tengamos nombre y dni, generamos el Nia
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    public Alumno(Alumno alumno){

        if (alumno==null){
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }

        //Llama a los métodos set en lugar de asignar directamente
        setNombre(alumno.getNombre());
        setDni(alumno.getDni());
        setCorreo(alumno.getCorreo());
        setTelefono(alumno.getTelefono());
        setFechaNacimiento(alumno.getFechaNacimiento());
        this.nia = alumno.getNia();
    }

    public String getNia() {
        return nia;
    }

    private void setNia() {;
        if (this.nombre != null && this.dni != null) {
            if (dni.length() < 8) {
                throw new IllegalArgumentException("ERROR: El DNI debe tener al menos 8 caracteres.");
            }
            String primeraParte = nombre.replaceAll("\\s+", "").substring(0, 4).toLowerCase(); //quito los espacios, cojo los 4 primeros y los paso a minúscula
            String ultimaParte = dni.substring(5, 8); // Últimos tres dígitos del DNI
            this.nia = primeraParte + ultimaParte;
        }else {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        }
    }

    private void setNia(String nia) {
        if (nia == null || nia.trim().isEmpty()) {
            throw new NullPointerException("ERROR: Las Iniciales no puede ser nulas");
        }

        if (nia.trim().length() < 7) {
            throw new IllegalArgumentException("ERROR:El NIA debe tener al menos 7 caracteres."); //4 letras de nombre + 3 de DNI
        }
        this.nia = nia;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre=formateaNombre(nombre);

    }


    private String formateaNombre(String nombre){

        if (nombre==null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()){
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }

        nombre = nombre.trim();    //elimina espacios en blanco del principio y final y divide palabras
        String[] palabras =nombre.split("\\s");  //divide cada palabra separada por espacio
        StringBuilder nombreFormateado = new StringBuilder(); //creo StringBuilder para tratar las palabras en lugar de concatenarlas con +

        //recorre cada palabra y pone mayúscula la primera letra, minúsculas las demás y un espacio al final
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase(); //en cada palabra, la primera letra en mayúscula y demás en minúscula

                // Añade la palabra formateada al StringBuilder separada por un espacio
                nombreFormateado.append(palabra).append(" ");
            }
        }
        return nombreFormateado.toString().trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        }

        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }

        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni){

        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        }

        if (comprobarLetraDni(dni)){
            this.dni=dni;
        }




    }


    private boolean comprobarLetraDni(String dni) {

        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        }

        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }


        // Expresión regular para validar un DNI español (8 dígitos y una letra al final)
        Pattern pattern = Pattern.compile(ER_DNI);
        Matcher matcher = pattern.matcher(dni);

        // Verificar si el formato del DNI es correcto
        if (matcher.matches()) {
            // Obtener el número y la letra del DNI usando grupos
            String numeroDni = matcher.group(1);
            String letraDni = matcher.group(2).toUpperCase(); // Convertir a mayúsculas

            // Calcular la letra correspondiente al número del DNI directamente
            String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";

            // Verificar si la letra pasada es válida
            char letraEsperada = letrasValidas.charAt(Integer.parseInt(numeroDni) % 23);
            if (letraDni.charAt(0) == letraEsperada) {
                return true;
            } else {
                throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
            }
        } else {
            // El formato del DNI no es válido
            throw new IllegalArgumentException("Formato de DNI no válido");
        }
    }

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");

        }
        String fechaComoCadena = fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (!fechaComoCadena.matches(FORMATO_FECHA)) {
            throw new IllegalArgumentException("ERROR: Formato de correo no válido");
        }

        // Comprobar que el alumnado tiene al menos 16 años
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < MIN_EDAD_ALUMNADO) {
            throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a " + MIN_EDAD_ALUMNADO + " años.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    private String getIniciales(){
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NullPointerException("Error: El nombre no puede ser nulo.");
        }
        StringBuilder iniciales = new StringBuilder();
        String[] palabras = nombre.trim().split("\\s+");
        for (String palabra : palabras) {
            iniciales.append(Character.toUpperCase(palabra.charAt(0)));
        }
        return iniciales.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public String imprimir(){
        return "metodo imprimir alumno";
    }

    @Override
    public String toString() {
        return "Número de Identificación del Alumnado " +
                "(NIA)=" + nia +
                " nombre=" + nombre +
                " (" + getIniciales() + ")"+
                ", DNI=" + dni +
                ", correo=" + correo +
                ", teléfono=" + telefono +
                ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
