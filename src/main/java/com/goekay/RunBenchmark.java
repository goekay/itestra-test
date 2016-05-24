package com.goekay;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import static com.goekay.DayOfYearFromItestra.getDayOfYear1;
import static com.goekay.DayOfYearFromItestra.getDayOfYear2;
import static com.goekay.DayOfYearFromItestra.getDayOfYear3;
import static com.goekay.DayOfYearFromRwth.getDayOfYear4;
import static com.goekay.DayOfYearFromRwth.getDayOfYear5;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 24.05.2016
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RunBenchmark {

    private static final int LOOP_COUNT = 10_000;

    /**
     * Main method to run benchmark
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RunBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .threads(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void itestraDayOfYear1(Blackhole bh) {
        for (int i = 0; i < LOOP_COUNT; i++) {
            int m = getDayOfYear1(2015, 4, 6);
            bh.consume(m);
        }
    }

    @Benchmark
    public void itestraDayOfYear2(Blackhole bh) {
        for (int i = 0; i < LOOP_COUNT; i++) {
            int m = getDayOfYear2(2015, 4, 6);
            bh.consume(m);
        }
    }

    @Benchmark
    public void itestraDayOfYear3(Blackhole bh) {
        for (int i = 0; i < LOOP_COUNT; i++) {
            int m = getDayOfYear3(2015, 4, 6);
            bh.consume(m);
        }
    }

    @Benchmark
    public void rwthDayOfYear4(Blackhole bh) {
        for (int i = 0; i < LOOP_COUNT; i++) {
            int m = getDayOfYear4(2015, 4, 6);
            bh.consume(m);
        }
    }

    @Benchmark
    public void rwthDayOfYear5(Blackhole bh) {
        for (int i = 0; i < LOOP_COUNT; i++) {
            int m = getDayOfYear5(2015, 4, 6);
            bh.consume(m);
        }
    }
}
