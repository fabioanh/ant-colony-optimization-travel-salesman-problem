package intelligence.swarm.aco;

import java.util.Random;

public class RandomUtils {
	private static RandomUtils instance = null;
	private static Random random;

	private RandomUtils(Long seed) {
		random = new Random(seed);
	}

	public static RandomUtils getInstance(Long seed) {
		if (instance == null && seed != null) {
			instance = new RandomUtils(seed);
		}
		return instance;
	}

	/**
	 * Returns an unbounded random integer
	 * 
	 * @return
	 */
	public Integer getRandomInt() {
		return random.nextInt();
	}

	/**
	 * Returns a bounded random integer
	 * 
	 * @param upperBound
	 * @return
	 */
	public Integer getRandomInt(Integer upperBound) {
		return random.nextInt(upperBound);
	}

	public Random getRandom() {
		return random;
	}

}
