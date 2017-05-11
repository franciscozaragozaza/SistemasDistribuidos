
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * El problema de los filósofos (Esta versión causa un Deadlock).
 *
 */
public class CenaFilosofos {
	/**
	 *El número de filósofos como un entero.
	 */
	private static final int NUM_FILOSOFOS = 5;
	
	/**
	 * Una prueba de los filósofos.
	 */
	public static void main (String[] args) {
		/**
		 *  Cada tenedor es un recurso compartido.
		 *  Los recursos compartidos en Java se definen como tipo Lock.
		 *  
		 */ 
		Lock[]	tenedores = new ReentrantLock[NUM_FILOSOFOS];
		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			tenedores[i] = new ReentrantLock();
		}
		/**
		 *  Se inicia el con 5 filósofos
		 *  En el siguiente ejemplo se implementa un código para inicializar un Thread por cada filósofo.
		 */
		Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
		
		for (int i = 0; i < NUM_FILOSOFOS; i++) {	
			filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i+1)%NUM_FILOSOFOS]);
			new Thread(filosofos[i]).start();
			}
		
		
	}

}
