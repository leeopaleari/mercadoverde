package br.com.fiap.mercadoverde.presentation.navigation

interface INavigationDestination {
    /**
     * Define uma rota específica à qual este destino pertence.
     * A rota é uma string que define o caminho para o seu componente.
     * Você pode pensar nela como um deep link implícito que leva a um destino específico.
     * Cada destino deve ter uma rota exclusiva
     */
    val route: String

    /**
     * Define um ID de destino específico.
     * Isso é necessário ao usar gráficos aninhados por meio do DLS de navegação,
     * para diferenciar a rota de um destino específico da rota de
     * todo o gráfico aninhado ao qual ele pertence.
     */
    val destination: String
}