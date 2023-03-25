package com.spring.basics.spring_basics.basicExample;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm {

    // There are no dependencies inside BubbleSortAlgorithm Class.

    public int[] sort(int[] numbers){
        // Bubble Sort Logic
        return numbers;
    }
}
