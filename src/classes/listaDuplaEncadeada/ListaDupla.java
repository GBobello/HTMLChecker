public class ListaDupla<T> {

    private NoListaDupla<T> primeiro;
    private NoListaDupla<T> ultimo;

    public ListaDupla() {
        this.primeiro = null;
        this.ultimo = null;
    } 

    public NoListaDupla<T> getPrimeiro() {
        return primeiro;
    }

    public NoListaDupla<T> getUltimo() {
        return ultimo;
    }

    public void insere(T info) {
        NoListaDupla<T> novo = new NoListaDupla<T>(info);
        novo.setProx(this.primeiro);
        if (this.primeiro != null) {
            this.primeiro.setAnt(novo);
        }
        this.primeiro = novo;

        if (this.ultimo == null) {
            this.ultimo = novo;
        }
    }

    public NoListaDupla<T> buscar(T info) {
        NoListaDupla<T> atual = this.primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(info)) {
                return atual;
            }
            atual = atual.getProx();
        }
        return null;
    }

    public void retirar(T info) {
        //deve retirar a primeira ocorrência do elemento da lista
        NoListaDupla<T> atual = this.primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(info)) {
                if (atual.getAnt() != null) {
                    atual.getAnt().setProx(atual.getProx());
                } else {
                    this.primeiro = atual.getProx();
                }
                if (atual.getProx() != null) {
                    atual.getProx().setAnt(atual.getAnt());
                } else {
                    this.ultimo = atual.getAnt();
                }
                return;
            }
            atual = atual.getProx();
        }
    }

    public void exibirOrdemInversa() {
        //Exibe ordem inversa da lista
        NoListaDupla<T> atual = this.ultimo;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getAnt();
        }
    }

    public void exibir() {
        //Exibe ordem inversa da lista
        NoListaDupla<T> atual = this.primeiro;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getProx();
        }
    }

    public void liberar() {
        //deve remover todos os encadeamentos dos nós da lista

        NoListaDupla<T> atual = this.primeiro;
        while (atual != null) {
            NoListaDupla<T> aux = atual.getProx();
            atual.setProx(null);
            atual.setAnt(null);
            atual.setInfo(null);
            atual = aux;
        }
        this.primeiro = null;
        this.ultimo = null;
    }

    public String toString() {
        String s = "";
        NoListaDupla<T> atual = this.primeiro;
        while (atual != null) {
            s += atual.getInfo() + " ";
            atual = atual.getProx();
        }
        return s;
    }

    public ListaDupla<T> clonar(){
        //Clona a lista
        ListaDupla<T> novaLista = new ListaDupla<>();
    
        NoListaDupla<T> p = getPrimeiro();

        while (p.getProx() != null){
            p = p.getProx();
        }

        novaLista.insere(p.getInfo());

        while (p.getAnt() != null) {
            novaLista.insere(p.getAnt().getInfo());
            p = p.getAnt();
        }

        return novaLista;
    }
    
    public ListaDupla<T> clonarDoProfessor() {
        ListaDupla<T> nova = new ListaDupla<>();
        NoListaDupla<T> ultimo = null;
        NoListaDupla<T> p = getPrimeiro();
        while (p!=null) {
            ultimo = p;
            p = p.getProx();
        }

        p = ultimo;
        while (p!=null) {
            nova.insere( p.getInfo() );
            p = p.getAnt();
        }

        return nova;
    }    

    public static void main(String[] args) {

        ListaDupla<String> lista = new ListaDupla<String>();
        ListaDupla<String> listaClone = new ListaDupla<String>();
        ListaDupla<String> listaCloneProfessor = new ListaDupla<String>();
        lista.insere("D");
        lista.insere("C");
        lista.insere("B");
        lista.insere("A");

        listaClone = lista.clonar();
        System.out.println(listaClone.toString());

        listaCloneProfessor = lista.clonarDoProfessor();
        System.out.println(listaCloneProfessor.toString());
    }

}
