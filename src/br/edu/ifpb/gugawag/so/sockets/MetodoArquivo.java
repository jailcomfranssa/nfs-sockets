package br.edu.ifpb.gugawag.so.sockets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MetodoArquivo {

    String local = "C:\\ja";
    public MetodoArquivo(){

    }
    //Listar
    public ArrayList readdir(ArrayList lista){
        try (Stream<Path> paths = Files.walk(Paths.get(this.local))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(item->lista.add(item));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }


    //CRIAR ARQUIVO
    public boolean create( String nome) throws IOException{
        File file = new File(local+"\\"+nome +".txt");
        if(file.createNewFile()){
            System.out.println("File deleted: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());

            return true;

        }
        return false;
    }
    //DELETAR ARQUIVO
    public boolean remove(String nome){
        File del = new File(local+"\\"+nome +".txt");
        if(del.delete()){
            System.out.println("File deleted: " + del.getName());
            System.out.println("Absolute path: " + del.getAbsolutePath());
            return true;
        }
        return false;

    }
    //Renomear
    public boolean rename(String nome1, String nome2){
        File f1 = new File(local+"\\"+nome1 +".txt");
        File f2 = new File(local+"\\"+nome2 +".txt");
        return f1.renameTo(f2);
    }


}
