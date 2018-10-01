package local.model;

import local.exception.ClienteException;


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
			throw new ClienteException("Nome é um campo obrigatório");
		}
		if(!nome.isEmpty()) {
			String partes[] = nome.split(" ");
			if(partes.length <2) {
				throw new ClienteException("Nome deve conter no mínimo um sobrenome");
				
			}
		}
		
		if(!nome.isEmpty()) {
			char inicio = nome.charAt(0);
			char fim = nome.charAt(nome.length()-1);
			if(inicio == ' ') {
				throw new ClienteException("Nome não pode ter espaços no começo");
			}
			if(fim == ' ') {
				throw new ClienteException("Nome não pode ter espaços no final");
			}
			
		}
		
		
		if(nome.isEmpty()) {
			throw new ClienteException("O nome do cliente não pode ser vazio");
 		}
		if (nome.length()<4 || nome.length()>55){
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