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


    // Começa com os obstáculos que não tem nenhuma aresta de entrada
    // Minion em todos
    // contabiliza o tempo de cada obstaculo
    // depois de um minion terminar, verificar se existe outro obstaculo disponível
    // caso tenha mais obstaculos disponíveis do que mínions, ir por ordem
    // alfabética
    // caso não tenha obstaculos, esperar todos os minios terminarem para poder ir
    // pro proximo

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
        Vertice<Obstaculos> temp;
        for (int j = 0; j < obstaculos.size(); j++) {
            for (int i = j + 1; i < obstaculos.size(); i++) {
                if (obstaculos.get(i).getDado().getName().compareTo(obstaculos.get(j).getDado().getName()) < 0) {
                    // swap elements
                    temp = obstaculos.get(j);
                    obstaculos.get(j).setDadosAndArestas(obstaculos.get(i).getDado(), obstaculos.get(i).getArestasEntrada(), obstaculos.get(i).getArestasSaida());
                    obstaculos.get(i).setDadosAndArestas(temp.getDado(), temp.getArestasEntrada(), temp.getArestasSaida());
                }
            }
        }
        for(int x = 0; x < obstaculos.size(); x++){
            System.out.println(obstaculos.get(x).getDado().getName());
            // System.out.println("----");
            // System.out.println(obstaculos.get(i).getArestasSaida());
            // System.out.println("-------------------");
        }
        return obstaculos;
    }

    public ArrayList<Vertice<Obstaculos>> sort2(ArrayList<Vertice<Obstaculos>> obstaculos) {
        Obstaculos temp;
        ArrayList<Aresta<Obstaculos>> arestaEntrada;
        ArrayList<Aresta<Obstaculos>> arestaSaida;

        Obstaculos temp2;
        ArrayList<Aresta<Obstaculos>> arestaEntrada2;
        ArrayList<Aresta<Obstaculos>> arestaSaida2;

        for (int i = 0; i < obstaculos.size(); i++) {
            for (int j = i + 1; j < obstaculos.size(); j++) {
                if (obstaculos.get(j).getDado().getName().compareTo(obstaculos.get(i).getDado().getName()) < 0) {
                    // swap elements
                     temp = obstaculos.get(i).getDado();
                     arestaEntrada = obstaculos.get(i).getArestasEntrada();
                     arestaSaida = obstaculos.get(i).getArestasSaida();

                     temp2 = obstaculos.get(j).getDado();
                     arestaEntrada2 = obstaculos.get(j).getArestasEntrada();
                     arestaSaida2 = obstaculos.get(j).getArestasSaida();


                     obstaculos.get(i).setDadosAndArestas(temp2, arestaEntrada2, arestaSaida2);
                     obstaculos.get(j).setDadosAndArestas(temp, arestaEntrada, arestaSaida);
                    //  obstaculos.get(i).setDado(obstaculos.get(j).getDado());
                    //  obstaculos.get(j).setDado(temp);
                }
            }
        }
        for(int i = 0; i < obstaculos.size(); i++){
            System.out.println(obstaculos.get(i).getDado().getName());
            System.out.println("--");
            System.out.println(obstaculos.get(i).getArestasSaida());
            System.out.println("-----");
        }
        return obstaculos;
    }

    public void caminhamento(){
        ArrayList<Vertice<Obstaculos>> inicio = new ArrayList<Vertice<Obstaculos>>();
        inicio = sort(busca0Entrada());
        ArrayList<Vertice<Obstaculos>> marcados = new ArrayList<Vertice<Obstaculos>>();
        ArrayList<Vertice<Obstaculos>> fila = new ArrayList<Vertice<Obstaculos>>();
        Vertice<Obstaculos> atual = inicio.get(0);
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

        public ArrayList<Vertice<Obstaculos>> buscaFilhos(Vertice<Obstaculos> obstaculo) {
        ArrayList<Vertice<Obstaculos>> filhosDisponiveis = new ArrayList<Vertice<Obstaculos>>();
        
        sort3(obstaculo.getArestasSaida());
        
        return filhosDisponiveis;
    }

    public ArrayList<Aresta<Obstaculos>> sort3(ArrayList<Aresta<Obstaculos>> obstaculos) {
        Obstaculos temp;
        ArrayList<Aresta<Obstaculos>> arestaEntrada;
        ArrayList<Aresta<Obstaculos>> arestaSaida;

        Obstaculos temp2;
        ArrayList<Aresta<Obstaculos>> arestaEntrada2;
        ArrayList<Aresta<Obstaculos>> arestaSaida2;

        for (int i = 0; i < obstaculos.size(); i++) {
            for (int j = i + 1; j < obstaculos.size(); j++) {
                if (obstaculos.get(j).getFim().getDado().getName().compareTo(obstaculos.get(i).getFim().getDado().getName()) < 0) {
                    // swap elements
                    temp = obstaculos.get(i).getFim().getDado();
                    //System.out.println(temp.getName());
                    arestaEntrada = obstaculos.get(i).getFim().getArestasEntrada();
                    arestaSaida = obstaculos.get(i).getFim().getArestasSaida();

                    temp2 = obstaculos.get(j).getFim().getDado();
                    arestaEntrada2 = obstaculos.get(j).getFim().getArestasEntrada();
                    arestaSaida2 = obstaculos.get(j).getFim().getArestasSaida();


                    obstaculos.get(i).getFim().setDadosAndArestas(temp2, arestaEntrada2, arestaSaida2);
                    obstaculos.get(j).getFim().setDadosAndArestas(temp, arestaEntrada, arestaSaida);
                    //  obstaculos.get(i).setDado(obstaculos.get(j).getDado());
                    //  obstaculos.get(j).setDado(temp);
                }
            }
        }
                for(int i = 0; i < obstaculos.size(); i++){
                    System.out.println(obstaculos.get(i).getFim().getDado().getName());
                    System.out.println("--");
                    System.out.println(obstaculos.get(i).getFim().getArestasSaida());
                    System.out.println("-----");
                }
            
        
        return obstaculos;
    }


}
