//import java.util.LinkedList;
//import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer {

	//private static Queue<Integer> store = new LinkedList<Integer>();
	private static int store = 1000;
	public static final int bSize= 5;
	
	private static Semaphore sNoVacío= new Semaphore(2,true);
	private static Semaphore sNoLleno = new Semaphore(bSize,true);
	public static int getStore() {
		return store;
	}
	public static Semaphore getsNoVacío() {
		return sNoVacío;
	}
	public static Semaphore getsNoLleno() {
		return sNoLleno;
	}
	public static void setStore(int store) {
		Buffer.store = Buffer.store-store;
	}
	public static void setStore2(int store) {
		Buffer.store = Buffer.store+store;
	}
}
