import java.util.Optional;

public class Actor {

    private final int checkEveryMinutes;
    public int maxCheckMinute = 0;
    public int processStartedMinute = 0;

    public Actor(int checkEveryMinutes) {

        this.checkEveryMinutes = checkEveryMinutes;
    }

    public Optional<Process> maybeCheck(Process process, int minute) {
        int currentRuntime = minute - processStartedMinute;
        if (currentRuntime <= maxCheckMinute) {
            return Optional.empty();
        }

        maxCheckMinute = currentRuntime;

        if (currentRuntime % checkEveryMinutes == 0) {
            return Optional.of(process.check());
        }
        return Optional.empty();

    }
}
