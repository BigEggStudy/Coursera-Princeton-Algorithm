import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Point2D;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private double[][] energy;
    private double[][] distTo;
    private int[][] edgeToX;
    private int[][] edgeToY;

    /**
     * create a seam carver object based on the given picture
     * @param picture picture for seam carver
     */
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }

        this.picture = new Picture(picture);
    }

    /**
     * current picture
     * @return current picture
     */
    public Picture picture() {
        return new Picture(this.picture);
    }

    /**
     * width of current picture
     * @return width of current picture
     */
    public int width() {
        return this.picture.width();
    }

    /**
     * height of current picture
     * @return height of current picture
     */
    public int height() {
        return this.picture.height();
    }

    /**
     * energy of pixel at column x and row y
     * @param x column x
     * @param y row y
     * @return energy of pixel at column x and row y
     */
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new IllegalArgumentException();
        }

        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return 1000;
        }

        return Math.sqrt(
                squareGradient(this.picture.get(x - 1, y), this.picture.get(x + 1, y))
                        + squareGradient(this.picture.get(x, y - 1), this.picture.get(x, y + 1))
        );
    }

    private int squareGradient(Color color1, Color color2) {
        int r = color1.getRed() - color2.getRed();
        int g = color1.getGreen() - color2.getGreen();
        int b = color1.getBlue() - color2.getBlue();

        return r * r + g * g + b * b;
    }

    private void computePictureEnergy() {
        this.energy = new double[width()][height()];
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                this.energy[col][row] = energy(col, row);
            }
        }
    }

    /**
     * sequence of indices for horizontal seam
     * @return sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() {
        this.energy = new double[width()][height()];
        this.distTo = new double[width()][height()];
        this.edgeToX = new int[width()][height()];
        this.edgeToY = new int[width()][height()];
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                this.energy[col][row] = energy(col, row);
                if (col == 0) {
                    this.distTo[col][row] = this.energy[col][row];
                } else {
                    this.distTo[col][row] = Double.POSITIVE_INFINITY;
                }
                this.edgeToX[col][row] = col;
                this.edgeToY[col][row] = row;
            }
        }

        for (int col = 0; col < width() - 1; col++) {
            for (int row = 0; row < height(); row++) {
                if (row >= 1) {
                    relax(col, row, col + 1, row - 1);
                }
                relax(col, row, col + 1, row);
                if (row + 1 < height()) {
                    relax(col, row, col + 1, row + 1);
                }
            }
        }

        double shortestLength = Double.POSITIVE_INFINITY;
        int endRow = 0;
        for (int row = 0; row < height(); row++) {
            if (distTo[width() - 1][row] < shortestLength) {
                shortestLength = distTo[width() - 1][row];
                endRow = row;
            }
        }

        int[] result = new int[width()];
        result[width() - 1] = endRow;
        for (int col = width() - 2; col >= 0; col--) {
            result[col] = this.edgeToY[col + 1][result[col + 1]];
        }

        this.energy = null;
        this.distTo = null;
        this.edgeToX = null;
        this.edgeToY = null;
        return result;
    }

    /**
     * sequence of indices for vertical seam
     * @return sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() {
        this.energy = new double[width()][height()];
        this.distTo = new double[width()][height()];
        this.edgeToX = new int[width()][height()];
        this.edgeToY = new int[width()][height()];
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                this.energy[col][row] = energy(col, row);
                if (row == 0) {
                    this.distTo[col][row] = this.energy[col][row];
                } else {
                    this.distTo[col][row] = Double.POSITIVE_INFINITY;
                }
                this.edgeToX[col][row] = col;
                this.edgeToY[col][row] = row;
            }
        }

        for (int row = 0; row < height() - 1; row++) {
            for (int col = 0; col < width(); col++) {
                if (col >= 1) {
                    relax(col, row, col - 1, row + 1);
                }
                relax(col, row, col, row + 1);
                if (col + 1 < width()) {
                    relax(col, row, col + 1, row + 1);
                }
            }
        }

        double shortestLength = Double.POSITIVE_INFINITY;
        int endCol = -1;
        for (int col = 0; col < width(); col++) {
            if (distTo[col][height() - 1] < shortestLength) {
                shortestLength = distTo[col][height() - 1];
                endCol = col;
            }
        }

        int[] result = new int[height()];
        result[height() - 1] = endCol;
        for (int row = height() - 2; row >= 0; row--) {
            result[row] =  this.edgeToX[result[row + 1]][row + 1];
        }

        this.energy = null;
        this.distTo = null;
        this.edgeToX = null;
        this.edgeToY = null;
        return result;
    }

    private void relax(int fromX, int fromY, int toX, int toY) {
        if (this.distTo[toX][toY] > this.distTo[fromX][fromY] + this.energy[toX][toY]) {
            this.distTo[toX][toY] = this.distTo[fromX][fromY] + this.energy[toX][toY];
            this.edgeToX[toX][toY] = fromX;
            this.edgeToY[toX][toY] = fromY;
        }
    }

    /**
     * remove horizontal seam from current picture
     * @param seam horizontal seam from current picture
     */
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != width() || height() <= 1 || seam[0] < 0 || seam[0] >= height()) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= height() || Math.abs(seam[i] - seam[i - 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }

        Picture result = new Picture(width(), height() - 1);
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height() - 1; row++) {
                if (row < seam[col]) {
                    result.set(col, row, picture.get(col, row));
                } else {
                    result.set(col, row, picture.get(col, row + 1));
                }
            }
        }
        this.picture = result;
    }

    /**
     * remove vertical seam from current picture
     * @param seam vertical seam from current picture
     */
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != height() || width() <= 1 || seam[0] < 0 || seam[0] >= width()) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= width() || Math.abs(seam[i] - seam[i - 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }

        Picture result = new Picture(width() - 1, height());
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width() - 1; col++) {
                if (col < seam[row]) {
                    result.set(col, row, this.picture.get(col, row));
                } else {
                    result.set(col, row, this.picture.get(col + 1, row));
                }
            }
        }
        this.picture = result;
    }
}
