package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    static String TOKEN_API = "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";

    public static String obterTraducao(String texto) {
        // Instancia o serviço da OpenAI com uma chave de API (token de autenticação)
        OpenAiService service = new OpenAiService(TOKEN_API);

        // Cria uma requisição de completamento (prompt) para o modelo GPT
        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct") // Define o modelo que será utilizado
                .prompt("Traduza para o português o texto: " + texto) // Texto enviado como prompt de tradução
                .maxTokens(1000) // Limita a quantidade de tokens da resposta
                .temperature(0.7) // Define o grau de criatividade da resposta (0.0 = mais exata, 1.0 = mais criativa)
                .build();

        // Envia a requisição para a API da OpenAI e armazena a resposta
        var resposta = service.createCompletion(requisicao);

        // Retorna o texto gerado pelo modelo (a tradução)
        return resposta.getChoices().get(0).getText();
    }
}