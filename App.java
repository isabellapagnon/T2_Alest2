import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {

        Path path = Paths.get("dez.txt");
        String aux[];
        Grafo grafo = new Grafo();

        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {

            String line = reader.readLine(); // lê a primeira linha

            while ((line = reader.readLine()) != null) {
                aux = line.split(" -> ");

                if (line.equals("}")) {
                    break;
                }

                Obstaculos obstaculo = new Obstaculos(aux[0]);
                Obstaculos obstaculo2 = new Obstaculos(aux[1]);

                grafo.adicionarAresta((grafo.adicionarVertice(obstaculo).getDado()), (grafo.adicionarVertice(obstaculo2).getDado()));

            }

        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        System.out.println("vertices: " + grafo.sizeVertice());
        System.out.println("Arestas: " + grafo.sizeArestas());

   
        grafo.sort2(grafo.busca0Entrada());
        grafo.buscaFilhos(grafo.busca0Entrada().get(1));

    }

}