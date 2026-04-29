package ito.persistencia;

import ito.data.Alumno;
import ito.data.Genero;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class GestionPersistencia {

    private Formatter output;
    private Scanner input;
    private String nombreArchivo;

    public GestionPersistencia(String nombreArchivo)  {
        this.nombreArchivo = nombreArchivo;
    }

    public void guardaDatos(ArrayList<Alumno> alumnos) throws FileNotFoundException {
        output = new Formatter(nombreArchivo);
        for (Alumno alumno : alumnos) {
            output.format("%s\n", alumno);
        }
        output.flush();
    }

    private Alumno procesaDatos(String line){
        Scanner inputTexto = new Scanner(line).useDelimiter(",");
        long nc=inputTexto.nextLong();
        String nombre=inputTexto.next();
        byte semestre=inputTexto.nextByte();
        String carrera=inputTexto.next();
        float promedio=inputTexto.nextFloat();
        Genero genero=Genero.valueOf(inputTexto.next());
        inputTexto.close();
        return new Alumno(nc,nombre,semestre,carrera,promedio,genero);
    }

    public  ArrayList<Alumno> leerDatos() throws FileNotFoundException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        File archivo = new File(this.nombreArchivo);
        input= new Scanner(archivo);
        while(input.hasNextLine()){
            String line = input.nextLine();
            alumnos.add(procesaDatos(line));
        }
        input.close();
        return alumnos;
    }
}
