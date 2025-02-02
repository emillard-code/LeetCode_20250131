package com.project.attempt;

public class LeetCodeAttempt {

    public static void main(String[] args) {

        int[][] grid1 = new int[][]{{1,0},{0,1}};
        System.out.println(makingALargeIsland(grid1));

        int[][] grid2 = new int[][]{{1,1},{1,0}};
        System.out.println(makingALargeIsland(grid2));

        int[][] grid3 = new int[][]{{1,1},{1,1}};
        System.out.println(makingALargeIsland(grid3));

    }

    // This method returns the size of the largest island that
    // can be created by changing the value of exactly one 0 to 1.
    public static int makingALargeIsland(int[][] grid) {

        // We will store our solution in int largestSize.
        int largestSize = 0;

        // In the off-chance that the entire array is filled with
        // nothing but 1s, we have a boolean value to check for this.
        boolean filled = true;

        // We will loop through every single index and test out the size of the
        // island that can be attained if we convert that index from 0 to 1.
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                // If the index is already 1, we skip this iteration of the loop.
                // Otherwise, we set boolean filled as false to indicate there's at
                // least one 0 within the grid.
                if (grid[i][j] == 1) { continue; }
                else { filled = false; }

                // We make a copy of the grid to avoid recursion issues.
                // We then set the current index to 1 in order to test it.
                int[][] newGrid = daCopy(grid);
                newGrid[i][j] = 1;

                // We call a helper method to calculate the island size from the current state
                // of the grid. We overwrite int largestSize if this size turns out to be bigger
                // than the existing value of int largestSize.
                int size = islandSize(newGrid, i, j);
                if (size > largestSize) { largestSize = size; }

            }

        }

        // If it turns out the entire grid is nothing but 1s, we simply return
        // the multiplication of the length of the grid with the width of the grid.
        if (filled) { return grid.length * grid[0].length; }

        // Otherwise, we return int largestSize which should hold the largest size that
        // had been obtained thus far by testing various indexes being converted to 1.
        return largestSize;

    }

    // A helper method that calculates the size of an island given certain starting coordinates.
    private static int islandSize(int[][] grid, int startPointX, int startPointY) {

        // If for some reason we start on a tile that's 0, return 0.
        if (grid[startPointX][startPointY] == 0) { return 0; }

        // Otherwise, set the current tile to 0 so that later recursive calls of this method
        // will not mistakenly try to go back to this specific coordinate.
        grid[startPointX][startPointY] = 0;

        // int totalSize will hold the total size of the island that can currently be traversed.
        int totalSize = 0;

        // For each cardinal direction (that is a valid path to travel towards), we will recursively
        // call this method and increment int totalSize with the value that the method call returns.
        // Once we have added the total values from all valid directions, we return int totalSize
        // plus 1 (to account for the current tile), to indicate the total size of the island.
        if (startPointX + 1 < grid.length && grid[startPointX + 1][startPointY] == 1) {
            totalSize += islandSize(grid, startPointX + 1, startPointY);
        }

        if (startPointX - 1 >= 0 && grid[startPointX - 1][startPointY] == 1) {
            totalSize += islandSize(grid, startPointX - 1, startPointY);
        }

        if (startPointY + 1 < grid[0].length && grid[startPointX][startPointY + 1] == 1) {
            totalSize += islandSize(grid, startPointX, startPointY + 1);
        }

        if (startPointY - 1 >= 0 && grid[startPointX][startPointY - 1] == 1) {
            totalSize += islandSize(grid, startPointX, startPointY - 1);
        }

        return totalSize + 1;

    }

    // A helper method that creates a new, identical copy of an array
    // to help avoid problems that could arise from recursion.
    private static int[][] daCopy(int[][] array) {

        int[][] newDoubleArray = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {

                newDoubleArray[i][j] = array[i][j];

            }

        }

        return newDoubleArray;

    }

}
