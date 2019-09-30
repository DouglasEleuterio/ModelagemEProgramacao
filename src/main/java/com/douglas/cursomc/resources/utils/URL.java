package com.douglas.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Metodo que paga o String passado na URL, exemplo
 * locahost:8080/produtos/nome=computador&categorias=1,2,3
 * Desejamos pegar o String categorias e montar uma lista de Integer, conforme precisamos na assinatura de metodo search
 *
 * @see com.douglas.cursomc.repositories.ProdutoRepository
 */
public class URL {


    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntList(String s) {
        String[] vet = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<vet.length; i++) {
            list.add(Integer.parseInt(vet[i]));
        }
        return list;
        //return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}