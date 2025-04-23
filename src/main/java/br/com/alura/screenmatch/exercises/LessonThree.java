package br.com.alura.screenmatch.exercises;

public class LessonThree {
//    1 - Relacione Categoria e Produto usando @OneToMany, permitindo que uma categoria tenha vários produtos. Assim, estamos definindo um relacionamento 1:n do lado da categoria. Aqui, ao salvarmos uma categoria, queremos salvar seus produtos automaticamente também. Faça a configuração necessária para atender a esse requisito.
//
//2 - Repare que o relacionamento criado é unidirecional: somente a classe Categoria o enxerga. Podemos deixá-lo bidirecional, configurando um relacionamento do tipo n:1 do lado do Produto, com a anotação @ManyToOne.
//
//3 - Na sua classe Principal, você pode criar várias categorias e produtos diferentes e fazer as associações correspondentes. Extra (pra quem quer mergulhar mesmo): Agora, iremos trabalhar com um novo tipo de relacionamento: o relacionamento muitos para muitos, que usa a anotação @ManyToMany.
//
//    Para esse relacionamento, geralmente é criada uma tabela intermediária entre as entidades. Pesquise como isso é feito e mapeie um relacionamento do tipo @ManyToMany entre Produto e Pedido, usando uma tabela intermediária. Dica: use a anotação @JoinTable, em conjunto com @JoinColumn.
//
//            Depois, associe produtos a pedidos na sua classe Principal.
//
//            4 - Crie uma nova classe Fornecedor, com os atributos id e nome. Transforme a classe em entidade.
//
//5 - Configure um relacionamento unidirecional entre Fornecedor e Produto. O relacionamento deve ser mapeado na classe Produto. Logo, é nessa classe que deverá ter a anotação de relacionamento. Qual é a melhor anotação para usarmos neste caso?
//
//            6 - Faça as devidas associações entre Fornecedor e Produto na sua classe Principal.
}
