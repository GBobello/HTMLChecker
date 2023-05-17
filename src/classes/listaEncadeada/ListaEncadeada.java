package classes.listaEncadeada;

public class ListaEncadeada <T> {

    private NoLista<T> primeiro;
    private NoLista<T> ultimo;

    public ListaEncadeada(){
        primeiro = null;
        ultimo = null;
    }

    public NoLista<T> getPrimeiro(){
        return primeiro;
    }

    public NoLista<T> getUltimo(){
        return ultimo;
    }

    public void inserir(T info){
        NoLista<T> novo = new NoLista<T>();
        novo.setInfo(info);
        if (this.primeiro == null) {
        	this.primeiro = novo;
        	this.ultimo = novo;
        }
        else {
        	this.ultimo.setProximo(novo);
        	this.ultimo = novo;
        }
//        novo.setProximo(getPrimeiro());
//        this.primeiro = novo;    
    }
    
    public boolean estaVazia(){
        return this.primeiro == null;
    }

    public NoLista<T> buscar(T info){
        NoLista<T> p = getPrimeiro();
        while (p != null){
            if (p.getInfo().equals(info)){
                return p;
            } else {
                p = p.getProximo();
            }
        }
        
        return null;
    }
    
    public boolean buscarBoolean(T info){
        NoLista<T> p = getPrimeiro();
        while (p != null){
            if (p.getInfo().equals(info)){
                return true;
            } else {
                p = p.getProximo();
            }
        }
        
        return false;
    }

    public void retirar(T valor){
        NoLista<T> anterior = null;
        NoLista<T> p = getPrimeiro();
        while ((p != null) && (!p.getInfo().equals(valor))){
            anterior = p;
            p = p.getProximo();
        }

        if (p != null){
            if (p == primeiro)
                primeiro = primeiro.getProximo();
            else   
                anterior.setProximo(p.getProximo());
        }   
    }

    public int obterComprimento(){
        int qtdeNos = 0;
        NoLista<T> p = getPrimeiro();
        while (p != null){
            qtdeNos++;
            p = p.getProximo();
        }
        return qtdeNos;
    }

    public void exibir(){
        NoLista<T> p = getPrimeiro();
        while (p != null){
            System.out.println(p.getInfo());

            p = p.getProximo();
        }
    }

    public NoLista<T> obterNo(int idx){
        if (idx < 0){
            throw new IndexOutOfBoundsException();
        }
         
        NoLista<T> p = getPrimeiro();

        int sequencia =0;
        while ((p != null) && (idx > 0)) {
            idx --;
            p = p.getProximo();
        }

        if (p == null){
            throw new IndexOutOfBoundsException();
        }

        return p;
    }

    public String toString(){
        String str = "";
        NoLista<T> p = getPrimeiro();
        while (p != null){
            if (p != getPrimeiro())
                str += ";";

            str += p.getInfo().toString();
            p = p.getProximo();
        }
        return str;
    }

}
