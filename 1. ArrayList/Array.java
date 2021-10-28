public class Array {
    private int defaultSize = 16;
    private int size;
    private String[] arr;

    public Array(){
        this.arr = new String[this.defaultSize];
        this.size = 0;
    }

    public Array(int n){
        this.arr = new String[n];
        this.size = 0;
    }

    public Array(String[] arr){
        this.size = arr.length;
        this.arr = new String[arr.length];
        for(int i = 0; i < arr.length; i++){
            this.arr[i] = arr[i];
        }
    }

    public String[] getArray() {
        int i = 0;
        String[] ans = new String[this.size];

        for(String s : this.arr){
            if(!(s == null)) ans[i++] = s;
        }

        return ans;
    }

    public String getAnElement(int i){
        if (i >= this.size){
            return "Invalid index";
        }else{
            return this.arr[i];
        }
    }

    public void add(String element){
        if(this.arr.length == this.size){
            int currentLength = this.arr.length;
            String[] tempArr = this.arr;
            this.arr = new String[currentLength*2];
            for(int i = 0; i < currentLength; i++) this.arr[i] = tempArr[i];
            this.arr[this.size++] = element;
        }else{
            this.arr[this.size++] = element;
        }
    }

    public void add(int i, String element){
        if(i >= this.arr.length){
            System.out.println("Invalid index");
        }else if(this.arr.length == this.size){

        }else{
            for(int j = this.size-1; j >= i; j--) this.arr[j+1] = this.arr[j];
            this.arr[i] = element;
            this.size += 1;
        }
    }

    public void remove(String element){
        for(int i = 0; i < this.size; i++){
            if(this.arr[i].equals(element)){
                if(i < this.size - 1){
                    for(int j = i+1; j < this.size; j++){
                        this.arr[j-1] = this.arr[j];
                        this.arr[j] = null;
                    }
                }else{
                    this.arr[i] = null;
                }
                this.size--;
                i = -1;
            }
        }
    }

    public int[] findIndex(String element){
        int c = 0;
        for(int i = 0; i < this.size; i++){
            if(this.arr[i].equals(element)) c++;
        }

        if(c == 0) return new int[0];

        int idx = 0;
        int[] idxArr = new int[c];
        for(int i = 0; i < this.size; i++){
            if(this.arr[i].equals(element)){
                idxArr[idx++] = i;
            }
        }

        return idxArr;
    }

    public String[] subArray(int start, int end){
        if(end < start){
            System.out.println("Invalid index parameters");
            return new String[]{};
        }

        if((start < 0) || (end >= this.arr.length)){
            System.out.println("Array index out of bounds");
            return new String[]{};
        }

        String[] subArr = new String[end-start+1];
        for(int i = start; i <= end; i++) subArr[i] = this.arr[i];

        return subArr;
    }

    public void merge(String[] a1, String[] a2){
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        String[] mergedArr = new String[a1.length+a2.length];

        while((i1 < a1.length) && (i2 < a2.length)){
            if(a1[i1].compareTo(a2[i2]) <= 0) mergedArr[i++] = a1[i1++];
            else mergedArr[i++] = a2[i2++];
        }

        while(i1 < a1.length) mergedArr[i++] = a1[i1++];
        while(i2 < a2.length) mergedArr[i++] = a2[i2++];

        for(int j = 0; j < mergedArr.length; j++) add(mergedArr[j]);
    }

    public int length() { return this.arr.length; }

    public boolean isEmpty() { return (this.size == 0) ? true : false; }
}
