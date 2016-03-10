package intelligence.swarm.aco;

import java.util.List;

import org.apache.log4j.Logger;

public class AntColonyOptimization {
	final static Logger LOGGER = Logger.getLogger(AntColonyOptimization.class);
	private static final Long LONG_MAX = (long) 1000;

	private TravelSalesmanProblem tsp;
	/**
	 * pheromone matrix
	 */
	private Double[][] pheromone;
	/**
	 * heuristic information matrix
	 */
	private Double[][] heuristic;
	/**
	 * combined value of pheromone X heuristic information
	 */
	private Double[][] probability;
	private Double initialPheromone = 1.0;

	private Long maxIterations; // Max iterations
	private Long iterations = (long) 0;
	private Long maxTours; // Maz tours
	private Long tours = (long) 0;
	private Double alpha;
	private Double beta;
	private Double rho;
	private Long nAnts;
	private Long seed = (long) -1;
	// TODO: Check if this variable can be removed. Seems to be used only in the
	// constructor
	private String instanceFileName;

	private List<Ant> colony;
	private Ant bestAnt;

	private AntColonyOptimization(Long maxIterations, Long maxTours, Double alpha, Double beta,
			Double rho, Long nAnts, Long seed, String instanceFileName) {
		super();
		this.maxIterations = maxIterations;
		this.maxTours = maxTours;
		this.alpha = alpha;
		this.beta = beta;
		this.rho = rho;
		this.nAnts = nAnts;
		this.seed = seed;
		this.instanceFileName = instanceFileName;

		this.tsp = new TravelSalesmanProblem(this.instanceFileName);
	}

	private AntColonyOptimization() {
		super();
	}

	public void printParameters() {
		// @formatter:off
		LOGGER.info("ACO parameters:\n" +
					"Ants: " + nAnts + "\n" +
					"Alpha: " + alpha + "\n" +
					"Beta: " + beta + "\n" +
					"Rho: " + rho + "\n" +
					"Tours: " + maxTours + "\n" +
					"Iterations: " + maxIterations + "\n" +
					"Seed: " + seed + "\n" +
					"Initial pheromone: " + initialPheromone);
		// @formatter:on
	}

	/**
	 * length of the shortest tour found
	 */
	private Long bestTourLength = LONG_MAX;

	public Long getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(Long maxIterations) {
		this.maxIterations = maxIterations;
	}

	public Long getMaxTours() {
		return maxTours;
	}

	public void setMaxTours(Long maxTours) {
		this.maxTours = maxTours;
	}

	public Long getTours() {
		return tours;
	}

	public void setTours(Long tours) {
		this.tours = tours;
	}

	public Double getAlpha() {
		return alpha;
	}

	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}

	public Double getBeta() {
		return beta;
	}

	public void setBeta(Double beta) {
		this.beta = beta;
	}

	public Double getRho() {
		return rho;
	}

	public void setRho(Double rho) {
		this.rho = rho;
	}

	public Long getnAnts() {
		return nAnts;
	}

	public void setnAnts(Long nAnts) {
		this.nAnts = nAnts;
	}

	public Long getSeed() {
		return seed;
	}

	public void setSeed(Long seed) {
		this.seed = seed;
	}

	public String getInstanceFileName() {
		return instanceFileName;
	}

	public void setInstanceFileName(String instanceFileName) {
		this.instanceFileName = instanceFileName;
	}

	/**
	 * Create ants for the colony
	 */
	public void createColony() {
		System.out.println("Creating colony.\n\n");
		for (int i = 0; i < nAnts; i++) {
			colony.add(new Ant(tsp, pheromone));
		}
	}

	public void initializePheromone() {
		int size = tsp.getSize().intValue();
		pheromone = new Double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				pheromone[i][j] = initialPheromone;
			}
		}
	}

	public void printPheromone() {
		// TODO: FINISH IMPLEMENTATION. PRINT SOMETHING HERE
		int size = tsp.getSize().intValue();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			}
		}
	}

	/**
	 * Initialize the heuristic information matrix 1/(cost of each edge)
	 */
	void initializeHeuristic() {

	}

	/**
	 * Calculate probability in base of heuristic information and pheromone sums
	 * division
	 */
	void calculateProbability() {

	}

	/**
	 * Pheromone evaporation
	 */
	void evaporatePheromone() {

	}

	/**
	 * Update pheromone
	 */
	void depositPheromone() {

	}

	/**
	 * Check termination condition
	 */
	Boolean terminationCondition() {

	}

	public static class AntColonyOptimizationBuilder {
		private Long maxIterations;
		private Long maxTours;
		private Double alpha;
		private Double beta;
		private Double rho;
		private Long nAnts;
		private Long seed;
		private String instanceFileName;

		public AntColonyOptimizationBuilder() {
		}

		public AntColonyOptimizationBuilder maxIterations(Long maxIterations) {
			this.maxIterations = maxIterations;
			return this;
		}

		public AntColonyOptimizationBuilder maxTours(Long maxTours) {
			this.maxTours = maxTours;
			return this;
		}

		public AntColonyOptimizationBuilder alpha(Double alpha) {
			this.alpha = alpha;
			return this;
		}

		public AntColonyOptimizationBuilder beta(Double beta) {
			this.beta = beta;
			return this;
		}

		public AntColonyOptimizationBuilder rho(Double rho) {
			this.rho = rho;
			return this;
		}

		public AntColonyOptimizationBuilder nAnts(Long nAnts) {
			this.nAnts = nAnts;
			return this;
		}

		public AntColonyOptimizationBuilder seed(Long seed) {
			this.seed = seed;
			return this;
		}

		public AntColonyOptimizationBuilder instanceFileName(String instanceFileName) {
			this.instanceFileName = instanceFileName;
			return this;
		}

		public AntColonyOptimization build() {
			return new AntColonyOptimization(maxIterations, maxTours, alpha, beta, rho, nAnts, seed,
					instanceFileName);
		}
	}

}