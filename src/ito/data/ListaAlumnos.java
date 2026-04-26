package ito.data;

import ito.persistencia.GestionPersistencia;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ListaAlumnos {

    private ArrayList<Alumno> alumnos;
    private GestionPersistencia gp;

    public ListaAlumnos(String nombreArchivo) {
        this.gp = new GestionPersistencia(nombreArchivo);
        recuperarDatos();
    }

    private void recuperarDatos() {
        try{
            alumnos=gp.leerDatos();
        }catch(FileNotFoundException e){
            alumnos=new ArrayList<>();
        }
    }

    public void salvarDatos() throws FileNotFoundException {
        gp.guardaDatos(alumnos);
    }

    public boolean existe(Alumno a){
        boolean existe=false;
        for(Alumno alumno:alumnos)
            if (alumno.getNumeroControl() == a.getNumeroControl()) {
                existe = true;
                break;
            }
        return existe;
    }

    public void addAlumno(Alumno alumno) {
        if(alumno!=null && !existe(alumno))
            alumnos.add(alumno);
        else
            throw new IllegalArgumentException("El alumno es invalido!!");
    }

    public void deleteAlumno(Alumno alumno) {
        if(alumnos.contains(alumno))
            alumnos.remove(alumno);
        else
            throw new IllegalArgumentException("El alumno no existe!!");
    }

    public void deleteAlumno(long numeroControl){
        boolean existe=false;
        for(Alumno alumno:alumnos)
            if(alumno.getNumeroControl() == numeroControl){
                alumnos.remove(alumno);
                existe = true;
                break;
            }
        if(!existe)
            throw new IllegalArgumentException("El alumno no existe!!");
    }

    public void deleteAlumno(int posicion){
        if(posicion>=0 && posicion<alumnos.size())
            alumnos.remove(posicion);
        else
            throw new IllegalArgumentException("El alumno no existe!!");
    }

    public Alumno getAlumno(long numeroControl){
        Alumno alumno=null;
        for(Alumno a:alumnos)
            if(a.getNumeroControl() == numeroControl) {
                alumno = a;
                break;
            }
        return alumno;
    }

    public Alumno getAlumno(int posicion){
        if(posicion>=0 && posicion<alumnos.size())
            return alumnos.get(posicion);
        else
            throw new IllegalArgumentException("El alumno no existe!!");
    }

    public ArrayList<Alumno>  getAlumnos(){
        return alumnos;
    }

}
