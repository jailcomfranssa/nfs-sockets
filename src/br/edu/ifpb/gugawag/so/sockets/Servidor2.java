package br.edu.ifpb.gugawag.so.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Servidor2 {

    public static void main(String[] args) throws IOException {
        MetodoArquivo mtArquivo = new MetodoArquivo();

        System.out.println("== Servidor ==");


        ServerSocket serverSocket = new ServerSocket(7001);
        Socket socket = serverSocket.accept();


        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        DataInputStream dis = new DataInputStream(socket.getInputStream());


        while (true) {
            System.out.println("Cliente: " + socket.getInetAddress());

            String mensagem = dis.readUTF();
            System.out.println(mensagem);

//            ler arquivos
            if (mensagem.equals("readdir")) {
                String res = Metodos_StringBuilder.print(mtArquivo.readdir(new ArrayList()));
                dos.writeUTF(res);
            }

//
//
                //CRIAR ARQUIVO
                if (mensagem.equals("create")) {
                    dos.writeUTF("Digite o nome do Arquivo");
                    String novoArquivo = dis.readUTF();
                    if (mtArquivo.create(novoArquivo)) {
                        dos.writeUTF("Arquivo criado com sucesso");
                    } else {
                        dos.writeUTF("Falha ao criar o arquivo.");
                    }

                }
                //DELETAR ARQUIVO
                if (mensagem.equals("remove")) {
                    dos.writeUTF("digite o nome do arquivo pra deleta");
                    String del = dis.readUTF();
                    if (mtArquivo.remove(del)) {
                        dos.writeUTF("Arquivo deletado");

                    } else {
                        dos.writeUTF("Falha ao deletar o arquivo.");
                    }
                }

            //renomear arquivos
            if (mensagem.equals("rename")) {

                dos.writeUTF("Digite nome do arquivo");

                String nome1 = dis.readUTF();

                dos.writeUTF("Digite o novo nome do arquivo");

                String nome2 = dis.readUTF();
                if (mtArquivo.rename(nome1, nome2)) {
                    dos.writeUTF("Arquivo renomeado !!");
                } else {
                    dos.writeUTF("Falha ao tentar Renomear");
                }
            }



                else {
                    dos.writeUTF("Arquivo nao encontrado: " + mensagem);
                }
            }

        }
    }
