import java.util.ArrayList;
import java.util.Collections;

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

    public Vertice<Obstaculos> contains(Vertice<Obstaculos> obstaculo) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getDado().getName().equals(obstaculo.getDado().getName())) {
                return vertices.get(i);
            }
        }
        return null;
    }

    public int sizeVertice() {
        return vertices.size();
    }

    public int sizeArestas() {
        return arestas.size();
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<Obstaculos>> marcados = new ArrayList<Vertice<Obstaculos>>();
        ArrayList<Vertice<Obstaculos>> fila = new ArrayList<Vertice<Obstaculos>>();
        Vertice<Obstaculos> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado().getName());
        fila.add(atual);
        while (fila.size() > 0) {
            Vertice<Obstaculos> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestasSaida().size(); i++) {
                Vertice<Obstaculos> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)) { // se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado().getName());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    // Começa com os obstáculos que não tem nenhuma aresta de entrada
    // Minion em todos
    // contabiliza o tempo de cada obstaculo
    // depois de um minion terminar, verificar se existe outro obstaculo disponível
    // caso tenha mais obstaculos disponíveis do que mínions, ir por ordem
    // alfabética
    // caso não tenha obstaculos, esperar todos os minios terminarem para poder ir
    // pro proximo

    // public Obstaculos sort(ArrayList<Vertice<Obstaculos>> freeObstacles){

    // freeObstacles.forEach(obstaculo -> {
    // System.out.println(obstaculo.getDado().getName());
    // });
    // return null;
    // }

    public ArrayList<Vertice<Obstaculos>> busca0Entrada() {
        ArrayList<Vertice<Obstaculos>> aux = new ArrayList<Vertice<Obstaculos>>();
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getArestasEntrada().size() == 0) {
                aux.add(vertices.get(i));
                //System.out.println(vertices.get(i).getDado().getName());
            }
        }
        return aux;
    }

    public ArrayList<Vertice<Obstaculos>> sort(ArrayList<Vertice<Obstaculos>> obstaculos) {
        Obstaculos temp;
        for (int i = 0; i < obstaculos.size(); i++) {
            for (int j = i + 1; j < obstaculos.size(); j++) {
                if (obstaculos.get(j).getDado().getName().compareTo(obstaculos.get(i).getDado().getName()) < 0) {
                    // swap elements
                    temp = obstaculos.get(i).getDado();
                    obstaculos.get(i).setDado(obstaculos.get(j).getDado());
                    obstaculos.get(j).setDado(temp);
                }
            }
        }
        for(int i = 0; i < obstaculos.size(); i++){
            System.out.println(obstaculos.get(i).getDado().getName());
        }
        return obstaculos;
    }

}
