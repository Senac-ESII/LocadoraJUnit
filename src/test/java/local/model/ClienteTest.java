package local.model;

import local.exception.ClienteException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ClienteTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();



    //O nome não pode ser nulo. Lança ClienteException -  Nome é um campo obrigatório
    @Test
    public void naoDeveAceitarUsuarioNulo(){
        exception.expect(ClienteException.class);
        exception.expectMessage("Nome é um campo obrigatório");
        Cliente cliente = new Cliente();
        cliente.setNome(null);

    }
    //nome deve possuir entre 4 e 55 caracteres (inclusive). Lança ClienteException - O nome do cliente deve possuir entre 4 e 55 caracteres
    @Test
    public void deveAceitarNomeCom4Caracteres(){
        Cliente cliente = new Cliente();
        cliente.setNome( "John");
        assertThat(cliente.getNome(),is(equalTo("John")));

    }
    @Test
    public void deveAceitarNomeCom55Caracteres(){
        Cliente cliente = new Cliente();
        String n = "abcabcabcababcabcabcababcabcabcababcabcabcabasdasdasdas";
        String nome = RandomString.make();
        cliente.setNome(nome);
        assertThat(cliente.getNome(),is(equalTo(nome)));

    }
    @Test
    public void naoDeveAceitarNomeCom56Caracteres(){
        exception.expect(ClienteException.class);
        exception.expectMessage("O nome do cliente deve possuir entre 4 e 55 caracteres");
        Cliente cliente = new Cliente();
        String nome = RandomString.make(56);
        cliente.setNome(nome);
        assertThat(cliente.getNome(),is(equalTo(nome)));
    }
    @Test
    public void naoDeveAceitarNomeCom3Caracteres(){
        exception.expect(ClienteException.class);
        exception.expectMessage("O nome do cliente deve possuir entre 4 e 55 caracteres");
        Cliente cliente = new Cliente();
        String nome = RandomString.make(3);
        cliente.setNome(nome);
        assertThat(cliente.getNome(),is(equalTo(nome)));
    }
    //O nome do cadastro deve possuir pelo menos 2 nomes (ex.: Angelo Luz)
    @Test
    public void naoDeveAceitarNomeUnico(){
        exception.expect(ClienteException.class);
        exception.expectMessage("É necessário adicionar um sobrenome");
        Cliente cliente = new Cliente();
        cliente.setNome("Angelo");
    }
    @Test
    public void deveAceitarDoisNomes(){
        Cliente cliente = new Cliente();
        cliente.setNome("Angelo Luz");
        assertThat(cliente.getNome(),is("Angelo Luz"));
    }
    //O nome deverá ser salvo sem espaços no início e fim
    @Test
    public void naoDeveRegistrarNomeComEspacosNoInicioFim(){
        Cliente cliente = new Cliente();
        cliente.setNome(" Angelo Luz ");
        assertThat(cliente.getNome(),is("Angelo Luz"));
    }
    //O nome não deverá possuir símbolo ou número.Lança ClienteException - Números e símbolos não são permitidos
    @Test
    public void naoDeveAceitarNomeComNumero(){
        exception.expect(ClienteException.class);
        exception.expectMessage("Números e símbolos não são permitidos");
        Cliente cliente = new Cliente();
        cliente.setNome("J0rge Silva");
    }
    @Test
    public void naoDeveAceitarNomeComSimbolo(){
        exception.expect(ClienteException.class);
        exception.expectMessage("Números e símbolos não são permitidos");
        Cliente cliente = new Cliente();
        cliente.setNome("Jorg& Silva");
    }
    @Test
    public void deveAceitarNomeSemNumeroSimbolo(){
        Cliente cliente = new Cliente();
        cliente.setNome("Angelo Luz");
    }

}
