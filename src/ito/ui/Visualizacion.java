package ito.ui;

import ito.data.Alumno;
import java.util.ArrayList;

public class Visualizacion {

    public void visualizaAlumno(Alumno alumno){
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("%08d ",alumno.getNumeroControl()));
        buffer.append(String.format("%-30s ",alumno.getNombre()));
        buffer.append(String.format("%03d ",alumno.getSemestre()));
        buffer.append(String.format("%-16s ",alumno.getCarrera()));
        buffer.append(String.format("%6.2f ",alumno.getPromedio()));
        buffer.append(String.format("%-15s ",alumno.getGenero()));
        System.out.println(buffer.toString());
    }

    public void visualizaEncabezado(){
        System.out.printf("%-8s %-30s %-4s %-15s %-7s %-15s\n","No Cont","Nombre","Sem","Carrera","Prom","Genero");
    }

    public void visualizaTodos(ArrayList<Alumno> alumnos){
        visualizaEncabezado();
        for(Alumno alumno:alumnos){
            visualizaAlumno(alumno);
        }
    }
}
