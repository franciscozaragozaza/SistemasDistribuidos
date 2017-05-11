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
		 *  Se inicia el con 5 filósofos
		 *  En el siguiente ejemplo se implementa un código para inicializar un Thread por cada filósofo.
		 */
		Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
		Monitor monitor = new Monitor(NUM_FILOSOFOS);
		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			filosofos[i] = new Filosofo(i, monitor);
			new Thread(filosofos[i]).start();
		}
	}
}
