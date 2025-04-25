package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepositoy;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
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

    private List<Serie> series = new ArrayList<>();

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
                    4 - Buscar série por título
                    5 - Buscar série por ator
                    6 - Buscas Top 5 séries
                    7 - Buscar séries por categoria
                    8 - Buscar série por temporada e por avaliação
                    9 - Buscar episódio por trecho
                    
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
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriePorAutor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    buscarSeriePorTemporadaEAvaliacao();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
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
    private void buscarEpisodioPorSerie() {
        // Lista todas as séries que foram buscadas anteriormente
        listarSeriesBuscadas();

        // Solicita ao usuário que digite o nome da série desejada
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = leitura.nextLine();

        // Busca a série pelo nome (ignora maiúsculas/minúsculas) e retorna o primeiro resultado encontrado
        Optional<Serie> serie = repository.findByTituloContainingIgnoreCase(nomeSerie);

        // Verifica se a série foi encontrada
        if (serie.isPresent()) {
            var serieEncontrada = serie.get(); // Obtém a série encontrada

            // Cria uma lista para armazenar os dados de todas as temporadas da série
            List<DadosTemporada> temporadas = new ArrayList<>();

            // Loop para percorrer todas as temporadas da série
            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                // Monta a URL da API com o título da série e o número da temporada
                var json = consumo.obterDados(
                        ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY
                );

                // Converte o JSON retornado em um objeto da classe DadosTemporada
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);

                // Adiciona os dados dessa temporada à lista de temporadas
                temporadas.add(dadosTemporada);
            }

            // Exibe os dados de todas as temporadas no console
            temporadas.forEach(System.out::println);

            // Cria uma lista com todos os episódios de todas as temporadas
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()  // Para cada temporada, pega a lista de episódios
                            .map(e -> new Episodio(d.numero(), e))) // Cria um objeto Episodio com o número da temporada e os dados do episódio
                    .collect(Collectors.toList());

            // Associa a lista de episódios à série
            serieEncontrada.setEpisodios(episodios);

            // Salva a série com os episódios no repositório (provavelmente no banco de dados)
            repository.save(serieEncontrada);

        } else {
            // Se a série não for encontrada, exibe uma mensagem
            System.out.println("Série não encontrada.");
        }
    }

    // Método que lista as séries já buscadas, ordenadas por gênero
    private void listarSeriesBuscadas() {
        series = repository.findAll();
        //series = dadosSeries.stream() // Acessa a lista de DadosSerie
          //      .map(d -> new Serie(d)) // Converte cada DadosSerie em um objeto Serie
           //     .collect(Collectors.toList()); // Coleta os objetos Serie em uma nova lista
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero)) // Ordena as séries pelo gênero
                .forEach(System.out::println); // Exibe as séries ordenadas no console
    }

    private void buscarSeriePorTitulo(){
        System.out.println("Escolha uma série pelo título: ");
        var tituloSerie = leitura.nextLine();
        Optional<Serie> serieBuscada = repository.findByTituloContainingIgnoreCase(tituloSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("Dados da série: " + serieBuscada.get());
        } else {
            System.out.println("Série não encontrada!");
        }

    }

    private void buscarSeriePorAutor() {
        System.out.println("Qual o nome do ator para busca: ");
        var nomeAtor = leitura.nextLine();
        System.out.println("Avaliações a partir de que valor? ");
        var avaliacao = leitura.nextDouble();
        List<Serie> seriesEncontradas = repository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        System.out.println("Séries em que " + nomeAtor + " trabalhou: ");
        seriesEncontradas.forEach( s ->
                System.out.println(s.getTitulo() + ", avaliação: " + s.getAvaliacao()));
    }

    private void buscarTop5Series() {
        List<Serie> serieTop = repository.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s ->
                System.out.println(s.getTitulo() + ", avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Deseja buscar séries de qual categoria/gênero? ");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = repository.findByGenero(categoria);
        System.out.println("Séries da categoria " + nomeGenero);
        seriesPorCategoria.forEach(System.out::println);
    }

    private void buscarSeriePorTemporadaEAvaliacao() {
        System.out.println("Deseja buscar séries com quantas temporadas? ");
        var numeroTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Deseja buscar séries com qual avaliação? ");
        var serieAvaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> seriePorTemporadaEAvaliacao = repository.seriesPorTemporadaEAvaliacao(numeroTemporadas, serieAvaliacao);
        seriePorTemporadaEAvaliacao.forEach(s ->
                System.out.println(s.getTitulo() + " - avaliação: " + s.getAvaliacao()));
    }

    private void buscarEpisodioPorTrecho(){
        System.out.println("Qual o nome do episódio para busca?");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosEncontrados = repository.episodiosPorTrecho(trechoEpisodio);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Série: %s Temporada %s - Episódio %s - %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(),
                        e.getNumeroEpisodio(), e.getTitulo()));
    }

}