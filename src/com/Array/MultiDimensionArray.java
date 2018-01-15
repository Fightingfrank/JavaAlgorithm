package com.Array;

public class MultiDimensionArray {
    int [] dimensions; //
    int [] factors; //
    Object[] data;

    public MultiDimensionArray(int[] arg){
        dimensions = new int[arg.length];
        factors = new int[arg.length];
        int product = 1;
        for(int i = 0; i < arg.length ; i++){
            dimensions[i] = arg[i];
            factors[i] = product;
            product *= dimensions[i];
        }

        data = new Object[product];
    }

    protected int getOffset(int[] indices){
        if (indices.length != dimensions.length){
            throw new IllegalArgumentException("wrong number of indices");
        }

        int offset = 0;

        for(int i = 0 ; i < dimensions.length; ++i){
            if(indices[i] < 0 || indices[i] >= dimensions[i]) throw new IndexOutOfBoundsException();
            offset += factors[i] * indices[i];
        }

        return offset;
    }


    public Object get (int[] indices){
        return data[getOffset(indices)];
    }

    public void put(int[] indices, Object object){
        data[getOffset(indices)] = object;
    }
}
