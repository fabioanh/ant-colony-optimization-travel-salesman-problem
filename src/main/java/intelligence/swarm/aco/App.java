package intelligence.swarm.aco;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App {

	private static Long maxIterations; // Max iterations
	private Long iterations = (long) 0;
	private static Long maxTours; // Maz tours
	private static Long tours = (long) 0;
	private static Double alpha;
	private static Double beta;
	private static Double rho;
	private static Long nAnts;
	private static Long seed = (long) -1;
	private static String instanceFileName;

	public static void main(String[] args) {
		readArguments(args);
	}

	private static void setDefaultParameters() {
		alpha = 1.0;
		beta = 1.0;
		rho = 0.2;
		nAnts = (long) 10;
		maxIterations = (long) 0;
		maxTours = (long) 10000;
		// TODO: Set the correct seed (constant one)
		seed = null;
	}

	private static void readArguments(String[] args) {

		setDefaultParameters();

		try {
			Options options = new Options();

			CommandLineParser parser = new DefaultParser();
			CommandLine cmd;
			cmd = parser.parse(options, args);

			if (cmd.getOptionValue("ants") != null) {
				nAnts = Long.parseLong(cmd.getOptionValue("ants"));
			}
			if (cmd.getOptionValue("alpha") != null) {
				alpha = Double.parseDouble(cmd.getOptionValue("alpha"));
			}
			if (cmd.getOptionValue("beta") != null) {
				beta = Double.parseDouble(cmd.getOptionValue("beta"));
			}
			if (cmd.getOptionValue("rho") != null) {
				rho = Double.parseDouble(cmd.getOptionValue("rho"));
			}
			if (cmd.getOptionValue("iterations") != null) {
				maxIterations = Long.parseLong(cmd.getOptionValue("iterations"));
			}
			if (cmd.getOptionValue("tours") != null) {
				tours = Long.parseLong(cmd.getOptionValue("tours"));
			}
			if (cmd.getOptionValue("seed") != null) {
				seed = Long.parseLong(cmd.getOptionValue("seed"));
			}
			if (cmd.getOptionValue("instance") != null) {
				instanceFileName = cmd.getOptionValue("instance");
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
