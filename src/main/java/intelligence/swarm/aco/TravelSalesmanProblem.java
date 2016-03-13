package intelligence.swarm.aco;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class TravelSalesmanProblem {
	private final static Logger LOGGER = Logger.getLogger(TravelSalesmanProblem.class);

	private static final String EUC_2D = "EUC_2D";
	private static final String ATT = "ATT";
	private static final String GEO = "GEO";
	private static final String CEIL_2D = "CEIL_2D";
	private static final String NAME = "NAME";
	private static final String EDGE_WEIGHT_TYPE = "EDGE_WEIGHT_TYPE";
	private static final String DIMENSION = "DIMENSION";
	private static final String NODE_COORD_SECTION = "NODE_COORD_SECTION";
	private static final String[] SUPPORTED_EDGE_FORMATS = new String[] { CEIL_2D, GEO, ATT,
			EUC_2D };
	/**
	 * instance name
	 */
	@SuppressWarnings("unused")
	private String name;
	private String edgeWeightType;
	/**
	 * optimal tour length if known, otherwise a bound
	 */
	private Long optimum;
	/**
	 * number of cities
	 */
	private Long n;
	/**
	 * number of nearest neighbors
	 */
	private Long n_near;
	/**
	 * List containing coordinates of nodes
	 */
	private ArrayList<Point> nodes;
	/**
	 * distance matrix: distance[i][j] gives distance between city i und j
	 */
	private Long[][] distance;
	/* long int **nn_list; */ /*
								 * nearest neighbor list; contains for each node
								 * i a sorted list of n_near nearest neighbors
								 */

	public TravelSalesmanProblem(TravelSalesmanProblem tsp) {
		this.edgeWeightType = tsp.getEdgeWeightType();
		this.n = tsp.getN();
		this.name = tsp.getName();
		this.distance = new Long[n.intValue()][n.intValue()];
		System.arraycopy(tsp.getDistance(), 0, this.distance, 0, tsp.getDistance().length);
		this.nodes = new ArrayList<>(tsp.getNodes());
	}

	public TravelSalesmanProblem(String fileName) {

		LOGGER.debug("Attempt to create TSP");

		BufferedReader reader;
		nodes = new ArrayList<>();
		try {
			reader = Files.newBufferedReader(Paths.get(fileName), Charset.defaultCharset());
			String line = null;
			while (!(line = reader.readLine()).equals(NODE_COORD_SECTION)) {
				processNonCoordinates(line);
			}
			for (int i = 0; i < n; i++) {
				processCoordinate(reader.readLine());
			}
			setDistance(computeDistances());

			LOGGER.debug("TSP instance set up properly. Number of cities found: " + n);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Long[][] computeDistances() {
		Long[][] dists = new Long[n.intValue()][n.intValue()];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dists[i][j] = computeDistance(i, j);
			}
		}
		return dists;
	}

	private Long computeDistance(int i, int j) {
		switch (edgeWeightType) {
		case EUC_2D:
			return roundDistance(i, j);
		case CEIL_2D:
			return ceilDistance(i, j);
		case GEO:
			return geoDistance(i, j);
		case ATT:
			return attDistance(i, j);
		default:
			return null;
		}
	}

	private Long attDistance(int i, int j) {
		Double deltaX = nodes.get(i).getX() - nodes.get(j).getX();
		Double deltaY = nodes.get(i).getY() - nodes.get(j).getY();
		Double rij = Math.sqrt(deltaX * deltaX + deltaY * deltaY) / 10.0;
		Double tij = Long.valueOf(rij.longValue()).doubleValue();
		Long dij;
		if (tij < rij) {
			dij = tij.longValue() + 1;
		} else {
			dij = tij.longValue();
		}
		return dij;
	}

	private Long geoDistance(int i, int j) {
		Double deg, min;
		Double latI, latJ, longI, longJ;
		Double q1, q2, q3;
		Long dd;
		Double x1 = nodes.get(i).getX();
		Double y1 = nodes.get(i).getY();
		Double x2 = nodes.get(j).getX();
		Double y2 = nodes.get(j).getY();
		deg = Long.valueOf(x1.longValue()).doubleValue();
		min = x1 - deg;
		latI = Math.PI * (deg + 5.0 * min / 3.0) / 180.0;
		deg = Long.valueOf(x2.longValue()).doubleValue();
		min = x2 - deg;
		latJ = Math.PI * (deg + 5.0 * min / 3.0) / 180.0;
		deg = Long.valueOf(y1.longValue()).doubleValue();
		min = y1 - deg;
		longI = Math.PI * (deg + 5.0 * min / 3.0) / 180.0;
		deg = Long.valueOf(y2.longValue()).doubleValue();
		min = y2 - deg;
		longJ = Math.PI * (deg + 5.0 * min / 3.0) / 180.0;
		q1 = Math.cos(longI - longJ);
		q2 = Math.cos(latI - latJ);
		q3 = Math.cos(latI + latJ);
		dd = (long) (6378.388 * Math.acos(0.5 * ((1.0 + q1) * q2 - (1.0 - q1) * q3)) + 1.0);
		return dd;
	}

	private Long ceilDistance(int i, int j) {
		Double deltaX = nodes.get(i).getX() - nodes.get(j).getX();
		Double deltaY = nodes.get(i).getY() - nodes.get(j).getY();
		Double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY) + 0.000000001;
		return dist.longValue();
	}

	private Long roundDistance(int i, int j) {
		Double deltaX = nodes.get(i).getX() - nodes.get(j).getX();
		Double deltaY = nodes.get(i).getY() - nodes.get(j).getY();
		Double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY) + 0.5;
		return dist.longValue();
	}

	private void processCoordinate(String line) {
		String[] coords = line.trim().split(" ");
		nodes.add(new Point(Double.valueOf(coords[1]), Double.valueOf(coords[2])));
	}

	private void processNonCoordinates(String line) {
		if (line.startsWith(DIMENSION)) {
			n = Long.valueOf(line.split(" ")[1]);
		} else if (line.startsWith(EDGE_WEIGHT_TYPE)) {
			String edgeWeightTypeInput = line.split(" ")[1];
			edgeWeightType = Arrays.asList(SUPPORTED_EDGE_FORMATS).contains(edgeWeightTypeInput)
					? edgeWeightTypeInput : null;
		} else if (line.startsWith(NAME)) {
			name = line.split(" ")[1];
		}
		// TODO: Add implementation if something to do with the other non
		// coordinates lines is required
	}

	public Long getSize() {
		return n;
	}

	public Long[][] getDistance() {
		return distance;
	}

	private void setDistance(Long[][] distance) {
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEdgeWeightType() {
		return edgeWeightType;
	}

	public void setEdgeWeightType(String edgeWeightType) {
		this.edgeWeightType = edgeWeightType;
	}

	public Long getN() {
		return n;
	}

	public void setN(Long n) {
		this.n = n;
	}

	public ArrayList<Point> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Point> nodes) {
		this.nodes = nodes;
	}

	/**
	 * Point class definition.
	 * 
	 * @author fakefla
	 *
	 */
	public class Point {
		private Double x;
		private Double y;

		public Point(Double x, Double y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Double getX() {
			return x;
		}

		public void setX(Double x) {
			this.x = x;
		}

		public Double getY() {
			return y;
		}

		public void setY(Double y) {
			this.y = y;
		}

	}
}