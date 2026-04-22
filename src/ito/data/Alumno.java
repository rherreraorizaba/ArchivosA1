package ito.data;

public class Alumno {

    private long numeroControl;    // 8 bytes
    private String nombre;  // n bytes en función de la cantidad de caracteres de la cadena
    private int semestre; // 4 bytes
    private String carrera; // n byyes en función de la cantidad de caracteres de la cadena
    private float promedio; // 4 bytes
    private String genero;  // n bytes en funcion de la cantidad de caracteres de la cadena

    public Alumno(long numeroControl, String nombre, int semestre, String carrera, float promedio, String genero) {
        this.numeroControl = numeroControl;
        this.nombre = nombre;
        this.semestre = semestre;
        this.carrera = carrera;
        this.promedio = promedio;
        this.genero = genero;
    }

    public long getNumeroControl() {
        return numeroControl;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSemestre() {
        return semestre;
    }

    public String getCarrera() {
        return carrera;
    }

    public float getPromedio() {
        return promedio;
    }

    public String getGenero() {
        return genero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public String toString(){
        return ""+numeroControl+nombre+semestre+carrera+promedio+genero;
    }
}
