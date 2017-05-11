import java.util.Random;

/**
 * Runnable es usado para implementar los hilos (threads).
 */
class Filosofo implements Runnable{
	/**
	 * Random es el tiempo en que come un filósofo.
	 * id es el número que identifica a cada filósofo.
	 */
	private Random numGenerator = new Random();
	private int id;

	/**
	 * Monitor que controla los recursos.
	 */
	private Monitor monitor;

	/**
	 * Se construye un filósofo con un id único.
	 */
	public Filosofo (int id, Monitor monitor) {
		this.id = id;
		this.monitor = monitor;
	}
	/**
	 * Ejecuta el thread, piensa, levanta cubiertos, come.
	 */
	public void run() {
		try {
			while (true) {
				piensa();
				monitor.levantaCubiertos(id);
				come();
				monitor.bajarCubiertos(id);
			}
		} catch (InterruptedException e) {
			System.out.println("Filósofo: " + id + " interrumpido.\n");
		}
	}

	/**
	 * Se imprime el mensaje en el que el filósofo piensa.
	 * Cuando esto pasa el filósofo duerme por un tiempo aleatorio.
	 */
	private void piensa() throws InterruptedException {
		System.out.println("Filósofo " + id + " está pensando.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}

	/**
	 * El filósofo come por un tiempo aleatorio al dormir el hilo.
	 */
	private void come() throws InterruptedException {
		Thread.sleep (numGenerator.nextInt(10));
	}
}