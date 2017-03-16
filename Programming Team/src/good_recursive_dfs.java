import java.io.*;

class good_recursive_dfs {
	static void dfs(int a[][], int m[], int i, int n) {
		int j;
		System.out.println("\t" + (i + 1));
		m[i] = 1;
		for (j = 0; j < n; j++)
			if (a[i][j] == 1 && m[j] == 0)
				dfs(a, m, j, n);
	}

	public static void main(String args[]) throws IOException {
		int n, i, j;
		System.out.println("No. of vertices : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m[] = new int[n];
		int a[][] = new int[n][n];
		for (i = 0; i < n; i++) {
			m[i] = 0;
		}
		System.out.println("\n\nEnter 1 if edge is present, 0 if not");
		for (i = 0; i < n; i++) {
			System.out.println("\n");
			for (j = i; j < n; j++) {
				System.out.println("Edge between " + (i + 1) + " and " + (j + 1) + " : ");
				a[i][j] = Integer.parseInt(br.readLine());
				a[j][i] = a[i][j];
			}
			a[i][i] = 0;
		}
		System.out.println("\nOrder of accessed nodes : \n");
		for (i = 0; i < n; i++)
			if (m[i] == 0)
				dfs(a, m, i, n);

	}
}