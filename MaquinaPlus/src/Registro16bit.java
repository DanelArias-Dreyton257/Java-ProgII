//TODO
//Falta el clk
public class Registro16bit {
	private boolean[] valor= {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private boolean carga=false;
	
	public void setValor(boolean[] valor) {
		if(carga) {
			this.valor=valor;
			}
	}
	public boolean[] getValor() {return this.valor;}
	
	public void setCarga(boolean carga) {this.carga=carga;}

}
