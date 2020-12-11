
public class BancoDeRegistros {
	private Registro8bit B = new Registro8bit();
	private Registro8bit C = new Registro8bit();
	private Registro8bit D = new Registro8bit();
	private Registro8bit E = new Registro8bit();
	private boolean[] selReg= {false,false};
	private boolean cargaReg=false;
	
	public void setCargaReg(boolean carga) {
		this.cargaReg=carga;
	} //TODO
	
	public void setSelReg (boolean[] select) {
			this.selReg=select;
	}
	public boolean[] getValor() {
		boolean[] result;
		if (!selReg[0] && !selReg[1]) {
			result = B.getValor();
		}
		else if (!selReg[0] && selReg[1]) {
			result = C.getValor();
		}
		else if (selReg[0] && !selReg[1]) {
			result = D.getValor();
		}
		else {
			result = E.getValor();
		}
		return result;
	}
}
