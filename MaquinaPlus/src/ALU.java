import java.io.*;
import java.util.*;
public class ALU {
	private boolean[] selALU= {false,false,false,false};
	private boolean flagC=false;
	private boolean flagZ= false;
	
	public void setSelALU(boolean[] sel) {
		this.selALU=sel;
	}//TODO
	
	public void setFlagZ(boolean z) {this.flagZ=z;}
	public void setFlagC(boolean c) {this.flagC=c;}
	public boolean getFlagZ() {return this.flagZ;}
	public boolean getFlagC() {return this.flagC;}
	
	public boolean[] exe(boolean[] a,boolean[] b) {
		boolean[] result= {false,false,false,false,false,false,false,false};
		if (!selALU[0] && !selALU[1] && !selALU[2] && !selALU[3]) {
			result = suma(a,b);
		}
		else if (!selALU[0] && !selALU[1] && !selALU[2] && selALU[3]) {
			//result = resta(); //TODO
		}
		else if (!selALU[0] && !selALU[1] && selALU[2] && !selALU[3]) {
			//result = and(a, b);//TODO
		}
		else if (!selALU[0] && !selALU[1] && selALU[2] && selALU[3]) {
			//result = or(a, b);//TODO
		}
		else if  (!selALU[0] && selALU[1] && !selALU[2] && !selALU[3]) {
			// = xor(a,b);//TODO
		}
		else if (!selALU[0] && selALU[1] && !selALU[2] && selALU[3]) {
			//result = not(a);//TODO
		}
		else if  (!selALU[0] && selALU[1] && selALU[2] && !selALU[3]) {
			//result = b;
		}
		else {
			
		}
			
		//flags
		
		/*if (result==0x0) {
			this.setFlagZ(true);
		}*/
		
		return result;
	}

	public boolean[] suma (boolean[] a,boolean[] b) {
		boolean[] r= {false,false,false,false,false,false,false,false};
		boolean carry=false;
		for (int i=a.length-1;i>=0;i--) {
				if (a[i] && b[i] && carry) {
					//1 + carry
					r[i]=true;
					carry=true;
				}
				else if (((!a[i] && b[i])||(a[i] && !b[i]))&& carry) {
					//1 + no carry
					r[i]=false;
					carry=true;
				}
				else if (!a[i] && !b[i] && carry){
					//0 + no carry
					r[i]=true;
					carry=false;
				}
				//NO CARRY
				else if (a[i] && b[i] && !carry) {
					//1 + carry
					r[i]=false;
					carry=true;
				}
				else if (((!a[i] && b[i])||(a[i] && !b[i])) && !carry) {
					//1 + no carry
					r[i]=true;
					carry=false;
				}
				else {
					//0 + no carry
					r[i]=false;
					carry=false;
				}
		}
		if (carry) {setFlagC(carry);}
		return r;
	}
	public static void main(String[] args) {
		ALU alu = new ALU();
		boolean[] sel= {false,false,false,false};
		alu.setSelALU(sel);
		boolean[] a= {true,false,false,false,false,false,false,true};//81
		boolean[] b= {true,false,false,false,false,false,true,true};//81
		boolean[] c=alu.exe(a, b);
		for(int i=0;i<c.length;i++) {
			System.out.print("["+c[i]+"], ");
			if (i==c.length-1) {
			System.out.println("");}
		}
		System.out.println(alu.flagC);
	}
}