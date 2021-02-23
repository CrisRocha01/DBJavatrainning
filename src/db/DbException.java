package db;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L; //serial obrigatório para runtimeException (aceitar sugestão)
	
	public DbException(String msg) { //o erro retorna uma string
		super(msg);
	}
}
