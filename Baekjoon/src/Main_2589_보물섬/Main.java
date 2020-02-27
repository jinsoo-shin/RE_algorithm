package Main_2589_보물섬;

import java.util.*;
import java.io.*;

public class Main {
	// L 육지
	// W 바다
	// 육지로만 이동 가능, 보물간 최단 거리로 이동하는 시간 구하기
	// 가로 세로는 범위 50이하
	// 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다

	static char map[][];
	static int R, W, ans;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/Main_2589_보물섬/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = 0;
		R = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[R][W];
		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}
		System.out.println(ans);
	}

	static void bfs(int x, int y) {
		boolean[][] visit = new boolean[R][W];
		Queue<Land> q = new LinkedList<>();
		visit[x][y] = true;
		q.add(new Land(x, y, 0));
		while (!q.isEmpty()) {
			Land cur = q.poll();
			if(ans<cur.time) {
				ans= cur.time;
			}
			for (int i = 0; i < 4; i++) {
				int cx = cur.x + dx[i];
				int cy = cur.y + dy[i];
				if (cx >= R || cy >= W || cx < 0 || cy < 0 || visit[cx][cy] || map[cx][cy] == 'W') {
					continue;
				}
				visit[cx][cy] = true;
				q.add(new Land(cx, cy, cur.time + 1));
			}
		}

	}

	static class Land {
		int x;
		int y;
		int time;

		@Override
		public String toString() {
			return "Land [x=" + x + ", y=" + y + ", time=" + time + "]";
		}

		public Land(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}
}
