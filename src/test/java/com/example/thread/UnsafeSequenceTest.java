package com.example.thread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UnsafeSequenceTest {

    @DisplayName("@NotThreadSafe Test")
    @ParameterizedTest
    @ValueSource(ints = {1000})
    void test_fail_not_thread_safe_sequence(Integer totalCount) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        UnsafeSequence unsafeSequence = new UnsafeSequence();
        Runnable runnable = unsafeSequence::getNext;

        int count = 0;
        while(count < totalCount) {
            executorService.submit(runnable);
            count++;
        }

        executorService.shutdown();
        while (!executorService.awaitTermination(3, TimeUnit.SECONDS));

        int actual = unsafeSequence.getNext();
        System.out.println(actual);
        assertThat(actual).isNotEqualTo(totalCount);
    }
}