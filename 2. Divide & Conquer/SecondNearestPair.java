import java.util.ArrayList;

public class SecondNearestPair {
    Point from = null;
    Point to = null;
    boolean foundMin = false;
    Point[] xSort;
    Point[] ySort;

    public Triplet secondNearestPair(){
        Triplet t1 = nearestPair(0, xSort.length-1);
        this.from = t1.from;
        this.to = t1.to;
        this.foundMin = true;

        Triplet t2 = nearestPair(0, xSort.length-1);

        return t2;
    }

    public Triplet nearestPair(int a, int b){
        int n = b-a+1;

        if(n == 2){
            if(!foundMin || checkNotMin(xSort[a], xSort[b])) return new Triplet(xSort[a], xSort[b], distance(xSort[a], xSort[b]));
            else return new Triplet(null, null, Double.MAX_VALUE);
        }
        if(n == 3){
            double d1 = distance(xSort[a], xSort[b]);
            double d2 = distance(xSort[a], xSort[a+1]);
            double d3 = distance(xSort[b], xSort[a+1]);

            if(d1 <= d2 && d1 <= d3){
                if((from==null && to==null) || checkNotMin(xSort[a], xSort[b])) return new Triplet(xSort[a], xSort[b], d1);
                else if(d2 <= d3) return new Triplet(xSort[a], xSort[a+1], d2);
                else return new Triplet(xSort[a+1], xSort[b], d3);
            }
            else if(d2<= d1 && d2<= d3){
                if((from==null && to==null) || checkNotMin(xSort[a], xSort[a+1])) return new Triplet(xSort[a], xSort[a+1], d2);
                else if(d1 <= d3) return new Triplet(xSort[a], xSort[b], d1);
                else return new Triplet(xSort[a+1], xSort[b], d3);
            }
            else{
                if((from==null && to==null) || checkNotMin(xSort[a+1], xSort[b])) return new Triplet(xSort[a+1], xSort[b], d3);
                else if(d1 <= d2) return new Triplet(xSort[a], xSort[b], d1);
                else return new Triplet(xSort[a], xSort[a+1], d2);
            }
        }

        int mid = (a+b)/2;
        Triplet left = nearestPair(a, mid);
        Triplet right = nearestPair(mid+1, b);
        double d = -1;
        Point from_stripe = null, to_stripe = null;

        if(left.d <= right.d){
            d = left.d;
            from_stripe = left.from;
            to_stripe = left.to;
        }else{
            d = right.d;
            from_stripe = right.from;
            to_stripe = right.to;
        }

        ArrayList<Point> stripe = new ArrayList<>();
        double lowerRange = xSort[mid].x - d;
        double higherRange = xSort[mid].x + d;
        for(int i = 0; i < ySort.length; i++){
            if((ySort[i].x >= lowerRange) && (ySort[i].x <= higherRange)) stripe.add(ySort[i]);
        }

        for(int i = 0; i < stripe.size(); i++){
            for(int j = 1; j <= 7 && ((i+j) < stripe.size()); j++){
                double dist = distance(stripe.get(i), stripe.get(i+j));
                if((dist <= d) && (!foundMin || checkNotMin(stripe.get(i), stripe.get(i+j)))){
                    d = dist;
                    from_stripe = stripe.get(i);
                    to_stripe = stripe.get(i+j);
                }
            }
        }

        return new Triplet(from_stripe, to_stripe, d);
    }

    public void setXSort(Point[] arr){
        this.xSort = new Point[arr.length];
        for(int i = 0; i < arr.length; i++) this.xSort[i] = arr[i];
        mergeSort(this.xSort, 0, this.xSort.length-1, true);
    }

    public void setYSort(Point[] arr){
        this.ySort = new Point[arr.length];
        for(int i = 0; i < arr.length; i++) this.ySort[i] = arr[i];
        mergeSort(this.ySort, 0, this.ySort.length-1, false);
    }

    public double distance(Point a, Point b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public boolean checkNotMin(Point a, Point b){
        return (!a.equals(from) || !b.equals(to)) && (!a.equals(to) || !b.equals(from));
    }

    public void mergeSort(Point[] arr, int a, int b, boolean x){
        if(a < b){
            int c = (a+b)/2;
            mergeSort(arr, a, c, x);
            mergeSort(arr, c+1, b, x);
            merge(arr, a, c, b, x);
        }
    }

    public void merge(Point[] arr, int a, int c, int b, boolean x){
        Point[] first = new Point[c-a+1];
        Point[] second = new Point[b-c];

        for(int i = 0; i < (c-a+1); i++) first[i] = arr[a+i];
        for(int i = 0; i < (b-c); i++) second[i] = arr[c+1+i];

        int i = 0, j = 0, k = a;
        while((i < first.length) && (j < second.length)){
            if(x){
                if(first[i].x <= second[j].x) arr[k++] = first[i++];
                else arr[k++] = second[j++];
            }else{
                if(first[i].y <= second[j].y) arr[k++] = first[i++];
                else arr[k++] = second[j++];
            }
        }

        while(i < first.length) arr[k++] = first[i++];
        while(j < second.length) arr[k++] = second[j++];
    }
}
