package ito.app;

import ito.data.Alumno;
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
        alumnos.add(new Alumno(3627462,"Juan Perez",2,"QUIMICA",78.3f,"MASCULINO"));
        alumnos.add(new Alumno(3627443,"Pedro Perez",5,"QUIMICA",78.3f,"MASCULINO"));
        alumnos.add(new Alumno(3627423,"Teresa Perez",4,"QUIMICA",78.3f,"FEMENINO"));
        alumnos.add(new Alumno(3622362,"Roberto Perez",2,"QUIMICA",78.3f,"MASCULINO"));
        alumnos.add(new Alumno(3627462,"Lucia Perez",2,"QUIMICA",78.3f,"FEMENINO"));
        alumnos.add(new Alumno(34327462,"Fabiola Perez",4,"QUIMICA",78.3f,"FEMENINO"));
        alumnos.add(new Alumno(37657462,"Guillermo Perez",3,"QUIMICA",78.3f,"MASCULINO"));
        alumnos.add(new Alumno(3600462,"Juana Perez",5,"QUIMICA",78.3f,"FEMENINO"));
        alumnos.add(new Alumno(3690462,"Leopoldo Perez",9,"QUIMICA",78.3f,"MASCULINO"));
        alumnos.add(new Alumno(36344462,"Hugo Perez",12,"QUIMICA",78.3f,"MASCULINO"));
        guardarALumnos(alumnos);
    }

    public static void main(String[] args) throws FileNotFoundException {
          crearAlumnos();
    }
}
