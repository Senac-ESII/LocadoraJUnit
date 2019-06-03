package local.model;

import local.exception.FilmeException;
import org.junit.jupiter.api.Test;

import javax.validation.Valid;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class FilmeTest {
    //TODO: Nome deve possuir entre 2 e 99 caracteres (inclusive)
    // Lança FilmeException - O nome do filme deve possuir entre 2 e 99 caracteres

    @Test
    public void naoDeveValidarNomeFilmeComMenosde2Caracteres() {
        //cenário
        Filme filme = new Filme();

        Exception ex = assertThrows(FilmeException.class, () -> filme.setNome("a"));
        assertThat(ex.getMessage(),is(equalTo("O nome do filme deve possuir entre 2 e 99 caracteres")));
    }

    @Test
    public void deveValidarNomeFilmeCom2Caracteres() {
        //cenário
        Filme filme = new Filme();
        filme.setNome("ab");
        assertThat(filme.getNome(), is("ab"));
    }

    @Test
    public void deveValidarNomeFilmeCom99Caracteres() {
        //cenário
        Filme filme = new Filme();
        String nomeFilme = "";
        for (int i = 0; i < 99; i++) {
            nomeFilme += "B";
        }
        filme.setNome(nomeFilme);
        assertThat(filme.getNome(), is(nomeFilme));
    }

    @Test
    public void naoDeveValidarNomeFilmeComMaisDe99Caracteres() {
        //cenário
        Filme filme = new Filme();
        String nomeFilme = "";
        for (int i = 0; i < 100; i++) {
            nomeFilme += "C";
        }
        final String nome = nomeFilme;
        Exception ex = assertThrows(FilmeException.class, () -> filme.setNome(nome));
        assertThat(ex.getMessage(),is(equalTo("O nome do filme deve possuir entre 2 e 99 caracteres")));
    }

    //TODO: Estoque não pode ser negativo, e o máximo é 99 (inclusive)
    // Lança FilmeException - Valor de estoque inválido

    @Test
    public void naoDeveValidarEstoqueNegativo() {
        //cenário
        Filme filme = new Filme();

        Exception ex = assertThrows(FilmeException.class, () -> filme.setEstoque(-1));
        assertThat(ex.getMessage(),is(equalTo("Valor de estoque inválido")));
    }

    @Test
    public void deveValidarEstoquePositivo() {
        //cenário
        Filme filme = new Filme();
        filme.setEstoque(0);
        assertThat(filme.getEstoque(), is(0));
    }

    @Test
    public void deveValidarEstoquePositivoEMenorQue99() {
        //cenário
        Filme filme = new Filme();
        filme.setEstoque(99);
        assertThat(filme.getEstoque(), is(99));
    }

    @Test
    public void naoDeveValidarEstoquePositivoEMaiorQue99() {
        //cenário
        Filme filme = new Filme();
        Exception ex = assertThrows(FilmeException.class, () -> filme.setEstoque(100));
        assertThat(ex.getMessage(),is(equalTo("Valor de estoque inválido")));
    }

    //TODO: O preço de Locacao deverá ser um número positivo entre R$ 1,00 e R$ 9,99 (inclusive)
    // Lança FilmeException - Valor locação inválido

    @Test
    public void naoDeveValidarValorDelocacaoMenor1() {
        //cenário
        Filme filme = new Filme();
        Exception ex = assertThrows(FilmeException.class, () -> filme.setPrecoLocacao(0.99));
        assertThat(ex.getMessage(),is(equalTo("Valor locação inválido")));
    }

    @Test
    public void deveValidarValorDelocacaoMaiorIgual1_00() {
        //cenário
        Filme filme = new Filme();
        filme.setPrecoLocacao(1.00);
        assertThat(filme.getEstoque(), is(1.00));
    }

    @Test
    public void deveValidarValorDelocacaoMenorIgual9_99() {
        //cenário
        Filme filme = new Filme();
        filme.setPrecoLocacao(9.99);
        assertThat(filme.getEstoque(), is(9.99));
    }

    @Test
    public void naoDeveValidarValorDelocacaoMaior9_99() {
        //cenário
        Filme filme = new Filme();
        Exception ex = assertThrows(FilmeException.class, () -> filme.setPrecoLocacao(10.00));
        assertThat(ex.getMessage(),is(equalTo("Valor locação inválido")));
    }
}
