package db;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L; //serial obrigat�rio para runtimeException (aceitar sugest�o)
	
	public DbException(String msg) { //o erro retorna uma string
		super(msg);
	}
}
