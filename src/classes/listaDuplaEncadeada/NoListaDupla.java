public class NoListaDupla<T> {
    private T info;
    private NoListaDupla<T> prox;
    private NoListaDupla<T> ant;

    public NoListaDupla(T info) {
        this.info = info;
        this.prox = null;
        this.ant = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoListaDupla<T> getProx() {
        return prox;
    }

    public void setProx(NoListaDupla<T> prox) {
        this.prox = prox;
    }

    public NoListaDupla<T> getAnt() {
        return ant;
    }

    public void setAnt(NoListaDupla<T> ant) {
        this.ant = ant;
    }
}