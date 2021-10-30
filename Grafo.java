import java.util.ArrayList;

public class Grafo<Bag> {
    private ArrayList<Vertice<Bag>> vertices;
    private ArrayList<Aresta<Bag>> arestas;
    
    public Grafo(){
        this.vertices = new ArrayList<Vertice<Bag>>();
        this.arestas = new ArrayList<Aresta<Bag>>();
    }
    
    public void adicionarVertice(Bag dado){
        Vertice<Bag> novoVertice = new Vertice<Bag>(dado);
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(int peso, Bag dadoInicio, Bag dadoFim){
        Vertice<Bag> inicio = this.getVertice(dadoInicio);
        Vertice<Bag> fim = this.getVertice(dadoFim);
        Aresta<Bag> aresta = new Aresta<Bag>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }
    
    public Vertice<Bag> getVertice(Bag dado){
        Vertice<Bag> vertice = null;
        for(int i=0; i < this.vertices.size(); i++){
            if (this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
    
    public void buscaEmLargura(){
        ArrayList<Vertice<Bag>> marcados = new ArrayList<Vertice<Bag>>();
        ArrayList<Vertice<Bag>> fila = new ArrayList<Vertice<Bag>>();
        Vertice<Bag> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<Bag> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestasSaida().size(); i++){
                Vertice<Bag> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)){ //se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }
}
