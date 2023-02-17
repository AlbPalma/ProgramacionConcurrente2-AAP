import java.util.Random;





public class Coche extends Thread{
	private enum STATE{LIBRE,ESPERANDO,CRUZANDO};
	private STATE state;
	private int id;
	private static int totalCoches;
	private MonitorCarretera monitor;
	private int vecesCruzado;
	
	public Coche(MonitorCarretera m) {
		id = (totalCoches++)+1;
		
		state = STATE.LIBRE;
		monitor = m;
		start();
	}
	
	private void libre() {
		System.out.println("Coche "+id+" está libre.");
		Random rnd = new Random();
		int Time = rnd.nextInt(250-50+1)+50;
		
		try {
			sleep(Time);
			
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(id+ " deja de estar libre.Quiere cruzar el puente.");
		state = STATE.ESPERANDO;
		
		
	}//Fin libre.
	
	private void esperar() {
		System.out.println("Coche "+id+" está esperando.");
		monitor.obtenerPaso();
		state = STATE.CRUZANDO;
	}
	private void cruzar() {
		System.out.println("Coche "+id+" está cruzando.");
		
		Random rnd = new Random();
		int Time = rnd.nextInt(250-50+1)+50;
		
		try {
			sleep(Time);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		vecesCruzado++;
		//System.out.println("Coche: "+id+"termina de cruzar.Veces que ha cruzado: "+vecesCruzado);
		monitor.liberarPaso(id,vecesCruzado);
		state= STATE.LIBRE;
		
		
	}//Fin cruzar.
	
	public void run() {
		while(true) {
			switch(state) {
			case CRUZANDO:
				cruzar();
				break;
			case ESPERANDO:
				esperar();
				break;
			case LIBRE:
			libre();
			break;
			
			}
			
			
		}//Fin while.
		
	}
	
	
}
