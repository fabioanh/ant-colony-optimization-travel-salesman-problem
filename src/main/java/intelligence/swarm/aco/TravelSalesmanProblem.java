package intelligence.swarm.aco;

import java.util.List;

public class TravelSalesmanProblem {

	/**
	 * instance name
	 */
	private String name;
	private String edge_weight_type;
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
	private List<Point> nodeptr;
	/**
	 * distance matrix: distance[i][j] gives distance between city i und j
	 */
	private Long[][] distance;
	/* long int **nn_list; */ /*
								 * nearest neighbor list; contains for each node
								 * i a sorted list of n_near nearest neighbors
								 */

	public TravelSalesmanProblem(String fileName) {

	}

	public Long getSize() {
		return n;
	}

	/**
	 * Point class definition.
	 * 
	 * @author fakefla
	 *
	 */
	public class Point {
		private int x;
		private int y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}
}