package it.contocorrente.entity;

public class Movimento {


	private String dataMovimento;
	private double importo;
	private String iban;
	private TipoOperazione tipo;


	//Getters
	public String getDataMovimento() { return dataMovimento; }
	public double getImporto() { return importo; }
	public String getIban() { return iban; }
	public TipoOperazione getTipo() { return tipo; }

	//Setters
	public void setDataMovimento(String dataMovimento) { this.dataMovimento = dataMovimento; }
	public void setImporto(double importo) { this.importo = importo; }
	public void setIban(String iban) { this.iban = iban; }
	public void setTipo(TipoOperazione tipo) { this.tipo = tipo; }
	








}
