package intelligence.swarm.aco;

import java.util.ArrayList;
import java.util.List;

public class Ant {
	private Boolean init;
	/**
	 * solution
	 */
	private List<Long> tour;
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
		this.tour = new ArrayList<Long>();
		this.visited = new ArrayList<Long>();
		init = false;
	}

	public Ant(TravelSalesmanProblem tsp, Double[][] probability) {
		super();
		this.probability = probability;
		this.tsp = tsp;
		this.size = tsp.getSize();
		this.tour = new ArrayList<Long>();
		this.visited = new ArrayList<Long>();
		this.tourLength = null;
		this.init = true;
	}

	public void computeTourLength() {
		// TODO: IMPLEMENT
	}

	public Long search() {
		// TODO: IMPLEMENT
		return null;
	}

	public Long getTourLength() {
		return tourLength;
	}
}
