package br.edu.ifpb.gugawag.so.sockets;

import java.util.Collection;
public class Metodos_StringBuilder {
    public static String print(Collection<? extends Object> collection) {
        StringBuilder nomes = new StringBuilder();
        for (Object obj : collection) {
            nomes.append(obj.toString()).append(" ");
        }
        return nomes.toString();
    }
}
