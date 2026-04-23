package ito.app;

import ito.data.Alumno;
import ito.data.Genero;
import ito.persistencia.GestionPersistencia;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MyApp {


    static void guardarALumnos(ArrayList<Alumno> a) throws FileNotFoundException {
        GestionPersistencia gp= new GestionPersistencia("alumnos.txt");
        gp.guardaDatos(a);
    }

    static void crearAlumnos() throws FileNotFoundException {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        alumnos.add(new Alumno(3627462L,"Juan Perez",(byte)2,"QUIMICA",78.3f,Genero.MASCULINO));
        alumnos.add(new Alumno(3627443,"Pedro Perez",(byte)5,"QUIMICA",78.3f,Genero.MASCULINO));
        alumnos.add(new Alumno(3627423,"Teresa Perez",(byte)4,"QUIMICA",78.3f,Genero.FEMENINO));
        alumnos.add(new Alumno(3622362,"Roberto Perez",(byte)2,"QUIMICA",78.3f,Genero.MASCULINO));
        alumnos.add(new Alumno(3627462,"Lucia Perez",(byte)2,"QUIMICA",78.3f,Genero.FEMENINO));
        alumnos.add(new Alumno(34327462,"Fabiola Perez",(byte)4,"QUIMICA",78.3f,Genero.FEMENINO));
        alumnos.add(new Alumno(37657462,"Guillermo Perez",(byte)3,"QUIMICA",78.3f,Genero.MASCULINO));
        alumnos.add(new Alumno(3600462,"Juana Perez",(byte)5,"QUIMICA",78.3f,Genero.FEMENINO));
        alumnos.add(new Alumno(3690462,"Leopoldo Perez",(byte)9,"QUIMICA",78.3f,Genero.MASCULINO));
        alumnos.add(new Alumno(36344462,"Hugo Perez",(byte)12,"QUIMICA",78.3f,Genero.MASCULINO));
        guardarALumnos(alumnos);

    }

    static void leerAlumnos() throws FileNotFoundException {
        GestionPersistencia gp= new GestionPersistencia("alumnos.txt");
        ArrayList<Alumno> a=gp.leerDatos();
        System.out.printf("%d es la cantidad de alumnos procesados\n",a.size());

    }
    public static void main(String[] args) throws FileNotFoundException {
          //crearAlumnos();
        leerAlumnos();

    }
}
