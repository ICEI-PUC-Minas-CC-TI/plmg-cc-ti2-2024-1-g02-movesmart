package model;

public class Usuario 
{
	// atributos
	private int    codigo;
	private String login ;
	private String senha ;
	private String email ;
	
	// Construtor
	public Usuario( ) 
	{
		this.codigo = -1 ;
		this.login  = "" ;
		this.senha  = "" ;
		this.email   = '*';
	} // end Usuario ( )
	
	// Construtor Alternativo
	public Usuario( int codigo, String login, String senha, String email ) 
	{
		setCodigo( codigo );
		setLogin ( login  );
		setSenha ( senha  );
		setSexo  ( email   );
	} // end Usuario ( )
	
	// Construtor Alternativo
	public Usuario( String login, String senha, String email ) 
	{
		setLogin ( login  );
		setSenha ( senha  );
		setSexo  ( email   );
	} // end Usuario ( )

	// Retorna o codigo do usuario 
	public int getCodigo( ) 
	{
		return codigo;
	} // end getCodigo ( )

	// Atribui um codigo ao usuario
	public void setCodigo( int codigo ) 
	{
		this.codigo = codigo;
	} // end setCodigo ( )

	// Retorna o login do usuario 
	public String getLogin( ) 
	{
		return login;
	} // end getLogin ( )

	// Atribui um login ao usuario
	public void setLogin( String login ) 
	{
		this.login = login;
	} // end setLogin ( )

	// Retorna a senha do usuario 
	public String getSenha( ) 
	{
		return senha;
	} // end getSenha ( )

	// Atribui uma senha ao usuario
	public void setSenha( String senha ) 
	{
		this.senha = senha;
	} // end setSenha ( )

	// Retorna o email do usuario 
	public String getSexo()
	{
		return email;
	} // end getSexo ( )

	// Atribui um email ao usuario
	public void setSexo( String email ) 
	{
		this.email = email;
	} // end setSexo ( )

	// 
	@Override
	public String toString( ) 
	{
		return "Usuario [" + codigo + "," + login + "," + senha + "," + email + "]";
	} // end toString ( )
	
	@Override
	public boolean equals( Object obj ) 
	{
		return (this.getCodigo() == ((Usuario) obj).getCodigo());
	} // end equals ( )	
} // end class
