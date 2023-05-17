package classes;
import classes.listaEncadeada.ListaEncadeada;
import classes.pilha.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.File;

public class HTMLChecker {
    private BufferedReader reader;
    private StringBuilder html;
    private PilhaLista<String> pilha;
    private ListaEncadeada<String> tags;

    public HTMLChecker(String caminho) throws IOException {
        this.pilha = new PilhaLista<String>();
        this.tags = new ListaEncadeada<String>();
        this.reader = new BufferedReader(new FileReader(caminho));
        this.html = new StringBuilder();

        while (this.reader.ready()) {
            this.html.append(this.reader.readLine().toLowerCase());
        }

        this.reader.close();
    }
    
    public ListaEncadeada<String> getTags(){
    	return this.tags;
    }

    public String check() {
        String tagFechamento = "";
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == '<') {
                if (html.charAt(i + 1) == '/') {
                    for (int count = i + 2; count < html.length(); count++){    
                        if ((html.charAt(count) == '>') || (html.charAt(count) == ' ')){
                            break;
                        }
                        else {
                            tagFechamento += html.charAt(count);
                        }
                    }
                    if (tagFechamento.equals(pilha.peek())){
                        pilha.pop();
                        tagFechamento = "";
                    }
                    else {
                    	String tagAberta = pilha.peek();
                    	return "O arquivo está mal formatado, a tag: " + 
                    			tagAberta + " está aberta e não pode ser fechada por: " + 
                    			tagFechamento;
                    }
                } else {
                    String tag = "";
                    for (int j = i + 1; j < html.length(); j++) {
                        if ((html.charAt(j) == ' ') || (html.charAt(j) == '>')) {
                            tag = html.substring(i + 1, j);
                            break;
                        }
                    }
                    if (!tags.estaVazia()) {
                    	classes.listaEncadeada.NoLista<String> no = tags.buscar(tag);
                    	if (no == null){
                    		tags.inserir(tag);
                    		tags.inserir("1");
                    	} else {
                    		no = no.getProximo();
                    		no.setInfo(Integer.toString(Integer.parseInt(no.getInfo()) + 1));
                    	}
                    } else {
	            		tags.inserir(tag);
	            		tags.inserir("1");
                    }
                    
                    
                    if (tag.equals("meta") || 
                        tag.equals("link") ||
                        tag.equals("br") || 
                        tag.equals("hr") || 
                        tag.equals("base") ||
                        tag.equals("col") ||
                        tag.equals("command") ||
                        tag.equals("embed") ||
                        tag.equals("img") || 
                        tag.equals("input") ||
                        tag.equals("param") ||
                        tag.equals("source") ||
                        tag.equals("!doctype")) {
                        continue;
                    }
                    pilha.push(tag);
                }
            }
        }
        if (pilha.estaVazia()) {
            return "O arquivo está bem formatado.";
        } else {
            return "O arquivo está mal formatado, há tags de início sem tag final.";
        }
    }
}
