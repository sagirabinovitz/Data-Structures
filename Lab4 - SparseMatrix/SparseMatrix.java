
public class SparseMatrix<T> implements Matrix<T> {

    private T defaultValue;
    private DLinkedList<SparseMatrixEntry> dlist;
    private int size;
    private boolean transpose;

    public SparseMatrix(T defaultValue) {
        this(MAX_SIZE, defaultValue);
    }

    public SparseMatrix(int size, T defaultValue) {
        this.defaultValue = defaultValue;
        this.dlist = new DLinkedList<>();
        this.size = size;
        this.transpose = false;
    }

    @Override
    public T get(int row, int col) {
        if (transpose) {
            int temp = row;
            row = col;
            col = temp;
        }
        if (!checkPrecon(row, col)) {
            throw new IllegalArgumentException();
        }
        dlist.goToBeginning();
        SparseMatrixEntry entry = dlist.getCursor();
        while (entry != null) {
            if (entry.getRow() == row && entry.getCol() == col) {
                return entry.getValue();
            }
            entry = dlist.getNext();
        }

        return defaultValue;
    }

    @Override
    public void set(int row, int col, T element) {
        if (transpose) {
            int temp = row;
            row = col;
            col = temp;
        }
        if (!checkPrecon(row, col)) {
            throw new IllegalArgumentException();
        }
        dlist.goToBeginning();
        SparseMatrixEntry entry = dlist.getCursor();
        while (entry != null) {
            if (entry.getRow() == row && entry.getCol() == col) {
                if (element == defaultValue) {
                    dlist.remove(entry);
                    return;
                }
                entry.setValue(element);
                return;
            }
            entry = dlist.getNext();
        }
        entry = new SparseMatrixEntry(row, col, element);
        dlist.insert(entry);

    }

    @Override
    public void transpose() {
        transpose = !transpose;

    }

    private boolean checkPrecon(int i, int j) {
        return i >= 1 && j >= 1 && i <= size && j <= size;
    }

    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(int row, int col, T val) {
            this.value = val;
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public T getValue() {
            System.out.println(value);
            return value;
        }
        public void setValue(T newVal) {
            this.value = newVal;
        }
    }
}