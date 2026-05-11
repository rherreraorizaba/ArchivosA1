package ito.persistencia;

import ito.data.Alumno;
import ito.data.Genero;

import java.io.*;
import java.util.ArrayList;

public class PersistenciaBinaria {

    private DataInputStream input;
    private DataOutputStream output;
    private FileInputStream archivoInput;
    private FileOutputStream archivoOutput;
    private String nombreArchivo;

    public PersistenciaBinaria(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo+".dat";
    }

    public void guardaDatos(ArrayList<Alumno> alumnos) throws FileNotFoundException {
        archivoOutput= new FileOutputStream(this.nombreArchivo);
        output = new DataOutputStream(archivoOutput);
        try {
            for (Alumno alumno : alumnos) {
                output.writeLong(alumno.getNumeroControl());
                output.writeUTF(alumno.getNombre());
                output.writeByte(alumno.getSemestre());
                output.writeUTF(alumno.getCarrera());
                output.writeFloat(alumno.getPromedio());
                output.writeByte(Genero.valueOf(alumno.getGenero()).ordinal());
            }
        }catch(IOException e){}
        try {
            output.close();
        }catch(IOException e){}
    }

    public ArrayList<Alumno> leerDatos() throws FileNotFoundException {
        archivoInput = new FileInputStream(this.nombreArchivo);
        input = new DataInputStream(archivoInput);
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            while (true) {
                Long numeroControl = input.readLong();
                String nombre = input.readUTF();
                byte semestre = input.readByte();
                String carrera = input.readUTF();
                float promedio = input.readFloat();
                Genero genero = Genero.values()[input.readByte()];
                alumnos.add(new Alumno(numeroControl, nombre, semestre, carrera, promedio, genero));
            }
        }catch(IOException e){}
        try {
            input.close();
        }catch(IOException e){}
        return alumnos;
    }
}
