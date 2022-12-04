import java.util.*;
import java.util.Random;
class Algoritm
{
    static int ROW = 8; //количество строк
    static int COL = 8; //количество столбцов


    private static int[][] buildM() {  // функция генерации случайной матрицы

        int[][] maze = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
               if (Math.random()>0.3) maze[i][j] =1; else maze[i][j] =0 ;
            }

        } return(maze);
    }

    static class Cell
    {
        int x;
        int y;

        public Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    };


    static class queueNode
    {
        Cell pt;
        int length;

        public queueNode(Cell pt, int length)
        {
            this.pt = pt;
            this.length = length;
        }
    };


    static boolean checkValid(int row, int col)
    {
        return ((row >= 0) && (row < ROW) && (col >= 0) && (col < COL));
    }


    static int rowNum[] = {-1, 0, 0, 1};
    static int colNum[] = {0, -1, 1, 0};


    static int Lee(int mat[][], Cell src, Cell dest)
    {

        if (mat[src.x][src.y] != 1 || mat[dest.x][dest.y] != 1)
            return -1;

        boolean [][]visited = new boolean[ROW][COL];


        visited[src.x][src.y] = true;


        Queue<queueNode> q = new LinkedList<>();


        queueNode s = new queueNode(src, 0);
        q.add(s);


        while (!q.isEmpty())
        {
            queueNode curr = q.peek();
            Cell pt = curr.pt;


            if (pt.x == dest.x && pt.y == dest.y)
                return curr.length;
            q.remove();

            for (int i = 0; i < 4; i++)
            {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];


                if (checkValid(row, col) &&
                        mat[row][col] == 1 &&
                        !visited[row][col])
                {

                    visited[row][col] = true;
                    queueNode Adjcell = new queueNode
                            (new Cell(row, col),
                                    curr.length + 1 );
                    q.add(Adjcell);
                }
            }
        }


        return -1;
    }


    public static void main(String[] args)
    {

        int mat[][]=buildM(); // генерируем случайную матрицу

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(mat[i][j]); }
            System.out.println(' ');
            }
        /*int mat[][] =
                {{ 1, 0, 1, 1, 1 },
                 { 1, 0, 1, 0, 1 },
                 { 1, 1, 1, 1, 1 },
                 { 0, 0, 1, 0, 1 },
                 { 1, 1, 1, 0, 1 }};
           */
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(4, 0);

        int length = Lee(mat, source, dest);

        if (length != -1)
            System.out.println("длинна самого короткого пути " + length);
        else
            System.out.println("Путь не существует");
    }
}