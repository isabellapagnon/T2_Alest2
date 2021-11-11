import java.util.ArrayList;

public class Vertice<Obstaculos> {
    private Obstaculos obstaculos;
    private ArrayList<Aresta<Obstaculos>> arestasEntrada;
    private ArrayList<Aresta<Obstaculos>> arestasSaida;

    public Vertice(Obstaculos valor) {
        this.obstaculos = valor;
        this.arestasEntrada = new ArrayList<Aresta<Obstaculos>>();
        this.arestasSaida = new ArrayList<Aresta<Obstaculos>>();
    }

    public Obstaculos getDado() {
        return obstaculos;
    }

    public void setDado(Obstaculos obstaculos) {
        this.obstaculos = obstaculos;
    }

    public void adicionarArestaEntrada(Aresta<Obstaculos> aresta) {
        this.arestasEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta<Obstaculos> aresta) {
        this.arestasSaida.add(aresta);
    }

    public ArrayList<Aresta<Obstaculos>> getArestasEntrada() {
        return arestasEntrada;
    }

    public ArrayList<Aresta<Obstaculos>> getArestasSaida() {
        return arestasSaida;
    }

    public void setDadosAndArestas(Obstaculos obstaculos, ArrayList<Aresta<Obstaculos>> arestaEntrada,
                                    ArrayList<Aresta<Obstaculos>> arestaSaida) {
        this.obstaculos = obstaculos;
        this.arestasEntrada  = arestaEntrada;
        this.arestasSaida = arestaSaida;

    }

    // public void setDadosAndArestas2(Obstaculos obstaculos, ArrayList<Aresta<Obstaculos>> arestaEntradaa,
    //                                 ArrayList<Aresta<Obstaculos>> arestaSaidaa) {
    //     setDado(obstaculos);
    //     setArestaEntrada(arestaEntradaa);
    //     setArestaSaida(arestaSaidaa);

    // }

    // public void setArestaEntrada(ArrayList<Aresta<Obstaculos>> arestaEntradaa){
    //     for(int i = 0; i < arestasEntrada.size(); i++){
    //         arestasEntrada.remove(i);
    //     }

    //     for(int i = 0; i < arestaEntradaa.size(); i++){
    //         arestasEntrada.add(arestaEntradaa.get(i));
    //     }
    // }

    // public void setArestaSaida(ArrayList<Aresta<Obstaculos>> arestaSaidaa){
    //     for(int i = 0; i < arestasSaida.size(); i++){
    //         arestasSaida.remove(i);
    //     }

    //     for(int i = 0; i < arestaSaidaa.size(); i++){
    //         arestasSaida.add(arestaSaidaa.get(i));
    //     }
    // }

}
