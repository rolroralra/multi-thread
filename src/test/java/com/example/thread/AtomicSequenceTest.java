package com.example.thread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AtomicSequenceTest {
    @DisplayName("@ThreadSafe AtomicInteger Test")
    @ParameterizedTest
    @ValueSource(ints = {1000})
    void test_success_thread_safe_sequence(Integer totalCount) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        AtomicSequence sequence = new AtomicSequence();
        Runnable runnable = sequence::getNext;

        int count = 0;
        while(count < totalCount) {
            executorService.submit(runnable);
            count++;
        }

        executorService.shutdown();
        while (!executorService.awaitTermination(3, TimeUnit.SECONDS));

        int actual = sequence.getNext();
        System.out.println(actual);
        assertThat(actual).isEqualTo(totalCount);
    }
}