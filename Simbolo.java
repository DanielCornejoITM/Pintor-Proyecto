public class Simbolo{
	String etiqueta;
	Integer direccion=0x00;
	String tipo;
	Integer tam=0x00;
	public Simbolo(String etiqueta, Integer direccion, String tipo){
		this.etiqueta=etiqueta;
		this.direccion=direccion;
		this.tipo=tipo;
	}

	public void defTam(int tam){
		this.tam=tam;
	}

	public int getTam(){
		return tam;
	}
}