package local.service;

import static local.util.DataUtils.adicionarDias;

import java.io.File;
import java.util.Date;
import java.util.List;

import local.model.Filme;
import local.model.Locacao;
import local.model.Usuario;
import local.exception.FilmeSemEstoqueException;
import local.exception.LocadoraException;

public class LocacaoService {
//TODO atualizar para muitos filmes
    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
        if (usuario == null) {
            throw new LocadoraException("Impossivel locar sem um usuário");
        }

        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Nenhum filme foi selecionado");
        }

        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);

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
