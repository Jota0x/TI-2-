package model;

public class Pessoa {

	private int codigo;
	private String senha;
	private String login;
	private char sexo;
	
	//construtor
	public Pessoa()
	{
		codigo = -1;
		senha = "";
		login = "";
		sexo = '?';
	}
	
	public Pessoa(int c,String s,String l,char sex)
	{
		codigo = c;
		senha = s;
		login = l;
		sexo = sex;
	}
	
	
	
	//setters
	 public void setCodigo(int c) {codigo = c;}
	 public void setSenha(String s) {senha = s;}
	 public void setLogin(String l) {login = l;}
	 public void setSexo(char s) {sexo = s;}
	 
	 //getters
	 public int getCodigo() {return codigo;}
	 public String getSenha() {return senha;}
	 public String getLogin() {return login;}
	 public char getSexo() {return sexo;}

	 @Override
	 public String toString() {
		 return "Usuario [codigo:  " + codigo + ", login: " + login + ", senha: " + senha + ", sexo: " + sexo +"]";
	 }
	 
	 
	
}
