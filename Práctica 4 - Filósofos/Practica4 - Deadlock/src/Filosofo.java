import java.util.Random;
import java.util.concurrent.locks.Lock;

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
	 * Monitor que controla los recursos
	 */
	private Monitor monitor;

	/**
	 * Se construye un filósofo con un id único y dos cubiertos.
	 */
	public Filosofo (int id, Lock cubiertoIzquierdo, Lock cubiertoDerecho) {
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
			System.out.println("El Filósofo " + id + " fue interrumpido.\n");			
		}
	}

	/** 
	 * Se imprime el mensaje en el que el filósofo piensa.
	 * Cuando esto pasa el filósofo duerme por un tiempo aleatorio.
	 */
	private void piensa() throws InterruptedException {
		System.out.println("El Filósofo " + id + " está pensando.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}

	/** 
	 * El filósofo ocupa un cubierto (Recurso compartido)
	 * El filósofo levanta el cubierto izquierdo para comer (Hace un lock del recurso para que nadie más pueda ocuparlo)
	 */
	private void levantaCubiertoIzquierdo() {
		cubiertoIzquierdo.lock();
		System.out.println("El filósofo " + id + " tiene en la mano un cubierto.\n");
	}

	private void levantaCubiertoDerecho() {
		cubiertoDerecho.lock();
	}

	/**
	 * El filósofo come...
	 * @throws InterruptedException
	 */
	private void come() throws InterruptedException {
		System.out.println("El filósofo " + id + " está comiendo.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}

	/**
	 * El filósofo baja los cubiertos y los recursos son desbloqueados.
	 */
	private void bajaCubiertos() {
		cubiertoIzquierdo.unlock();
		cubiertoDerecho.unlock();
		
	}
}