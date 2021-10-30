import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {

        Path path = Paths.get("oito_enunciado.txt");
        String aux[];
        Grafo<Minions> grafo = new Grafo<Minions>();

        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {

            String line = reader.readLine(); // lê a primeira linha

            while ((line = reader.readLine()) != null) {
                aux = line.split(" -> ");

                if(line.equals("}")){
                    break;
                }

               Minions minions = new Minions(aux[0]);
               grafo.adicionarVertice(minions);

               Minions minions2 = new Minions(aux[1]);
               grafo.adicionarVertice(minions2);

               grafo.adicionarAresta(minions.getTime(), minions, minions2);

            }
            
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

    }

}