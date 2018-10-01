package local.model;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Test;

import local.exception.FilmeException;

import static org.junit.Assert.fail;

public class FilmeTest {
    //TODO: Nome deve possuir entre 2 e 99 caracteres (inclusive)
    //TODO: Estoque não pode ser negativo, e o máximo é 99 (inclusive)
    //TODO: O preço de Locacao deverá ser um número positivo entre R$ 1,00 e R$ 9,99 (inclusive)

	@Test
    public void filmeEntreDoisCaracteres(){
		Filme filme = new Filme();
		String nome = "a";
		try {
			filme.setNome(nome);
			fail("O nome do filme deve ter entre 2 e 99 caracteres");
		} catch (FilmeException ex) {
			
		}
    }
	
	@Test
    public void filmeEntreNoventaENoveCaracteres(){
        try {
            Filme filme = new Filme(RandomString.make(100), 2, 1.5);
            fail("O nome do filme deve ter entre 2 e 99 caracteres");
        } catch (FilmeException ex){
        }
    }
	
	@Test
    public void estoqueNaoPodeSerNegativo(){
        try {
            Filme filme = new Filme("Naruto: Prison Blood", -1, 1.5);
            Assert.fail("O estoque não pode ser negativo");
        } catch (FilmeException ex){
            Assert.assertEquals(ex.getMessage(), "O estoque do filme não pode ser negativo ou maior de 99");
        }
    }
	
	@Test
    public void precoDeLocacaoDeveraDerUmNumeroPositivoEntreUmENoveENoventaENove1(){
        
        try {
            Filme filme = new Filme("Naruto: Prison Blood", 2, 0.99);
            Assert.fail("O valor não pode ser menor de R$ 1.00");
        } catch (FilmeException ex){
            Assert.assertEquals(ex.getMessage(), "O preço de locação deve ser entre R$ 1,00 e R$ 9,99");
        }
    }
	
	@Test
    public void precoDeLocacaoDeveraDerUmNumeroPositivoEntreUmENoveENoventaENove2(){
        
        try {
            Filme filme = new Filme("Naruto: Prison Blood", 2, 1.00);
        } catch (FilmeException ex){
        }
    }
	
    @Test
    public void precoDeLocacaoDeveraDerUmNumeroPositivoEntreUmENoveENoventaENove3(){
        
        try {
            Filme filme = new Filme("Naruto: Prison Blood", 2, 9.99);
        } catch (FilmeException ex){
            Assert.assertEquals(ex.getMessage(), "O preço de locação deve ser entre R$ 1,00 e R$ 9,99");
        }
    }
    
    @Test
    public void precoDeLocacaoDeveraDerUmNumeroPositivoEntreUmENoveENoventaENove4(){
        
        try {
            Filme filme = new Filme("Sem idéia", 2, 10.00);
            Assert.fail("O valor da locação não pode ser maior de R$ 9.99");
        } catch (FilmeException ex){
            Assert.assertEquals(ex.getMessage(), "O preço de locação deve ser entre R$ 1,00 e R$ 9,99");
        }
    }

}
