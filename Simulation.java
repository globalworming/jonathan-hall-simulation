import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {

    public static void main(String[] args) {
        for (int checkEveryMinutes = 1; checkEveryMinutes < 90; checkEveryMinutes++) {
            var random = new Random();
            List<Integer> doneMinutes = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                int minute = 0;
                int interruptions = 0;
                var process = new Process(random);
                Actor actor = new Actor(checkEveryMinutes);
                while (minute < 1000) {
                    minute++;
                    process.progress();
                    var result = actor.maybeCheck(process, minute);
                    if (result.isPresent()) {
                        if (result.get().done) {
                            doneMinutes.add(minute);
                            //System.out.printf("%s, %s, %s, %s%n", result.get().runtimeMinutes, "every five minutes", interruptions, minute);
                            break;
                        }
                        if (result.get().interrupted) {
                            interruptions++;
                            process = new Process(random, result.get().runtimeMinutes);
                            actor.processStartedMinute = minute;
                        }
                    }
                }
            }
            System.out.println("check every " + checkEveryMinutes + " minutes, mean over 100000 processes " + doneMinutes.stream().mapToInt(it -> it).average().orElse(0));
        }
    }

}
