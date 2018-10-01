package local.model;

import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import local.exception.ClienteException;

public class ClienteTest {
    //TODO: O nome não pode ser nulo. Lança RuntimeException -  Nome não é um campo obrigatório
    //TODO: nome deve possuir entre 4 e 55 caracteres (inclusive). Lança ClienteException - O nome do cliente deve possuir entre 4 e 55 caracteres
    //TODO: O nome do cadastro deve possuir pelo menos 2 nomes (ex.: Angelo Luz)
    //TODO: O nome deverá ser salvo sem espaços no início e fim
    //TODO: O nome não deverá possuir símbolo ou número.Lança ClienteException - Números e símbolos não são permitidos
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void nomeNaoPodeSerNulo() {
		thrown.expectMessage("Nome é um campo obrigatório");
		Cliente cliente = new Cliente();
		cliente.setNome(null);
		Assertions.assertThat(cliente.getNome()).isEqualTo(null);
	}
	
	@Test
	public void nomeDevePossuirMaisQueTresCaracteres() {
		Cliente cliente = new Cliente();
		String nome = "a ";
		for (int i = 0; i < 1 ; i++) {
			nome += "a";
		}
		try {
			cliente.setNome(nome);
			fail("O nome do cliente deve possuir entre 4 e 55 caracteres");
		} catch (ClienteException ex) {

		}
	}
	
	@Test
	public void nomeDevePossuirMenosQueCinquentaESeisCaracteres() {
		Cliente cliente = new Cliente();
		String nome = "a ";
		for (int i = 0; i < 55 ; i++) {
			nome += "a";
		}
		try {
			cliente.setNome(nome);
			fail("O nome do cliente deve possuir entre 4 e 55 caracteres");
		} catch (ClienteException ex) {
			ex.getMessage().equals("O nome do cliente deve possuir entre 4 e 55 caracteres");
		}
	}
	
	@Test
	public void nomeDevePossuirPeloMenosDoisNomes() {
		Cliente cliente = new Cliente();
		String nome = "Vinicius Mendes";
		
		try {
			cliente.setNome(nome);
			
		} catch (ClienteException ex) {
			fail("O nome do usuário deve possuir sobrenome");
		}
	}
	
	@Test
	public void nomeNaoPodeTerEspacoNoInicio() {
		Cliente cliente = new Cliente();
		String nome = " Jeniffer Camargo";
		System.err.println("inicio: " + nome.charAt(0));
		System.err.println("fim: " + nome.charAt(nome.length() - 1));

		try {
			cliente.setNome(nome);
			fail("Nome não pode ter espaço no inicio");
		} catch (ClienteException ex) {
			Assertions.assertThat(ex.getMessage().equals("Nome não pode ter espaço no inicio"));
		}
	}
	
	@Test
	public void nomeNaoPodeTerEspacoNoFinal() {
		Cliente cliente = new Cliente();
		String nome = "Jeniffer Camargo ";
		System.err.println("inicio: " + nome.charAt(0));
		System.err.println("fim: " + nome.charAt(nome.length() - 1));

		try {
			cliente.setNome(nome);
			fail("Nome não pode ter espaço no final");
		} catch (ClienteException ex) {
			Assertions.assertThat(ex.getMessage().equals("Nome não pode ter espaço no final"));
		}
	}
	
	@Test
    public void nomeNaoDeveTerNumeros() {
    	Cliente cliente = new Cliente();
        String nome = "Vinicius Mendes07";
        
        try {
            cliente.setNome(nome);
            fail("Nome não pode conter números e símbolos");
        }catch (ClienteException ex){
        	Assertions.assertThat(ex.getMessage().equals("Números e símbolos não são permitidos"));
        }
    }
	
	@Test
    public void nomeNaoDeveTerSimbolos() {
    	Cliente cliente = new Cliente();
        String nome = "@Viniciu$ Mende$";
        
        try {
            cliente.setNome(nome);
            fail("Nome não pode conter números e símbolos");
        }catch (ClienteException ex){
        	Assertions.assertThat(ex.getMessage().equals("Números e símbolos não são permitidos"));
        }
    }
	
}

