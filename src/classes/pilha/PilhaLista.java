package classes.pilha;

import classes.pilha.listaEncadeada.ListaEncadeada;

public class PilhaLista <T> implements Pilha<T>{
    
    private ListaEncadeada<T> lista = new ListaEncadeada<T>();

    @Override
    public void push(T v) {
        lista.inserir(v);
    }

    @Override
    public T pop() {
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        return lista.getPrimeiro().getInfo();
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public void liberar() {
        try {
            while (true) {
                pop();
            }
        } catch (Exception e) {

        }
    }
}
