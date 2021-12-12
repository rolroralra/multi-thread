package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LazyInitializationTest {

    @DisplayName("LazyInitialization is not thread safe.")
    @ParameterizedTest
    @ValueSource(ints = {1000})
    void test_failed_lazy_initialization_not_thread_safe(Integer totalCount) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Collection<Callable<LazyInitialization>> tasks = IntStream.range(0, totalCount)
                .mapToObj(i -> (Callable<LazyInitialization>) LazyInitialization::getInstance)
                .collect(Collectors.toList());

        List<Future<LazyInitialization>> futures = executorService.invokeAll(tasks);

        executorService.shutdown();
        while (!executorService.awaitTermination(3, TimeUnit.SECONDS));

        Set<String> resultSet = new HashSet<>();

        Iterator<Future<LazyInitialization>> it = futures.iterator();
        while (it.hasNext()) {
            Future<LazyInitialization> future = it.next();
            if (future.isDone()) {
                resultSet.add(future.get().toString());
                it.remove();
            }
        }

        System.out.println(resultSet.size());

        assertThat(resultSet).hasSizeGreaterThan(1);
    }
}
