package local.service;

import static local.util.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import local.model.Filme;
import local.model.Locacao;
import local.model.Cliente;
import local.exception.FilmeSemEstoqueException;
import local.exception.LocadoraException;

public class LocacaoService {
//TODO atualizar para muitos filmes
    public Locacao alugarFilme(Cliente cliente, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
    	if (cliente == null || cliente.getNome().isEmpty()) {
            throw new LocadoraException("Impossivel locar sem um usuário ou com nome vazio");
        }

        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Nenhum filme foi selecionado");
        }
        
        if(cliente.getNome().length() <4 || cliente.getNome().length() >55) {
        	throw new LocadoraException("Nome do cliente deverá ter entre 4 e 55 caracteres");
        }
        
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);

        for(Filme filme: filmes) {
            if (filme.getEstoque() == 0) {
                throw new FilmeSemEstoqueException("Filme sem estoque");
            }

            locacao.addFilme(filme);
            locacao.setDataLocacao(new Date());
            locacao.setValor(filme.getPrecoLocacao());

            //Entrega no dia seguinte
            Date dataEntrega = new Date();
            dataEntrega = adicionarDias(dataEntrega, 1);
            locacao.setDataRetorno(dataEntrega);
        }
        //Salvando a locacao...	
        //TODO adicionar método para salvar
        return locacao;
    }
}
