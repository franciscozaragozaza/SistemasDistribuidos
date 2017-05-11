/**
 * El monitor asegura que el filósofo levantará únicamente
 * ambos cubiertos cuando sus vecinos nó estén comiendo.
 *
 */
class Monitor {
	private enum State {PENSANDO, HAMBRIENTO, COMIENDO};
	private State[] estadoFilosofo;

	/**
	 * Constructor que crea un monitor para el número adecuado de filósofos
	 * Como estado inicial, todos los filósofos están pensando.
	 */

	public Monitor (int numFilosofos) {
		estadoFilosofo = new State[numFilosofos];
		for (int i = 0; i < estadoFilosofo.length; i++) {
			estadoFilosofo[i] = State.PENSANDO;
		}
	}

	/**
	 * Un filósofo toma ambos cubiertos.
	 * El filósofo se pone a pensar si ambos vecinos comen.
	 */
	public synchronized void levantaCubiertos(int idFilosofo) throws InterruptedException {
		/**
		 * Establece el estado del filosofo como hambriento e imprime un mensaje indicándolo.
		 */
		estadoFilosofo[idFilosofo] = State.HAMBRIENTO;
		System.out.println("Filósofo: " + idFilosofo + " está hambriento.\n");
		while (losVecinosComen(idFilosofo)) {
			/**
			 * Mientras los vecinos comen.
			 * El filósofo debería estar pensando.
			 */
			wait();
		}
		/**
		 * Cuando los vecinos dejan de comer el filosofo empieza a comer.
		 */
		estadoFilosofo[idFilosofo] = State.COMIENDO;
		System.out.println("Filósofo: " + idFilosofo + " está comiendo.\n");
		System.out.flush();
	}

	private boolean losVecinosComen(int idFilosofo) {
		/**
		 * Verifica que no haya filósofos comiendo de ningún lado.
		 */
		if (estadoFilosofo[(idFilosofo + 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;
		if (estadoFilosofo[(idFilosofo + estadoFilosofo.length - 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;

		return false;
	}

	/**
	 * El filósofo baja los cubiertos y se notifica a todos que ya
	 * pueden ocupar los cubiertos.
	 */
	public synchronized void bajarCubiertos(int idFilosofo) {
		/**
		 * Al bajar los cubiertos se establece el estado del filósofo a PENSANDO que lo deja en espera.
		 */
		estadoFilosofo[idFilosofo] = State.PENSANDO;
		notifyAll();
	}
}
