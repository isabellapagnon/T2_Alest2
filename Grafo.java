import java.util.ArrayList;

public class Grafo {
    private ArrayList<Vertice<Obstaculos>> vertices;
    private ArrayList<Aresta<Obstaculos>> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<Obstaculos>>();
        this.arestas = new ArrayList<Aresta<Obstaculos>>();
    }

    public Vertice<Obstaculos> adicionarVertice(Obstaculos dado) {
        Vertice<Obstaculos> novoVertice = new Vertice<Obstaculos>(dado);
        if (contains(novoVertice) == null) {
            this.vertices.add(novoVertice);
            return novoVertice;
        }
        return contains(novoVertice);
    }

    public void adicionarAresta(Obstaculos dadoInicio, Obstaculos dadoFim) {
        Vertice<Obstaculos> inicio = this.getVertice(dadoInicio);
        Vertice<Obstaculos> fim = this.getVertice(dadoFim);
        Aresta<Obstaculos> aresta = new Aresta<Obstaculos>(inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<Obstaculos> getVertice(Obstaculos dado) {
        Vertice<Obstaculos> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<Obstaculos>> marcados = new ArrayList<Vertice<Obstaculos>>();
        ArrayList<Vertice<Obstaculos>> fila = new ArrayList<Vertice<Obstaculos>>();
        Vertice<Obstaculos> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while (fila.size() > 0) {
            Vertice<Obstaculos> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestasSaida().size(); i++) {
                Vertice<Obstaculos> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)) { // se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    public Vertice<Obstaculos> contains(Vertice<Obstaculos> obstaculo) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getDado().getName().equals(obstaculo.getDado().getName())) {
                return vertices.get(i);
            }
        }
        return null;
    }

    public void removeDuplicatedObstacles(Vertice<Obstaculos> obstaculo) {
        vertices.remove(obstaculo);
        arestas.remove(obstaculo);

    }

    public int sizeVertice(){
        return vertices.size();
    }

    public int sizeArestas(){
        return arestas.size();
    }

}
