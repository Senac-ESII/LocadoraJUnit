package local.service;

import local.exception.FilmeSemEstoqueException;
import local.exception.LocadoraException;
import local.model.Filme;
import local.model.Locacao;
import local.model.Usuario;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class LocacaoServiceTest {

    @Test
    public void naoDeveLocarFilmeSemUsuario() {
        //Cenário
        Usuario usuario = null;
        Filme filme = new Filme();
        filme.setNome("Rambo III");
        filme.setEstoque(2);
        filme.setPrecoLocacao(4.00);
        LocacaoService ls = new LocacaoService();

        //Processamento e validação
        try {
            ls.alugarFilme(usuario, filme);
            fail("Locação realizada com usuário null");
        }catch (LocadoraException | FilmeSemEstoqueException ex){

            assertEquals("Impossível locar sem um usuário",ex.getMessage());
            assertThat(ex.getMessage(),is(equalTo("Impossível locar sem um usuário")));
        }
    }
    @Test
    public void deveValidarValorLocacao() throws FilmeSemEstoqueException, LocadoraException {
        //Cenário
        double valorFilme = 4.00;
        Usuario usuario = new Usuario();
        usuario.setNome("Alemão Xarope");
        Filme filme = new Filme();
        filme.setPrecoLocacao(valorFilme);
        filme.setEstoque(2);
        filme.setNome("Zezão");
        LocacaoService ls = new LocacaoService();

        //Execução
        Locacao locacao = ls.alugarFilme(usuario,filme);

        //Validação
        //assertEquals(valorFilme,locacao.getValor(),0.001);
        assertThat(locacao.getValor(),is(equalTo(valorFilme)));

    }

}