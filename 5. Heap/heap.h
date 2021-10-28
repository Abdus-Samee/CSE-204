#include <iostream>
#include <vector>

using namespace std;

class Heap
{
    int capacity;
    int current;
    int* data;
public:
    Heap(int capacity) {
        this->capacity = capacity;
        this->current = 0;
        this->data = new int[capacity];
    }

    int size() { return this->capacity; }

    int getMax()
    {
        if(capacity) return data[0];
        else return INT_MIN;
    }

    void insert(int i)
    {
        if(this->current < this->capacity){
            this->data[this->current++] = i;

            int idx = this->current-1;
            while((idx != 0) && (this->data[parent(idx)] < this->data[idx])){
                int temp = this->data[idx];
                this->data[idx] = this->data[parent(idx)];
                this->data[parent(idx)] = temp;
                idx = parent(idx);
            }
        }
    }

    void deleteKey()
    {
        if(!this->current){
                cout << "No key found\n";
        }

        this->data[0] = this->data[this->current-1];
        this->capacity--;
        this->current--;
        heapify(0);
    }

    int parent(int idx)
    {
        return (idx-1)/2;
    }

    int left(int idx)
    {
         return 2*idx+1;
    }

    int right(int idx)
    {
        return 2*idx+2;

    }

    void heapify(int idx)
    {
        int l = left(idx);
        int r = right(idx);
        int maximum = idx;

        if((l < this->capacity) && (this->data[l] > this->data[maximum])) maximum = l;
        if((r < this->capacity) && (this->data[r] > this->data[maximum])) maximum = r;

        if(idx != maximum){
            int temp = this->data[idx];
            this->data[idx] = this->data[maximum];
            this->data[maximum] = temp;

            heapify(maximum);
        }
    }

    void heapSort()
    {
        int temp = this->data[0];
        this->data[0] = this->data[this->capacity-1];
        this->data[this->capacity-1] = temp;
        this->capacity--;

        heapify(0);
    }

    int getData(int idx)
    {
        return this->data[idx];
    }
};

void heapsort(vector<int>&v)
{
    Heap heap(v.size());

    for(int i : v) heap.insert(i);

    for(int i = 0; i < v.size(); i++) heap.heapSort();

    int len = v.size();
    for(int i = 0; i < len; i++) v[i] = heap.getData(len-i-1);
}
