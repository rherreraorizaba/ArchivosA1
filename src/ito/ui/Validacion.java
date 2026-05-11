package ito.ui;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import ito.data.Genero;
import ito.data.Alumno;

public class Validacion {

    private Scanner input;

    public Validacion(Scanner input) {
        this.input = input;
    }

    public long leerLong(String text, long min,long max, String error){
        long valor;
        if(min>=max)
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor o igual que el máximo!!");
        do{
            System.out.print(text);
            try{
                 valor = Long.parseLong(input.nextLine());
            }catch(NumberFormatException e){
                valor=max+1;
            }
            if(valor<min || valor>max)
                System.err.println(error);;
        }while(valor<min || valor>max);
        return valor;
    }

    public long leerNumeroControl(){
       return leerLong("Proporciona número de Control:",10000000L,99999999L,"EL valor introduccido no es un valor valido!!");
    }

    public String leerString(String text, List<String> validacion, String error){
        String valor;
        do{
            System.out.print(text);
            valor = input.nextLine();
            if(validacion!=null)
                if(validacion.contains(valor))
                  break;
                else
                    valor=null;
        }while(valor==null || valor.isEmpty());
        return valor;
    }

    public String leerNombre(){
        return leerString("Proporciona Nombre de alumno:",null,"Nombre invalido!!");
    }

    public String leerCarrera(){
        ArrayList<String> carreras=new  ArrayList<>(Arrays.asList("INDUSTRIAL","QUIMICA","ELECTRICA","ELECTRONICA","MECANICA","INFORMATICA","SISTEMAS","SEMICONDUCTORES","CIENCIA DE DATOS","GESTION"));
        return leerString("Proporciona Carrera:",carreras,"Carerra invalido!!");
    }

    public byte leerSemestre(){
        byte semestre=(byte)leerLong("Proporciona semestre[1..13]:",1,13,"Semestre invalido!!");
        return semestre;
    }

    public boolean leerBoolean(String texto,String verdadero,String falso,String error){
        String valor=leerString(String.format("Es el alumno a eliminar:[%s/%s]:",verdadero,falso), Arrays.asList(verdadero,falso),"Opción no valida!!");
        return valor.compareTo(verdadero)==0;
    }

    public float leerFlotante(String texto,float min,float max,String error){
        float valor;
        if(min>=max)
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor o igual que el máximo!!");
        do{
            System.out.print(texto);
            try{
                valor = Float.parseFloat(input.nextLine());
            }catch(NumberFormatException e){
                valor=max+1;
            }
            if(valor<min || valor>max)
                System.err.println(error);;
        }while(valor<min || valor>max);
        return valor;
    }

    public float leerPromedio(){
        return leerFlotante("Proporciona promedio:",0,100,"Promedio invalido!!");
    }

    public Genero leerGenero(){
        ArrayList<String> generos=new  ArrayList<>(Arrays.asList("MASCULINO","FEMENINO","BINARIO"));
        return Genero.valueOf(leerString("Proporciona Genero:",generos,"Genero invalido!!"));
    }

    public Alumno leerAlumno(){
        long nc=leerNumeroControl();
        String nombre=leerNombre();
        byte semestre=leerSemestre();
        String carrera=leerCarrera();
        float promedio=leerPromedio();
        Genero genero=leerGenero();
        return new Alumno(nc,nombre,semestre,carrera,promedio,genero);
    }
}
