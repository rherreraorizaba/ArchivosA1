package ito.data;

/*
    Reglas del negocios
    1) El semestre debe ser un valor entre 1 y 13
    2) El promedio debe ser un valor entre 0 y 100
    3) El genero solo puede ser MASCULINO, FEMENINO o BINARIO
 */
public class Alumno {

    private long numeroControl;    // 8 bytes
    private String nombre;  // n bytes en función de la cantidad de caracteres de la cadena
    private byte semestre; // 4 bytes
    private String carrera; // n byyes en función de la cantidad de caracteres de la cadena
    private float promedio; // 4 bytes
    private Genero genero;// n bytes en funcion de la cantidad de caracteres de la cadena
    private final byte MIN_SEM=1;
    private final byte MAX_SEM=13;
    private final float MIN_PROM=0;
    private final float MAX_PROM=100;

    public Alumno(long numeroControl, String nombre, byte semestre, String carrera, float promedio, Genero genero) {
       if(semestre<MIN_SEM || semestre>MAX_SEM)
           throw new IllegalArgumentException("El semestre es invalido!!");
       if(promedio<MIN_PROM || promedio>MAX_PROM)
           throw new IllegalArgumentException("El promedio no es valido!!");

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

    public byte getSemestre() {
        return semestre;
    }

    public String getCarrera() {
        return carrera;
    }

    public float getPromedio() {
        return promedio;
    }

    public String getGenero() {
        return genero.name();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSemestre(byte semestre){
        if(semestre>=1 && semestre<=13)
            this.semestre = semestre;
        else
            throw new IllegalArgumentException("El semestre es invalido!!");
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setPromedio(float promedio) {
        if(promedio<0 || promedio>100)
            throw new IllegalArgumentException("El promedio no es valido!!");
        this.promedio = promedio;
    }

    public String toString(){
        /*String cadena=Long.toString(numeroControl);
        cadena+=","+nombre;
        cadena+=","+semestre;
        cadena+=","+carrera;
        cadena+=","+promedio;
        cadena+=","+genero;
        return cadena;*/
        return String.format("%d,%s,%d,%s,%5.2f,%s",numeroControl,nombre,semestre,carrera,promedio,genero);
    }
}
