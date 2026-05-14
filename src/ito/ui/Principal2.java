package ito.ui;

import ito.data.Alumno;
import ito.data.ListaAlumnos;
import ito.persistencia.GestionAleatoria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Principal2 {

    private Validacion validacion;
    private Visualizacion visualizacion;
    private Scanner input = new Scanner(System.in);
    private String menu;
    private GestionAleatoria archivo;

    public Principal2(){
        validacion= new Validacion(input);
        visualizacion = new Visualizacion();
        archivo= new GestionAleatoria("alumnos.rand");
        creaMenu();
    }

    private void creaMenu(){
        menu="Menu principal\n";
        menu+="1.- Agregar alumno\n";
        menu+="2.- Eliminar alumno\n";
        menu+="3.- Modificar alumno\n";
        menu+="4.- Consultar alumno\n";
        menu+="5.- Listar alumnos\n";
        menu+="6.- Salir\n";
        menu+="Proporciona opcion:[1..6]:";
    }

    private byte leerOpcion(){
        return (byte)validacion.leerLong(menu,1,6,"Opcion invalida!!");
    }

    private void agregarAlumno(){
        Alumno alumno=validacion.leerAlumno();
        try {
            archivo.agregarAlumno(alumno);
        }catch(IllegalArgumentException | FileNotFoundException e){
            System.err.println("El alumno no fue agregado!!");
        }
    }

    private void eliminarAlumno(){
        long nc=validacion.leerNumeroControl();
        Alumno alumno=archivo.buscarAlumno(nc);
        visualizacion.visualizaAlumno(alumno);
        boolean respuesta=validacion.leerBoolean("Es el alumno a eliminar:[Si/No]:","Si","No","Opcion invalidad!!");
        if(respuesta)
            archivo.eliminarAlumno(alumno);
    }

    private void modificaAlumno(Alumno alumno){
       String menu="Menu de modificacion\n";
       menu+="1.- Modificar nombre\n";
       menu+="2.- Modificar carrera\n";
       menu+="3.- Modificar semestre\n";
       menu+="4.- Modificar promedio\n";
       menu+="Indica la opción:[1..4]:";
       visualizacion.visualizaAlumno(alumno);
       byte opcion=(byte)validacion.leerLong(menu,1,4,"Opcion equivocada!!");
       switch(opcion){
           case 1:alumno.setNombre(validacion.leerNombre());break;
           case 2:alumno.setCarrera(validacion.leerCarrera());break;
           case 3:alumno.setSemestre(validacion.leerSemestre());break;
           case 4:alumno.setPromedio(validacion.leerPromedio());
       }
    }

    private void modificarAlumno(){
        long nc=validacion.leerNumeroControl();
        Alumno alumno=archivo.buscarAlumno(nc);
        if(alumno!=null) {
            modificaAlumno(alumno);
            archivo.actualizaAlumno(alumno);
        }
    }

    private void mostrarAlumno(){
        long nc=validacion.leerNumeroControl();
        Alumno alumno=archivo.buscarAlumno(nc);
        if(alumno!=null)
            visualizacion.visualizaAlumno(alumno);
    }

    private void listarAlumnos(){
       long numReg=archivo.getNumeroRegistros();
       for(int i=1;i<=numReg;i++){
           Alumno alumno=archivo.buscarAlumno(i);
           visualizacion.visualizaAlumno(alumno);
       }
    }


    public void run() throws FileNotFoundException {
        byte opcion;
        do{
            opcion=leerOpcion();
            switch(opcion){
                case 1: agregarAlumno();break;
                case 2: eliminarAlumno();break;
                case 3: modificarAlumno();break;
                case 4: mostrarAlumno();break;
                case 5: listarAlumnos();
            }
        }while(opcion!=6);
    }
}
