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
                // System.out.println(vertices.get(i).getDado().getName());
            }
        }
        return aux;
    }

    public ArrayList<Vertice<Obstaculos>> disponiveisSemWorking(ArrayList<Vertice<Obstaculos>> working){
        ArrayList<Vertice<Obstaculos>> aux = busca0Entrada();
        int i = 0;
        while(i < working.size()){
            aux.remove(working.get(i));
            i++;
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
                    obstaculos.get(j).setDadosAndArestas(obstaculos.get(i).getDado(),
                            obstaculos.get(i).getArestasEntrada(), obstaculos.get(i).getArestasSaida());
                    obstaculos.get(i).setDadosAndArestas(temp.getDado(), temp.getArestasEntrada(),
                            temp.getArestasSaida());
                }
            }
        }
        for (int x = 0; x < obstaculos.size(); x++) {
            System.out.println(obstaculos.get(x).getDado().getName());
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

                }
            }
        }
        for (int i = 0; i < obstaculos.size(); i++) {
            System.out.println(obstaculos.get(i).getDado().getName());
            System.out.println("--");
            System.out.println(obstaculos.get(i).getArestasSaida());
            System.out.println("-----");
        }
        return obstaculos;
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
                if (obstaculos.get(j).getFim().getDado().getName()
                        .compareTo(obstaculos.get(i).getFim().getDado().getName()) < 0) {
                    // swap elements
                    temp = obstaculos.get(i).getFim().getDado();
                    // System.out.println(temp.getName());
                    arestaEntrada = obstaculos.get(i).getFim().getArestasEntrada();
                    arestaSaida = obstaculos.get(i).getFim().getArestasSaida();

                    temp2 = obstaculos.get(j).getFim().getDado();
                    arestaEntrada2 = obstaculos.get(j).getFim().getArestasEntrada();
                    arestaSaida2 = obstaculos.get(j).getFim().getArestasSaida();

                    obstaculos.get(i).getFim().setDadosAndArestas(temp2, arestaEntrada2, arestaSaida2);
                    obstaculos.get(j).getFim().setDadosAndArestas(temp, arestaEntrada, arestaSaida);
                    // obstaculos.get(i).setDado(obstaculos.get(j).getDado());
                    // obstaculos.get(j).setDado(temp);
                }
            }
        }
        for (int i = 0; i < obstaculos.size(); i++) {
            System.out.println(obstaculos.get(i).getFim().getDado().getName());
            System.out.println("--");
            System.out.println(obstaculos.get(i).getFim().getArestasSaida());
            System.out.println("-----");
        }

        return obstaculos;
    }

        
    public void removeByTime(int tempo, ArrayList<Vertice<Obstaculos>> disponiveis){
        for(int i = 0; i < disponiveis.size(); i++){
            if(tempo == disponiveis.get(i).getDado().getTime()){
                disponiveis.remove(i);
            }
        }
    }

    public int getByTime(int tempo){
        for(int i = 0; i < vertices.size(); i++){
            if(tempo == vertices.get(i).getDado().getTime()){
                return i;
            }
        }
        return -1;
    }

    public void minionsAgindo(int quantidadeDeMinions) {
        int tempo;
        Vertice<Obstaculos> aux;
        ArrayList<Vertice<Obstaculos>> disponiveis = new ArrayList<Vertice<Obstaculos>>();
        disponiveis = sort2(busca0Entrada());
        ArrayList<Vertice<Obstaculos>> working = new ArrayList<Vertice<Obstaculos>>();
        //ArrayList<Integer> tempo = new ArrayList<Integer>();

        while (vertices.size() > 0) {

            if(disponiveis.size() > 0){
                for (int i = 0; i < calculoMinionsVsDisponiveis(disponiveis.size(), quantidadeDeMinions); i++) {
                    working.add(disponiveis.get(i));
                }
            }
            removeAllDisponiveis(disponiveis, quantidadeDeMinions);

            sort4(working);
            if(disponiveis.size() > 0){
                sort4(disponiveis);
            }
            // tempo vai passar
            // chegou no obstaculo mais rapido
            tempo = working.get(0).getDado().getTime();
            aux = working.get(0);
            vertices.remove(working.remove(0));


            disponiveis = sort2(disponiveisSemWorking(working));
            sort2(working);
        }   

    }

    private int calculoMinionsVsDisponiveis(int tamanhoDisponiveis, int minions){
        if(tamanhoDisponiveis >= minions){
            return minions;
        }

        else{
            return tamanhoDisponiveis;
        }
    }

    private void removeAllDisponiveis(ArrayList<Vertice<Obstaculos>> disponiveis, int quantidadeDeMinions){
        int var = calculoMinionsVsDisponiveis(disponiveis.size(), quantidadeDeMinions);
        int i = 0;
        while(i < var){
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

//     caminha(Nodos, Arestas, Depende, trabalhadores)
//   Nodos[' '] = True
//   tempo = 0

//   # lista de obstáculos disponíveis para serem destruídos
//   # (sem dependências)
//   disponiveis = []

//   # dicionário dos obstáculos que estou trabalhando
//   ef = {' ': 0}

//   enquanto len(disponiveis) ou len(ef)
//     menor = 999
//     menorV = 'z'

//     para cada elemento em ef
//       se ef[v] < menor
//         menor = ef[v]
//         menorV = v

//     tempo += menor
//     ef.pop(menorV)

//     flag = True

//     para cada elemento em ef
//       # atualizar o tempo dos que restaram trabalhando
//       ef[k] -= menor 
//       se ef[k] == 0
//         flag = False

//     para cada elemento em Nodos
//       se (menorV, v) in Arestas and not Nodos[v]
//         Depende[v] -= 1
//         se Depende[v] == 0
//           V[v] = True
//           disponiveis.append(v)

//     # ordena os que estao disponiveis para serem feitos
//     # prioridade alfabetica
//     disponiveis.sort() 

//     enquanto len(ef) < trabalhadores e len(disponiveis) e flag
//       char = disponiveis.pop(0)
//       ef[char] = int(char.split("_")[1]) 

//   retorna tempo

}
