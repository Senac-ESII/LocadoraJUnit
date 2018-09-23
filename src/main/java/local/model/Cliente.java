package local.model;

import local.exception.ClienteException;

import javax.validation.constraints.Size;

public class Cliente {


	private String nome;
	
	public Cliente() {}
	
	public Cliente(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome==null){
			throw new ClienteException("Nome não é um campo obrigatório");
		}
		if (nome.length()<=4 && nome.length()<55){
			throw new ClienteException("O nome do cliente deve possuir entre 4 e 55 caracteres");
		}
		if(!nome.matches("[a-zA-ZàèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃçÇ ]+")){
			throw new ClienteException("Números e símbolos não são permitidos");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}