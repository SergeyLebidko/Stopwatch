package stopwatch;

public class PolygonCoords {

    int[] x;
    int[] y;

    public PolygonCoords(int[] x, int[] y) {
        this.x = x;
        this.y = y;
    }

    public int getCountPolygonPoints(){
        return x.length;
    }

}
