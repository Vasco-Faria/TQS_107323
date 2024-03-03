package com.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class TqsStackTest 
{

    private TqsStack<String> wordStack;


    @BeforeEach
    void setUp(){
        wordStack = new TqsStack<String>();
    }

    @Test
    @DisplayName("TEST - Stack is empty on construction")
    void EmptyOnConstruction(){
        Assertions.assertTrue(wordStack.isEmpty());
    }

    @Test
    @DisplayName("TEST - Stack has size 0 on construction")
    public void testSizeZeroOnConstruction() {
        assertEquals(0, wordStack.size());
    }

    @Test
    @DisplayName("TEST - After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    public void testPushNotEmptyAndSizeN(){
        int n=3;
        for (int i = 0; i < n; i++) {
            wordStack.push("abc");
        }

        assertFalse(wordStack.isEmpty());
        assertEquals(n, wordStack.size());

    }

    @Test
    @DisplayName("TEST - If one pushes x then pops, the value popped is x.")
    public void testPushXThenPopX(){
        wordStack.push("xfd");
        assertEquals("xfd", wordStack.pop());
    }

    @Test
    @DisplayName("TEST -If one pushes x then peeks, the value returned is x, but the size stays the same")
    public void testPushXThenPeekX(){
        wordStack.push("xfd");
        assertEquals("xfd", wordStack.peek());
        assertEquals(1, wordStack.size());
    }

    @Test
    @DisplayName("TEST - If the size is n, then after n pops, the stack is empty and has a size 0 ")
    public void testSizeNAfterNPops(){
        int n=3;
        for (int i = 0; i < n; i++) {
            wordStack.push("abc");
        }
        for (int i = 0; i < n; i++) {
            wordStack.pop();
        }
        assertEquals(0, wordStack.size());
        Assertions.assertTrue(wordStack.isEmpty());
    }
    
    @Test
    @DisplayName("TEST - Popping from an empty stack does throw a NoSuchElementException")
    public void testPopEmptyStack(){
        Assertions.assertThrows(java.util.EmptyStackException.class, () -> {
            wordStack.pop();
        });
    }

    @Test
    @DisplayName("TEST - Peeking into an empty stack does throw a NoSuchElementException")
    public void testPeekEmptyStack(){
        Assertions.assertThrows(java.util.EmptyStackException.class, () -> {
            wordStack.peek();
        });
    }

    @Test
    public void testPushFullStackThrowsException() {
        TqsStack<String> stack = new TqsStack<String>(2);  
        stack.push("a");
        stack.push("a");
        assertThrows(IllegalStateException.class, () -> stack.push("abc"));
    }


}
