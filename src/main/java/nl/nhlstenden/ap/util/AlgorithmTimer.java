package nl.nhlstenden.ap.util;

/**
 * Utility class for timing algorithm execution.
 * Displays speed rounded to the nearest tenth of a second as required,
 * and also shows milliseconds for more detail on small datasets.
 */
public class AlgorithmTimer {

    private long startTime;
    private long endTime;

    public void start() {
        this.startTime = System.nanoTime();
    }

    public void stop() {
        this.endTime = System.nanoTime();
    }

    /**
     * Returns elapsed time formatted as both seconds and milliseconds.
     * Example: "0.3s (312.5ms)" or "0.0s (0.8ms)"
     *
     * @return formatted elapsed time string
     */
    public String getElapsedFormatted() {
        double seconds = (endTime - startTime) / 1_000_000_000.0;
        double millis = (endTime - startTime) / 1_000_000.0;
        return String.format("%.1fs (%.1fms)", seconds, millis);
    }

    /**
     * Returns the raw elapsed time in nanoseconds.
     *
     * @return elapsed nanoseconds
     */
    public long getElapsedNanos() {
        return endTime - startTime;
    }

    /**
     * Returns elapsed time in seconds as a double.
     *
     * @return elapsed seconds
     */
    public double getElapsedSeconds() {
        return (endTime - startTime) / 1_000_000_000.0;
    }
}