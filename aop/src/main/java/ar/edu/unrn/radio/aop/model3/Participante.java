package ar.edu.unrn.radio.aop.model3;


public class Participante {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String dni;

    public Participante(String nombre, String apellido, String telefono, String email, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }
}
