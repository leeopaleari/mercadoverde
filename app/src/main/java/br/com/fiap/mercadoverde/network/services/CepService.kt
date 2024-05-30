package br.com.fiap.mercadoverde.network.services

import br.com.fiap.mercadoverde.network.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {
    @GET("{cep}/json/")
    suspend fun getEnderecoByCep(@Path("cep") cep: String): Endereco

    @GET("{uf}/{cidade}/{rua}/json/")
    fun getEnderecosByUfCidade(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String
    ): Call<List<Endereco>>
}