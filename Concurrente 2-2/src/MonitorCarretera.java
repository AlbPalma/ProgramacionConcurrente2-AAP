
public class MonitorCarretera {
	private boolean libre;

	public MonitorCarretera() {
		libre = true;
		
	}

	public synchronized void obtenerPaso() {
		while (!libre) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//Fin while.
		
		libre =false;
		
	}

	public synchronized void liberarPaso(int a, int b) {
		
		System.out.println("Coche: "+a+" termina de cruzar.Veces que ha cruzado: "+b);
		libre=true;
		notify();
	}
}
