
public class DiagonalMatrix implements Matrix {


    double mat[];
    boolean flag = false;

    public DiagonalMatrix() {
        mat = new double[MAX_SIZE * 2 - 1];
    }

    public DiagonalMatrix(int size) {
        mat = new double[2 * size - 1];
    }


    @Override
    public double get(int i, int j) {
        if (flag) {
            int temp = i;
            i = j;
            j = temp;
        }
        int x = j - i;
        if (i < 1 || j > (mat.length + 1) / 2) {
            throw new RuntimeException("out of range");
        }
        if (x >= 0) {
            return mat[x];
        } else {
            return mat[mat.length + x];
        }
    }

    @Override
    public void set(int i, int j, double x) {
        if (flag) {
            int temp = i;
            i = j;
            j = temp;
        }
        int y = j - i;
        if (i < 1 || j > (mat.length + 1) / 2) {
            throw new RuntimeException("out of range");
        }
        if (y >= 0) {
            mat[y] = x;
        } else {
            mat[mat.length + y] = x;
        }
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= (mat.length + 1) / 2; i++) {
            for (int j = 1; j <= (mat.length + 1) / 2; j++) {
                if(j==(mat.length + 1) / 2){
                    s.append(get(i, j));
                }
                else {
                    s.append(get(i, j));
                    s.append("\t");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }


    @Override
    public void transpose() {
        flag = (!(flag));
    }

    @Override
    public Matrix getTranspose() {
        DiagonalMatrix B = new DiagonalMatrix();
        B.mat = mat;
        B.flag = flag;
        B.transpose();
        return B;

    }
}
