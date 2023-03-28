import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar o top250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; //url da API
        URI endereco = URI.create(url); // criamos a URI que dentro recebe a url
        var client = HttpClient.newHttpClient(); // criamos o cliente que recebe um novo cliente com o protocolo HTTP
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build(); //fizemos a requisição com o newBuider recebendo a url com o método GET
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //a resposta veio através fo body em formato de string
        String body = response.body(); // pegamos o body e colocamos dentro da variavel body
         //chamamos a body
        
        // extrair só os dados que interessa para nós (titulo, poster, rating)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados 
        
        
        var fabrica = new fabricaDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "./saida/" +titulo + ".png";
            fabrica.cria(inputStream, nomeArquivo);


            System.out.println(titulo);
            System.out.println();
            
        }
    }
}
