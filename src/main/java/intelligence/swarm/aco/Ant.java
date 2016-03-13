package intelligence.swarm.aco;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Ant {

	private final static Logger LOGGER = Logger.getLogger(Ant.class);

	@SuppressWarnings("unused")
	private Boolean init;
	/**
	 * solution
	 */
	private ArrayList<Long> tour;
	/**
	 * track of visited cities
	 */
	private List<Long> visited;
	private Long tourLength;

	/**
	 * 
	 * Matrix of the pheromone of the colony!
	 * 
	 */
	private Double[][] probability;
	private TravelSalesmanProblem tsp;
	private Long size;

	public Ant() {
		super();
		this.setTour(new ArrayList<Long>());
		this.setVisited(new ArrayList<Long>());
		init = false;
	}

	public Ant(Ant ant) {
		this.init = ant.getInit();
		this.size = ant.getSize();
		this.tourLength = ant.getTourLength();
		this.probability = new Double[size.intValue()][size.intValue()];
		System.arraycopy(ant.getProbability(), 0, this.probability, 0, ant.getProbability().length);
		this.tour = new ArrayList<>(ant.getTour());
		this.visited = new ArrayList<>(ant.getVisited());
		this.tsp = new TravelSalesmanProblem(ant.getTsp());
	}

	public Ant(TravelSalesmanProblem tsp, Double[][] probability) {
		super();
		this.probability = probability;
		this.tsp = tsp;
		this.size = tsp.getSize();
		this.setTour(new ArrayList<Long>());
		this.setVisited(new ArrayList<Long>());
		this.tourLength = null;
		this.init = true;
	}

	public void computeTourLength() {
		Long totalDistance = Long.valueOf(0);
		for (int i = 0; i < size.intValue() - 1; i++) {
			totalDistance += tsp.getDistance()[tour.get(i).intValue()][tour.get(i + 1).intValue()];
		}
		totalDistance += tsp.getDistance()[tour.get(tour.size() - 1).intValue()][tour.get(0)
				.intValue()];
		tourLength = totalDistance;
	}

	// Implemented using the closest neighbour heuristic
	public Long searchClosestCity(Long seed) {
		setVisited(new ArrayList<>());
		tour = new ArrayList<>();
		Long currentCity = Long
				.valueOf(RandomUtils.getInstance(seed).getRandomInt(size.intValue()));
		getVisited().add(currentCity);
		tour.add(currentCity);
		// Long tmpClosest = null;
		while (getVisited().size() < size) {
			currentCity = getVisited().get(getVisited().size() - 1);
			currentCity = closestCity(currentCity.intValue());
			getVisited().add(currentCity);
			tour.add(currentCity);
		}
		return null;
	}

	public Long search(Long seed) {
		setVisited(new ArrayList<>());
		tour = new ArrayList<>();
		Long initialCity = Long
				.valueOf(RandomUtils.getInstance(seed).getRandomInt(size.intValue()));
		getVisited().add(initialCity);
		tour.add(initialCity);

		computeRandomProportionalTour(seed, initialCity);

		computeTourLength();
		return tourLength;
	}

	/**
	 * Based on the probabilities matrix compute the tour using a random
	 * proportional rule (matrix)
	 * 
	 * @param seed
	 * @param initialCity
	 */
	private void computeRandomProportionalTour(Long seed, final Long initialCity) {
		Long currentCity = initialCity;
		Double rnd;
		Double tmpSumProbability;
		Double tmpTotal;

		while (visited.size() < size) {
			tmpSumProbability = 0.0;
			// Compute the total sum of the probabilities for the current city
			// used later to normalize the values and get the random next city
			for (int j = 0; j < size; j++) {
				if (!visited.contains(Long.valueOf(j))) {
					tmpSumProbability += probability[currentCity.intValue()][j];
				}
			}
			tmpTotal = tmpSumProbability;

			rnd = RandomUtils.getInstance(seed).getRandom().nextDouble();
			tmpSumProbability = 0.0;
			for (int j = 0; j < size; j++) {
				if (!visited.contains(Long.valueOf(j))) {
					tmpSumProbability += probability[currentCity.intValue()][j] / tmpTotal;
					if (tmpSumProbability >= rnd) {
						currentCity = Long.valueOf(j);
						visited.add(currentCity);
						tour.add(currentCity);
						break;
					}
				}
			}
		}
	}

	private Long closestCity(int currentCity) {
		Long closestCity = null;
		Long closestDistance = Long.MAX_VALUE;
		for (int j = 0; j < size; j++) {
			if (!visited.contains(j)) {
				if (tsp.getDistance()[currentCity][j] < closestDistance) {
					closestDistance = tsp.getDistance()[currentCity][j];
					closestCity = Long.valueOf(j);
				}
			}
		}
		return closestCity;
	}

	public Long getTourLength() {
		return tourLength;
	}

	private List<Long> getVisited() {
		return visited;
	}

	private void setVisited(List<Long> visited) {
		this.visited = visited;
	}

	public ArrayList<Long> getTour() {
		return tour;
	}

	public void setTour(ArrayList<Long> tour) {
		this.tour = tour;
	}

	public Boolean getInit() {
		return init;
	}

	public void setInit(Boolean init) {
		this.init = init;
	}

	public Double[][] getProbability() {
		return probability;
	}

	public void setProbability(Double[][] probability) {
		this.probability = probability;
	}

	public TravelSalesmanProblem getTsp() {
		return tsp;
	}

	public void setTsp(TravelSalesmanProblem tsp) {
		this.tsp = tsp;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public void setTourLength(Long tourLength) {
		this.tourLength = tourLength;
	}

	public void printTour() {
		StringBuilder sb = new StringBuilder();
		for (Long t : tour) {
			sb.append(t + " ");
		}
		LOGGER.info(sb.toString().trim());
	}
}
