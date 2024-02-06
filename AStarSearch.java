import java.util.Scanner;

public class AStarSearch {
    public static int minimumEnergy(String[] grid, int x_initial, int y_initial, int x_final, int y_final){
        int n = grid.length;
        int m = grid[0].length();

        if (1 <= n && n < 1500 && 1 <= m && m < 1500 && 0 <= x_initial && 0 <= y_initial && x_final <= n && y_final <= m ) {
            
            // final coordenates are in an obstacle and cannot reach them
            if (grid[x_final].charAt(y_final) == '#') {
                return -1;
            }

            HeapMin heapMin = new HeapMin(n * m);
            boolean[][] visited = new boolean[n][m];

            heapMin.add(new Point(x_initial, y_initial, 0));

            while (!heapMin.isEmpty()) {
                Point current = heapMin.remove();
                int x = current.getX();
                int y = current.getY();
                int energy = current.getEnergy();

                // check if you have reached the end point
                if (x == x_final && y == y_final) {
                    return energy;
                }
                
                // walking to the right
                if (y + 1 < m && !visited[x][y + 1] && grid[x].charAt(y + 1) != '#') {
                    heapMin.add(new Point(x, y + 1, energy));
                }

                // walking down
                if (x + 1 < n && !visited[x + 1][y] && grid[x + 1].charAt(y) != '#') {
                   heapMin.add(new Point(x + 1, y, energy)); 
                }

                // walking to the left
                if (y - 1 >= 0 && !visited[x][y - 1] && grid[x].charAt(y - 1) != '#') {
                    heapMin.add(new Point(x, y - 1, energy + 1));
                }

                // walking up
                if (x - 1 >= 0 && !visited[x - 1][y] && grid[x - 1].charAt(y) != '#') {
                    heapMin.add(new Point(x - 1, y, energy + 1));
                }

                // make the point as visited
                visited[x][y] = true;
            }
        }
        
        // unable to reach coordinates
        return -1;
    }
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
              
        // use # as the grid barries and . as free path

        // Example of use
        String[] grid = {
            "......#...",
            ".####.#.#.",
            "..#.#.#.#.",
            "#.#.#.#.#.",
            "#.#...#.#.",
            "#.#.###.#.", 
            "..#.....#.",
            ".########.", 
            "....#...#.", 
            "#.#...#.#.", 
        };
          
        System.out.println("Enter the INITIAL X coordinate:");
        int initial_x = Integer.parseInt(sc.nextLine());

        System.out.println("Enter the INITIAL Y coordinate:");
        int initial_y = Integer.parseInt(sc.nextLine());

        System.out.println("Enter the END X coordinate:");
        int final_x = Integer.parseInt(sc.nextLine());

        System.out.println("Enter the END Y coordinate:");
        int final_y = Integer.parseInt(sc.nextLine());

        int result = minimumEnergy(grid, initial_x, initial_y, final_x, final_y);
        System.out.println("\nThe path with o least energy expenditure was: " + result);

        sc.close();
     
    }
}