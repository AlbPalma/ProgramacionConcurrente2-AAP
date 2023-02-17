import java.util.Random;

public class Proceso extends Thread{
	private int id ;
	private int cantidad=0;
	public Proceso(int n) {
		id = n;
		start();
	}
	
	private void consumo() {
		Random rdmNum = new Random();
		int numP= rdmNum.nextInt(999)+1;
		int sleepTime= rdmNum.nextInt(250-25+1)+25;
		
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cantidad = numP;
		
		if(cantidad>Buffer.getStore()) {
			System.out.println("Proceso: "+id+"intenta tomar: "+ cantidad+". No hay suficiente, sólo hay: "+Buffer.getStore() );
			
		
		}
		else {
			Buffer.setStore(cantidad);
			System.out.println("Proceso: "+id+" toma: "+ cantidad+". Restan: "+Buffer.getStore() );
			System.out.println("Proceso: "+id+" espera." );
			
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			libero();
		}
		
		
		
		//Buffer.getStore().add(numP);
	}
	
	
	private void libero() {
		Random rdmNum = new Random();
		//int numP= rdmNum.nextInt(999)+1;
		int sleepTime= rdmNum.nextInt(250-25+1)+25;
		
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Proceso "+id+" devuelve "+ cantidad +"." );
		
		Buffer.setStore2(cantidad);
		System.out.println("Buffer: " + Buffer.getStore()+". ID: "+ id );
	}
	
	public void run() {
		while(true) {
			if(Buffer.getStore()== 0) {
				System.out.println("Buffer vacío. Esperando que se liberen recursos.");
				
			}
			
			try {
				
				Buffer.getsNoVacío().acquire();
				
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			consumo();
			
			
			
			Buffer.getsNoVacío().release();
		}
		
	}//Fin run


}//Fin de la clase.
