import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar o top250 filmes
            // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; //url da API
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMdb();
       
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-01-10&end_date=2023-01-17";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();


       var http = new ClienteHttp();
       String json = http.buscaDados(url);
        
    
        // exibir e manipular os dados 
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var fabrica = new fabricaDeFigurinhas();
        for (int i = 0; i < 7; i++) {

           Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = "./saida/" +conteudo.getTitulo() + ".png";
            fabrica.cria(inputStream, nomeArquivo);


            System.out.println(conteudo.getTitulo());
            System.out.println();
            
        }
    }
}
