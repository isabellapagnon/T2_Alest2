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
        Grafo<Obstaculos> grafo = new Grafo<Obstaculos>();

        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {

            String line = reader.readLine(); // lê a primeira linha

            while ((line = reader.readLine()) != null) {
                aux = line.split(" -> ");

                if(line.equals("}")){
                    break;
                }

                Obstaculos obstaculo = new Obstaculos(aux[0]);
                Obstaculos obstaculo2 = new Obstaculos(aux[1]);

                grafo.adicionarVertice(obstaculo);
                grafo.adicionarVertice(obstaculo2);
            
                grafo.adicionarAresta(obstaculo.getTime(), obstaculo, obstaculo2);

                grafo.contains(obstaculo);
                grafo.contains(obstaculo2);


            }
            
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

    }

}