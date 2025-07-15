public class ArrayUtils {
	private static void reverse(int[] array, int start, int end) {
		while (start < end) {
			int tmp = array[start];
			array[start] = array[end];
			array[end] = tmp;
			start++;
			end--;
		}
	}

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		int n = array.length;
		if (n == 0) { // case of empty array
			return array;
		}
		if (direction != 'R' & direction != 'L') { //case of invalid direction
			return array;
		}
		if (move < 0) { //condition to update move for minus
			if (direction == 'R') {
				return shiftArrayCyclic(array, -move, 'L');
			} else {
				return shiftArrayCyclic(array, -move, 'R');
			}
		}
		move = move % n;
		if (direction == 'L') {
			move = n - move;
		}
		reverse(array, 0, n - 1); //reverse the array
		reverse(array, 0, move - 1); // reverse the first part
		reverse(array, move, n - 1); // reverse the second part
		return array;
	}

	public static int rec_short_path(int[][] m, int start, int end, int[][] pathMatrix, boolean[] visited) {
		int n = m.length; //num of columns
		if (start == end) return 0; // if we reach end - there is a path
		if (pathMatrix[start][end] != -1) return pathMatrix[start][end];
		visited[start] = true; //to avoid checking the path to start
		int min_path = Integer.MAX_VALUE; //to secure taking the minimum each call
		for (int i = 0; i < n; i++) {
			if (m[start][i] == 1 && !visited[i]) {
				int path = rec_short_path(m, i, end, pathMatrix, visited); // if didn't visit yet and there is a path
				{
					if (path != -1) {
						min_path = Math.min(path + 1, min_path);
					}
				}
			}
		}
		visited[start] = false;
		pathMatrix[start][end] = (min_path == Integer.MAX_VALUE) ? -1 : min_path;
		return pathMatrix[start][end];
	}
	public static int findShortestPath(int[][] m, int i, int j) {
		int n = m.length;
		int[][] pathMatrix = new int[n][n];
		boolean[] visited = new boolean[n]; //default is false
		for (int k =0; k<n;k++){ //initialize pathMatrix to -1
			for (int t=0; t<n;t++){
				pathMatrix[k][t] = -1;
			}
		}
		return rec_short_path(m, i, j, pathMatrix, visited);
	}

}
