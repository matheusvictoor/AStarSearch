public class HeapMin {
    private Point[] heap;
    private int tail;

    public HeapMin(int capacity){
        this.heap = new Point[capacity];
        this.tail = -1;
    }

    public boolean isEmpty() {
        return this.tail == -1;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * (index + 1);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    public void add(Point point) {
        if (this.tail >= (this.heap.length -1))
            resize();

        this.tail += 1;
        this.heap[this.tail] = point;

        int i = this.tail;
        while (i > 0 && this.heap[parent(i)].getEnergy() > this.heap[i].getEnergy()) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
 
    private void resize() {
        Point[] newHeap = new Point[this.heap.length * 2];
        System.arraycopy(this.heap, 0, newHeap, 0, this.heap.length);
        this.heap = newHeap;
    }

    private void swap(int i, int parent) {
       Point aux = this.heap[parent];
       this.heap[parent] = this.heap[i];
       this.heap[i] = aux;
    }

    public Point remove() {
        if (isEmpty()) throw new RuntimeException("Empty heap");

        Point element = this.heap[0];
        this.heap[0] = this.heap[this.tail];
        this.tail -= 1;


        heapify(0);

        return element;
    }

    // organizes the tree by placing the smallest point at position zero
    private void heapify(int index) {
        if (isLeaf(index) || !isValidation(index))
            return;

        int minIndex = minIndex(index, left(index), right(index));

        if (minIndex != index) {
            swap(index, minIndex);
            heapify(minIndex);
        }
    }

    private boolean isLeaf(int index) {
        return index > parent(this.tail) && index <= this.tail;
    }

    private boolean isValidation(int index) {
        return index >= 0 && index <= this.tail;
    }

    private int minIndex(int index, int left, int right) {
        int minIndex = index;

        if (left <= this.tail && this.heap[left].getEnergy() < this.heap[minIndex].getEnergy()) {
            minIndex = left;
        }

        if (right <= this.tail && this.heap[right].getEnergy() < this.heap[minIndex].getEnergy()) {
            minIndex = right;
        }
        return minIndex;
    }

    public static void main(String[] args) {
        HeapMin heap = new HeapMin(10);

        int tam = 10;
        
        for (int i = 0; i < tam; i++) {
           int randomNumber = (int) Math.floor(Math.random() * 100);
           heap.add(new Point(3, 5, randomNumber));
       }

       for (int i = 0; i < tam; i++) {
           System.out.println(heap.remove());
       }
    }
}
