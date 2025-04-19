package br.com.alura.screenmatch.exercises;

public class LessonOne {
//    1 - Imagine que você tem uma lista de strings. Algumas das strings são números, mas outras não. Queremos converter a lista de strings para uma lista de números. Se a conversão falhar, você deve ignorar o valor. Por exemplo, na lista a seguir, a saída deve ser [10, 20]:
//
//    List<String> input = Arrays.asList("10", "abc", "20", "30x");
//    Copiar código
//    2 - Implemente um método que recebe um número inteiro dentro de um Optional. Se o número estiver presente e for positivo, calcule seu quadrado. Caso contrário, retorne Optional.empty.
//
//        public class Main {
//            public static void main(String[] args) {
//                System.out.println(processaNumero(Optional.of(5))); // Saída: Optional[25]
//                System.out.println(processaNumero(Optional.of(-3))); // Saída: Optional.empty
//                System.out.println(processaNumero(Optional.empty())); // Saída: Optional.empty
//
//
//            }
//            public static Optional<Integer> processaNumero(Optional<Integer> numero) {
//                // Implementar aqui
//            }
//        }
//        Copiar código
//    3 - Implemente um método que recebe uma String representando um nome completo separado por espaços. O método deve retornar o primeiro e o último nome após remover os espaços desnecessários.
//
//        public class Main {
//            public static void main(String[] args) {
//                System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
//                System.out.println(obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"
//
//
//            }
//
//            public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
//                // Implementar aqui
//            }
//
//        }
//        Copiar código
//    4 - Implemente um método que verifica se uma frase é um palíndromo. Um palíndromo é uma palavra/frase que, quando lida de trás pra frente, é igual à leitura normal. Um exemplo:
//
//        public class Main {
//            public static void main(String[] args) {
//                System.out.println(ehPalindromo("subi no onibus em marrocos")); // Saída: true
//                System.out.println(ehPalindromo("Java")); // Saída: false
//
//
//
//            }
//            public static boolean ehPalindromo(String palavra) {
//                // Implementar aqui
//            }
//
//        }
//        Copiar código
//    5 - Implemente um método que recebe uma lista de e-mails (String) e retorna uma nova lista onde cada e-mail está convertido para letras minúsculas.
//
//        public class Main {
//            public static void main(String[] args) {
//                List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
//                System.out.println(normalizeEmails(emails));
//    // Saída: ["teste@exemplo.com", "exemplo@java.com", "usuario@teste.com"]
//
//            }
//            public List<String> converterEmails(List<String> emails) {
//                // Implementar aqui
//            }
//        }
//        Copiar código
//    6 - Crie um enum Mes que represente os meses do ano. Adicione um método que retorna o número de dias de cada mês, considerando anos não bissextos.
//
//        public enum Mes {
//            // Definir os valores
//            ;
//
//            public int getNumeroDeDias() {
//                // Implementar aqui
//            }
//        }
//        Copiar código
//        Para chamar o método:
//
//                System.out.println(Mes.FEVEREIRO.getNumeroDeDias()); // 28
//    System.out.println(Mes.JULHO.getNumeroDeDias()); // 31
//        Copiar código
//    7 - Crie um enum Moeda com valores como DOLAR, EURO, REAL. Cada moeda deve ter uma taxa de conversão para reais. Adicione um método que recebe um valor em reais e retorna o valor convertido para a moeda.
//
//        public enum Moeda {
//            // Definir os valores
//            ;
//
//            public double converterPara(double valorEmReais) {
//                // Implementar aqui
//            }
//        }
//        Copiar código
//        Para chamar o método:
//
//                System.out.println(Moeda.DOLAR.converterPara(100)); // 19.60 (aproximado)
//    System.out.println(Moeda.EURO.converterPara(100)); // 18.18 (aproximado)
//        Copiar código
//    8 - Crie um enum CodigoErro com valores de erros HTTP, como NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR. Cada valor deve ter um código numérico e uma descrição associados. Adicione métodos para acessar o código e a descrição. Dica: consulte o site https://http.cat/ para conhecer os vários erros HTTP e conseguir descrevê-los melhor.
//
//        public enum CodigoErro {
//            // Definir os valores
//            ;
//
//            public int getCodigo() {
//                // Implementar aqui
//            }
//
//            public String getDescricao() {
//                // Implementar aqui
//            }
//        }
//        Copiar código
//        Para chamar o método:
//
//                System.out.println(CodigoErro.NOT_FOUND.getCodigo()); // 404
//    System.out.println(CodigoErro.BAD_REQUEST.getDescricao()); // Requisição inválid
}
