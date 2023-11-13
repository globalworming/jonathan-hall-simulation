import java.util.Random;

public class Process {
    private final Random random;
    public final int runtimeMinutes;
    public boolean interrupted = false;
    public boolean done = false;
    public int currentMinute = 0;

    public Process(Random random, int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
        this.random = random;
    }

    public Process(Random random) {
        int max = 90 + 1; // +1 because exclusive bound
        int min = 5;
        this.runtimeMinutes = random.nextInt(max - min) + min;
        this.random = random;
    }

    public void progress() {
        if (currentMinute >= runtimeMinutes) {
            return;
        }
        currentMinute++;
    }


    public Process check() {
        if (random.nextDouble() >= 0.7) {
            interrupted = true;
        }
        if (currentMinute >= runtimeMinutes) {
            done = true;
        }
        return this;
    }
}
