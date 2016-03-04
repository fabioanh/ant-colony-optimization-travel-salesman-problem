package intelligence.swarm.aco;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AntColonyOptimization {
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
	private String instanceFileName;

	private List<Ant> colony;
	private Ant bestAnt;
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
	void createColony() {
		System.out.println("Creating colony.\n\n");
		for (int i = 0; i < nAnts; i++) {
			colony.add(new Ant(tsp, pheromone));
		}
	}

	public class AntColonyOptimizationBuilder {
		private Long maxIterations;
		private Long maxTours;
		private Long tours;
		private Double alpha;
		private Double beta;
		private Double rho;
		private Long nAnts;
		private Long seed;
		private String instanceFileName;

		public AntColonyOptimizationBuilder maxIterations(Long maxIterations) {
			return this;
		}

		public AntColonyOptimizationBuilder maxTours(Long maxTours) {
			return this;
		}

		public AntColonyOptimizationBuilder tours(Long tours) {
			return this;
		}

		public AntColonyOptimizationBuilder alpha(Double alpha) {
			return this;
		}

		public AntColonyOptimizationBuilder beta(Double beta) {
			return this;
		}

		public AntColonyOptimizationBuilder rho(Double rho) {
			return this;
		}

		public AntColonyOptimizationBuilder nAnts(Long nAnts) {
			return this;
		}

		public AntColonyOptimizationBuilder seed(Long seed) {
			return this;
		}

		public AntColonyOptimizationBuilder instanceFileName(String instanceFileName) {
			return this;
		}
	}

}