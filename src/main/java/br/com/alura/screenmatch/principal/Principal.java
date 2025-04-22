package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepositoy;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    // injeção de dependências
    private SerieRepositoy repository;

    public Principal(SerieRepositoy repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    // Método responsável por buscar informações de uma série via API e armazená-las em uma lista
    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie(); // Obtém os dados da série a partir do nome digitado pelo usuário
        Serie serie = new Serie(dados);
       // dadosSeries.add(dados); // Adiciona os dados obtidos à lista de séries
        repository.save(serie);
        System.out.println(dados); // Exibe os dados da série no console
    }

    // Método auxiliar que solicita ao usuário o nome da série, realiza a requisição à API e retorna os dados da série
    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca"); // Solicita o nome da série ao usuário
        var nomeSerie = leitura.nextLine(); // Lê o nome da série digitado
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY); // Realiza a requisição HTTP à API, substituindo espaços por '+'
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class); // Converte o JSON recebido em um objeto do tipo DadosSerie
        return dados; // Retorna os dados da série
    }

    // Método que busca os episódios por temporada de uma série
    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie(); // Obtém os dados da série a partir do nome digitado pelo usuário
        List<DadosTemporada> temporadas = new ArrayList<>(); // Lista para armazenar os dados de cada temporada

        // Loop que percorre todas as temporadas da série
        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY); // Requisição da temporada específica
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class); // Converte o JSON em objeto DadosTemporada
            temporadas.add(dadosTemporada); // Adiciona os dados da temporada à lista
        }

        temporadas.forEach(System.out::println); // Exibe os dados de todas as temporadas no console
    }

    // Método que lista as séries já buscadas, ordenadas por gênero
    private void listarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = dadosSeries.stream() // Acessa a lista de DadosSerie
                .map(d -> new Serie(d)) // Converte cada DadosSerie em um objeto Serie
                .collect(Collectors.toList()); // Coleta os objetos Serie em uma nova lista

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero)) // Ordena as séries pelo gênero
                .forEach(System.out::println); // Exibe as séries ordenadas no console
    }

}