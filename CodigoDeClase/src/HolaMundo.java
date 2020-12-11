
public class HolaMundo {

	public static void main(String[] args) {
		
		String nombres[] = {"Jon Ander","Beñat","Eneko","Juan","Jon"};
		
		for (int i=0;i<nombres.length;i++) {
		System.out.println(String.format("Hola %s!!!",nombres[i]));
		}
		
		//tipos primitivos
		byte b=5; // 1 byte = 8 bits -128 a 127
		short s = 5;// 16 bits -32768 a 32767
		int in=5; // 32 bits
		long l=5; //64 bits
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(System.currentTimeMillis());//El tiempo transcurrido desde 1970
		float f=2; // 32 bits con decimales
		double d=2; //64 bits con decimales
		System.out.println(Double.MAX_VALUE);
		System.out.println(Double.MIN_VALUE);
		char c = 'A';
		boolean bool = true; // false
				
		//tipos no primitivos
		String str = "";

	}

}
