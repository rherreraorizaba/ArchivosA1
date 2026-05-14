package ito.persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import ito.data.Alumno;
import ito.data.Genero;


public class GestionAleatoria {

    private RandomAccessFile archivo;
    private String nombre;
    private static final long offset=56;
    private static final long size=64;

    public GestionAleatoria(String nombre){
        this.nombre = nombre;
    }

    public void open() throws FileNotFoundException {
        archivo= new RandomAccessFile(this.nombre,"rw");
    }
    public void close() throws IOException{
        archivo.close();
    }

    private boolean existe(long numeroDeControl){
       boolean existe=false;
       try {
           archivo.seek(1);
           while(true) {
               long nc = archivo.readLong();
               if(nc==numeroDeControl) {
                   existe = true;
                   break;
               }
               archivo.seek(archivo.getFilePointer()+offset);
           }
       }catch(IOException e){

       }
       return existe;
    }

    private void escribirAlumno(Alumno alumno){
        try {
            archivo.writeBoolean(false);
            archivo.writeLong(alumno.getNumeroControl());
            archivo.writeUTF(String.format("%-30s",alumno.getNombre()));
            archivo.writeByte(alumno.getSemestre());
            archivo.writeUTF(String.format("%-15s",alumno.getCarrera()));
            archivo.writeFloat(alumno.getPromedio());
            archivo.writeByte(Genero.valueOf(alumno.getGenero()).ordinal());
        }catch(IOException e){}
    }

    public void gardarAlumnos(ArrayList<Alumno> alumnos){
        try{
            this.open();
            for(Alumno alumno:alumnos)
                this.escribirAlumno(alumno);
            this.close();
        }catch(IOException e){}
    }

    public void agregarAlumno(Alumno alumno) throws FileNotFoundException {
        if(alumno==null)
            throw new NullPointerException("Alumno invalido!!");
        this.open();
        try {
            if (!existe(alumno.getNumeroControl())) {
                archivo.seek(archivo.length());
                escribirAlumno(alumno);
                this.close();
            }else{
                this.close();
                throw new IllegalArgumentException("Alumno existe!!");
            }
        }catch(IOException e){}
    }

    private Alumno obtenerAlumno(long numeroControl){
        Alumno alumno=null;
        try{
            archivo.seek(1);
            while(true){
                if(archivo.readLong()==numeroControl) {
                    String nombre=archivo.readUTF();
                    byte semestre=archivo.readByte();
                    String carrera=archivo.readUTF();
                    float promedio=archivo.readFloat();
                    Genero genero=Genero.values()[archivo.readByte()];
                    alumno= new Alumno(numeroControl,nombre,semestre,carrera,promedio,genero);
                    break;
                }
                archivo.seek(archivo.getFilePointer()+offset);
            }
        }catch(IOException e){}
        return alumno;
    }

    public void eliminarAlumno(Alumno alumno)  {
        if(alumno==null)
            throw new NullPointerException("Alumno invalido!!");
        try {
            this.open();
            Alumno alumArchivo= obtenerAlumno(alumno.getNumeroControl());
            if (alumArchivo!=null && alumno.equals(alumArchivo)) {
                archivo.seek(archivo.getFilePointer()-size);
                archivo.writeBoolean(true);
                this.close();
            }else {
                this.close();
                throw new IllegalArgumentException("Alumno no encontrado!!");
            }
        }catch(IOException e){}
    }

    public Alumno buscarAlumno(long numeroControl) {
        Alumno alumno=null;
        try{
            this.open();
            alumno= obtenerAlumno(numeroControl);
            if(alumno!=null){
                archivo.seek(archivo.getFilePointer()-size);
                if(archivo.readBoolean()) {
                    alumno=null;
                }
            }
        }catch(IOException e){ }
        return alumno;
    }

    public Alumno buscarAlumno(int numeroRegistro){
        Alumno alumno=null;
        int nr=0;
        try{
            this.open();
            while(true) {
                alumno = obtenerAlumno();
                if(alumno!=null)
                    if (++nr == numeroRegistro)
                        break;
            }
        }catch(IOException e){ alumno=null;}
        try{
            this.close();
        }catch(IOException e){}
        return alumno;
    }

    public Alumno obtenerAlumno(){
        Alumno alumno=null;
       try {
           if (!archivo.readBoolean()){
               long nc=archivo.readLong();
               String nombre=archivo.readUTF();
               byte semestre=archivo.readByte();
               String carrera=archivo.readUTF();
               float promedio=archivo.readFloat();
               Genero genero=Genero.values()[archivo.readByte()];
               alumno=new Alumno(nc,nombre,semestre,carrera,promedio,genero);
           }else
               archivo.seek(archivo.getFilePointer()+(size-1));
       }catch (IOException e){}
       return alumno;
    }



    public long getNumeroRegistros(){
        long numeroRegistros=0;
        try {
            this.open();
            while(true){
                if(!archivo.readBoolean())
                    numeroRegistros++;
                archivo.seek(archivo.getFilePointer()+(size-1));
            }
        }catch(IOException e){}
        try {
            this.close();
        }catch(IOException e){}
        return numeroRegistros;
    }

    public void actualizaAlumno(Alumno alumno){
        try{
            this.open();
            archivo.seek(1);
            while(true){
                if(archivo.readLong()==alumno.getNumeroControl()){
                    archivo.writeUTF(String.format("%-30s",alumno.getNombre()));
                    archivo.writeByte(alumno.getSemestre());
                    archivo.writeUTF(String.format("%-15s",alumno.getCarrera()));
                    archivo.writeFloat(alumno.getPromedio());
                    break;
                }
                archivo.seek(archivo.getFilePointer()+offset);
            }
        }catch(IOException e){}
        try{
            this.close();
        }catch(IOException e){}
    }

    public void recuperarAlumnosEliminados(){
        try{
            this.open();
            while(true){
                archivo.writeBoolean(false);
                archivo.seek(archivo.getFilePointer()+(size-1));
            }
        }catch(IOException e){}
    }
}
