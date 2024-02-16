package com.example;


import java.util.EmptyStackException;
import java.util.Stack;

public class TqsStack<T> {

    private Stack<T> stack;
    private int N=0;
    private int maxSize;

    public TqsStack(int maxSize) {
        this.stack = new Stack<>();
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Maximum size must be greater than 0");
        }
        this.maxSize = maxSize;
        this.N=0;
    }


    public TqsStack() {
        this.stack = new Stack<>();
        this.maxSize = Integer.MAX_VALUE;
        this.N=0;
    }

     public void push(T element) {
        if (maxSize > 0 && N >= maxSize) {
            throw new IllegalStateException("Stack is full");
        }
        stack.add(element);
        N++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        N--;
        return stack.pop();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.peek();
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N==0;
    }
}
