package intelligence.swarm.aco;

public class AntColonyOptimizationBuilder {
	private static class AntColonyOptimizationState {
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
	}
}
