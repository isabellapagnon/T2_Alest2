import java.util.ArrayList;

public class Grafo<Obstaculos> {
    private ArrayList<Vertice<Obstaculos>> vertices;
    private ArrayList<Aresta<Obstaculos>> arestas;
    
    public Grafo(){
        this.vertices = new ArrayList<Vertice<Obstaculos>>();
        this.arestas = new ArrayList<Aresta<Obstaculos>>();
    }
    
    public void adicionarVertice(Obstaculos dado){
        Vertice<Obstaculos> novoVertice = new Vertice<Obstaculos>(dado);
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(int peso, Obstaculos dadoInicio, Obstaculos dadoFim){
        Vertice<Obstaculos> inicio = this.getVertice(dadoInicio);
        Vertice<Obstaculos> fim = this.getVertice(dadoFim);
        Aresta<Obstaculos> aresta = new Aresta<Obstaculos>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }
    
    public Vertice<Obstaculos> getVertice(Obstaculos dado){
        Vertice<Obstaculos> vertice = null;
        for(int i=0; i < this.vertices.size(); i++){
            if (this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
    
    public void buscaEmLargura(){
        ArrayList<Vertice<Obstaculos>> marcados = new ArrayList<Vertice<Obstaculos>>();
        ArrayList<Vertice<Obstaculos>> fila = new ArrayList<Vertice<Obstaculos>>();
        Vertice<Obstaculos> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<Obstaculos> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestasSaida().size(); i++){
                Vertice<Obstaculos> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)){ //se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    public boolean contains(Obstaculos obstaculos){
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getDado().equals(obstaculos))
                return true;
                removeDuplicatedObstacles(obstaculos);
            }
        return false;
    }

    public void removeDuplicatedObstacles(Obstaculos obstaculo){
        vertices.remove(obstaculo);
        arestas.remove(obstaculo);

    }




}
