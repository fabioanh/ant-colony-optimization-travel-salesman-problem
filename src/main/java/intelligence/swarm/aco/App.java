package intelligence.swarm.aco;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import intelligence.swarm.aco.AntColonyOptimization.AntColonyOptimizationBuilder;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		AntColonyOptimization aco = readArguments(args);
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
		acoBuilder.seed(null);
		return acoBuilder;
	}

	private static AntColonyOptimization readArguments(String[] args) {

		AntColonyOptimizationBuilder acoBuilder = setDefaultParameters();
		try {
			Options options = new Options();

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd;
			cmd = parser.parse(options, args);

			if (cmd.getOptionValue("ants") != null) {
				acoBuilder = acoBuilder.nAnts(Long.parseLong(cmd.getOptionValue("ants")));
			}
			if (cmd.getOptionValue("alpha") != null) {
				acoBuilder = acoBuilder.alpha(Double.parseDouble(cmd.getOptionValue("alpha")));
			}
			if (cmd.getOptionValue("beta") != null) {
				acoBuilder = acoBuilder.beta(Double.parseDouble(cmd.getOptionValue("beta")));
			}
			if (cmd.getOptionValue("rho") != null) {
				acoBuilder = acoBuilder.rho(Double.parseDouble(cmd.getOptionValue("rho")));
			}
			if (cmd.getOptionValue("iterations") != null) {
				acoBuilder = acoBuilder
						.maxIterations(Long.parseLong(cmd.getOptionValue("iterations")));
			}
			if (cmd.getOptionValue("tours") != null) {
				acoBuilder = acoBuilder.maxTours(Long.parseLong(cmd.getOptionValue("tours")));
			}
			if (cmd.getOptionValue("seed") != null) {
				acoBuilder = acoBuilder.seed(Long.parseLong(cmd.getOptionValue("seed")));
			}
			if (cmd.getOptionValue("instance") != null) {
				acoBuilder = acoBuilder.instanceFileName(cmd.getOptionValue("instance"));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return acoBuilder.build();
	}
}
