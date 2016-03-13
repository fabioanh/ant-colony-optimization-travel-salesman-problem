package intelligence.swarm.aco;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import intelligence.swarm.aco.AntColonyOptimization.AntColonyOptimizationBuilder;

/**
 * Hello world!
 *
 */
public class App {

	private final static Logger LOGGER = Logger.getLogger(App.class);

	private static final String TOURS = "tours";
	private static final String ITERATIONS = "iterations";
	private static final String ANTS = "ants";
	private static final String RHO = "rho";
	private static final String BETA = "beta";
	private static final String ALPHA = "alpha";
private static final String SEED = "seed";
	private static final String INSTANCE = "instance";

	public static void main(String[] args) {
		readArguments(args);
	}

	/**
	 * Sets the default parameters to the ACO Builder class
	 * 
	 * @return
	 */
	private static AntColonyOptimizationBuilder setDefaultParameters() {
		AntColonyOptimizationBuilder acoBuilder = new AntColonyOptimization.AntColonyOptimizationBuilder();
		acoBuilder.alpha(1.0);
		acoBuilder.beta(1.0);
		acoBuilder.rho(0.2);
		acoBuilder.nAnts((long) 10);
		acoBuilder.maxIterations((long) 0);
		acoBuilder.maxTours((long) 1000);
		// TODO: Set the correct seed (constant one)
		acoBuilder.seed(-1L);
		return acoBuilder;
	}

	private static AntColonyOptimization readArguments(String[] args) {

		AntColonyOptimizationBuilder acoBuilder = setDefaultParameters();
		try {
			Options options = new Options();

			options.addOption(INSTANCE, true, "Path for the instance file of the TSP");
			options.addOption(SEED, true, "Seed to be used in the random numbers obtention");
			options.addOption(ALPHA, true, "Value for Alpha");
			options.addOption(BETA, true, "Value for Beta");
			options.addOption(RHO, true, "Value for Rho");
			options.addOption(ANTS, true, "Number of ants");
			options.addOption(ITERATIONS, true, "Maximum number of iterations");
			options.addOption(TOURS, true, "Maximum number of tours");

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd;
			cmd = parser.parse(options, args);

			if (cmd.getOptionValue(ANTS) != null) {
				acoBuilder = acoBuilder.nAnts(Long.parseLong(cmd.getOptionValue(ANTS)));
			}
			if (cmd.getOptionValue(ALPHA) != null) {
				acoBuilder = acoBuilder.alpha(Double.parseDouble(cmd.getOptionValue(ALPHA)));
			}
			if (cmd.getOptionValue(BETA) != null) {
				acoBuilder = acoBuilder.beta(Double.parseDouble(cmd.getOptionValue(BETA)));
			}
			if (cmd.getOptionValue(RHO) != null) {
				acoBuilder = acoBuilder.rho(Double.parseDouble(cmd.getOptionValue(RHO)));
			}
			if (cmd.getOptionValue(ITERATIONS) != null) {
				acoBuilder = acoBuilder
						.maxIterations(Long.parseLong(cmd.getOptionValue(ITERATIONS)));
			}
			if (cmd.getOptionValue(TOURS) != null) {
				acoBuilder = acoBuilder.maxTours(Long.parseLong(cmd.getOptionValue(TOURS)));
			}
			if (cmd.getOptionValue(SEED) != null) {
				acoBuilder = acoBuilder.seed(Long.parseLong(cmd.getOptionValue(SEED)));
			}
			if (cmd.getOptionValue(INSTANCE) != null) {
				acoBuilder = acoBuilder.instanceFileName(cmd.getOptionValue(INSTANCE));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		LOGGER.debug("Parameters read properly. Attempt to create TSP instances and run problem");

		return acoBuilder.build();
	}
}
