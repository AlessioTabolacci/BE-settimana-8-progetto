package it.contocorrente.entity;

public class ContoCorrente {

	private String iban;
	private String intestatario;
	private double saldo;
	private String dataCreazione;



	//Getters
	public String getIban() { return iban; }
	public String getIntestatario() { return intestatario; }
	public double getSaldo() { return saldo; }
	public String getDataCreazione() { return dataCreazione; }



	//Setters
	public void setIban(String iban) { this.iban = iban; }
	public void setIntestatario(String intestatario) { this.intestatario = intestatario; }
	public void setSaldo(double saldo) { this.saldo = saldo; }
	public void setDataCreazione(String dataCreazione) { this.dataCreazione = dataCreazione; }

}
