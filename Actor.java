import java.util.Optional;

public class Actor {

    private int checkEveryMinutes;
    private final Strategy strategy;
    public int maxCheckMinute = 0;
    public int processStartedMinute = 0;

    public Actor(int checkEveryMinutes) {
        this.strategy = Strategy.EVERY_X;
        this.checkEveryMinutes = checkEveryMinutes;
    }

    public Actor(Strategy strategy) {
        this.strategy = strategy;
    }

    public Optional<Process> maybeCheck(Process process, int minute) {
        int currentRuntime = minute - processStartedMinute;
        if (currentRuntime <= maxCheckMinute) {
            return Optional.empty();
        }

        if (currentRuntime >= 90) return Optional.of(process.check());

        maxCheckMinute = currentRuntime;

        switch (strategy) {
            case EVERY_X -> {
                if (currentRuntime % checkEveryMinutes == 0) {
                    return Optional.of(process.check());
                }
            }
            case HALF_LIFE -> {
                if (currentRuntime == 83) return Optional.of(process.check());
                if (currentRuntime == 67) return Optional.of(process.check());
                if (currentRuntime == 45) return Optional.of(process.check());
                if (currentRuntime == 22) return Optional.of(process.check());

            }
        }
        return Optional.empty();

    }


    public enum Strategy {

        EVERY_X, HALF_LIFE

    }

}


