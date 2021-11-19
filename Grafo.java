import java.util.ArrayList;
import java.util.Collections;

public class Grafo {
    private ArrayList<Vertice<Obstaculos>> vertices;
    private ArrayList<Aresta<Obstaculos>> arestas;

    public int tempoTotal;

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

    public ArrayList<Vertice<Obstaculos>> getVertices(){
        return vertices;
    }

    public Vertice<Obstaculos> contains(Vertice<Obstaculos> obstaculo) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getDado().getName().equals(obstaculo.getDado().getName())) {
                return vertices.get(i);
            }
        }
        return null;
    }

    public ArrayList<Vertice<Obstaculos>> busca0Entrada() {
        ArrayList<Vertice<Obstaculos>> aux = new ArrayList<Vertice<Obstaculos>>();
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getArestasEntrada().size() == 0) {
                aux.add(vertices.get(i));
            }
        }
        return aux;
    }

    public ArrayList<Vertice<Obstaculos>> disponiveisSemWorking(ArrayList<Vertice<Obstaculos>> working) {
        ArrayList<Vertice<Obstaculos>> aux = busca0Entrada();
        int i = 0;
        while (i < working.size()) {
            aux.remove(working.get(i));
            i++;
        }
        return aux;
    }

    public int minionsAgindo(int quantidadeDeMinions) {
        int tempo = 0;
        int tempoTotal = 0;
        Vertice<Obstaculos> aux;
        ArrayList<Aresta<Obstaculos>> arestasDeSaida;
        ArrayList<Vertice<Obstaculos>> disponiveis = new ArrayList<Vertice<Obstaculos>>();
        disponiveis = busca0Entrada();
        ArrayList<Vertice<Obstaculos>> working = new ArrayList<Vertice<Obstaculos>>();
        ArrayList<Vertice<Obstaculos>> excluded = new ArrayList<Vertice<Obstaculos>>();
        int workingSize = 0;

        while (vertices.size() > 0) {

            if (disponiveis.size() > 0) {
                workingSize = working.size(); 
                criaListaDeNomes(working);
                for (int i = 0; i < calculoMinionsVsDisponiveis(disponiveis.size(), quantidadeDeMinions, workingSize); i++) {
                    working.add(getVerticeByName(criaListaDeNomes(disponiveis).get(i)));
                }
            }

            removeAllDisponiveis(disponiveis, quantidadeDeMinions, workingSize);

            sort4(working);
            if (disponiveis.size() > 0) {
                sort4(disponiveis);
            }

            tempo = working.get(0).getDado().getTime();
            aux = working.get(0);
            working.remove(0);
            tempoTotal = tempoTotal + tempo;
            setarTempo(working, tempo);
            arestasDeSaida = vertices.get(getIndexByObject(aux)).getArestasSaida();
            deleteArestasDeEntradasDasArestasDeSaida(arestasDeSaida, aux);
            vertices.remove(aux);
            excluded.add(aux);
            verify(excluded);

            disponiveis = disponiveisSemWorking(working);;
        }
        return tempoTotal;

    }

    private void setarTempo(ArrayList<Vertice<Obstaculos>> working, int tempo) {
        int t = 0;
        for (int i = 0; i < working.size(); i++) {
            t = working.get(i).getDado().getTime();
            working.get(i).getDado().setTime(t - tempo);
        }

    }

    public int getIndexByObject(Vertice<Obstaculos> aux) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i) == aux) {
                return i;
            }
        }
        return -1;
    }

    public void deleteArestasDeEntradasDasArestasDeSaida(ArrayList<Aresta<Obstaculos>> arestasDeSaida,
            Vertice<Obstaculos> aux1) {
        int aux;
        for (int i = 0; i < arestasDeSaida.size(); i++) {
            aux = getIndexByObject(arestasDeSaida.get(i).getFim());
            // System.out.println(aux);
            if(aux != -1){
            vertices.get(aux).deleteVerticeFromArestaEntrada(aux1);
            }
        }

    }

    public void verify(ArrayList<Vertice<Obstaculos>> excluded) {

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).verify(excluded);
        }
    }

    private int calculoMinionsVsDisponiveis(int tamanhoDisponiveis, int minions, int working) {
        if (working > 0) {
            int calculo = minions - working;

            if (tamanhoDisponiveis >= calculo) {
                return calculo;
            }

            else {
                return tamanhoDisponiveis;
            }

        } else {
            if (tamanhoDisponiveis >= minions) {
                return minions;
            }

            else {
                return tamanhoDisponiveis;
            }
        }
    }

    private void removeAllDisponiveis(ArrayList<Vertice<Obstaculos>> disponiveis, int quantidadeDeMinions, int working) {
        int var = calculoMinionsVsDisponiveis(disponiveis.size(), quantidadeDeMinions, working);
        int i = 0;
        while (i < var) {
            disponiveis.remove(0);
            i++;
        }
    }

    public ArrayList<Vertice<Obstaculos>> sort4(ArrayList<Vertice<Obstaculos>> obstaculos) {
        Obstaculos temp;
        ArrayList<Aresta<Obstaculos>> arestaEntrada;
        ArrayList<Aresta<Obstaculos>> arestaSaida;

        Obstaculos temp2;
        ArrayList<Aresta<Obstaculos>> arestaEntrada2;
        ArrayList<Aresta<Obstaculos>> arestaSaida2;

        for (int i = 0; i < obstaculos.size(); i++) {
            for (int j = i + 1; j < obstaculos.size(); j++) {
                if (obstaculos.get(j).getDado().getTime() < obstaculos.get(i).getDado().getTime()) {
                    // swap elements
                    temp = obstaculos.get(i).getDado();
                    arestaEntrada = obstaculos.get(i).getArestasEntrada();
                    arestaSaida = obstaculos.get(i).getArestasSaida();

                    temp2 = obstaculos.get(j).getDado();
                    arestaEntrada2 = obstaculos.get(j).getArestasEntrada();
                    arestaSaida2 = obstaculos.get(j).getArestasSaida();

                    obstaculos.get(i).setDadosAndArestas(temp2, arestaEntrada2, arestaSaida2);
                    obstaculos.get(j).setDadosAndArestas(temp, arestaEntrada, arestaSaida);

                }
            }
        }
        return obstaculos;
    }

    public Vertice<Obstaculos> getVerticeByName (String name){
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getDado().getName() == name){
                return vertices.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> criaListaDeNomes(ArrayList<Vertice<Obstaculos>> disponiveis){
        ArrayList<String> nomes = new ArrayList<String>(disponiveis.size());
        for(int i = 0; i < disponiveis.size(); i++){
            nomes.add(disponiveis.get(i).getDado().getName());
        }
        Collections.sort(nomes);
        return nomes;

    }

    

}
