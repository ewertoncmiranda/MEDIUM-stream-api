package com.miranda.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //GERAR  LISTA DE CLIENTES
        List<Cliente> clientes = gerarListaDeClientes();



        //DISTINCT
        List<Integer> tempoCadastro = clientes.stream()
                .map(Cliente::getTempoCadastro)
                .distinct()
                .collect(Collectors.toList());
        tempoCadastro.forEach(System.out::println);

        //SORTED
        List<Cliente> collect1 = clientes.stream()
                                .sorted(Comparator.comparing(Cliente::getTempoCadastro))
                                .collect(Collectors.toList());
        collect1.forEach(System.out::println);
        //FILTER
        List<Cliente> collect = clientes.stream().filter(it -> it.getTipoCliente() == Tipo.CPF).collect(Collectors.toList());
        collect.forEach(System.out::println);
        //MAP
        List<String> stringList = clientes.stream().map(cliente -> {
            return cliente.getNome();
        }).collect(Collectors.toList());
        stringList.forEach(System.out::println);

        //MAX
        List<Cliente> lista = gerarListaDeClientes();
        Cliente cliente = lista.stream().max(Comparator.comparingInt(Cliente::getTempoCadastro)).get();
        System.out.println(cliente);
        
        //MIN
        Cliente cliente1 = lista.stream().min(Comparator.comparingInt(Cliente::getTempoCadastro)).get();
        System.out.println(cliente1);

        //COUNT
        long count = lista.stream()
                          .filter(it -> it.tipoCliente.equals(Tipo.CPF))
                          .count();
        System.out.println(count);
        //AVERAGE
        double asDouble = lista.stream()
                .filter(it -> it.tipoCliente.equals(Tipo.CNPJ))
                .mapToInt(Cliente::getTempoCadastro)
                .average().getAsDouble();
        System.out.println(asDouble);

        //ALLMATCH
        boolean bool = lista.stream().allMatch(it -> it.tempoCadastro > 1);
        System.out.println(bool);

        //ANYMATCH
        boolean bool2 = lista.stream().anyMatch(it -> it.nome.startsWith("k"));
        System.out.println(bool2);

        //NONEMATCH
        boolean bool3 = lista.stream().noneMatch(it-> it.tempoCadastro > 30);
        System.out.println(bool3);

        //LIMIT
        List<Cliente> clientesLimite = clientes.stream().limit(2).collect(Collectors.toList());
        clientesLimite.forEach(it->System.out.println(it.toString()));

        //COLLECT
        List<Cliente> collectToList = clientes.stream().filter(it -> it.getTipoCliente() == Tipo.CPF).collect(Collectors.toList());
        collect.forEach(System.out::println);
        Set<Cliente> collectToSet = clientes.stream().filter(it -> it.getTipoCliente() == Tipo.CPF).collect(Collectors.toSet());
        collect.forEach(System.out::println);

    }
    public static List<Cliente> gerarListaDeClientes(){
        Cliente c1 = new Cliente("Carlos" , 2, Tipo.CPF) ;
        Cliente c2 = new Cliente("Bar do Tião" , 9, Tipo.CNPJ) ;
        Cliente c3 = new Cliente("Glaucia" , 10, Tipo.CPF) ;
        Cliente c4 = new Cliente("Natasha" , 2 , Tipo.CPF) ;
        Cliente c5 = new Cliente("Mac Burguer" ,27 , Tipo.CNPJ) ;
        return List.of(c1,c2,c3,c4,c5);
    }
}

class Cliente{

    String nome ;
    int tempoCadastro ;
    Tipo tipoCliente;

    public Cliente(String nome , int tempoCadastro,Tipo  tipoCliente){
        this.nome = nome;
        this.tempoCadastro = tempoCadastro;
        this.tipoCliente = tipoCliente;
    };

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", tempoCadastro=" + tempoCadastro +
                ", tipoCliente=" + tipoCliente +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoCadastro() {
        return tempoCadastro;
    }

    public void setTempoCadastro(int tempoCadastro) {
        this.tempoCadastro = tempoCadastro;
    }

    public Tipo getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Tipo tipoCliente) {
        this.tipoCliente = tipoCliente;
    }


}

enum Tipo{
    CPF,CNPJ;
}
