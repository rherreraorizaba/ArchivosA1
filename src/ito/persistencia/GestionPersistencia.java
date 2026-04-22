package ito.persistencia;

import ito.data.Alumno;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;

public class GestionPersistencia {

    private Formatter formato;

    public GestionPersistencia(String nombreArchivo) throws FileNotFoundException {
        formato= new Formatter(nombreArchivo);
    }

    public void guardaDatos(ArrayList<Alumno> alumnos){
        for(Alumno alumno:alumnos){
            formato.format("%s\n",alumno);
        }
        formato.flush();
    }
}
