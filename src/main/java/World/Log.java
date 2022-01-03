package World;

import java.util.ArrayList;
import java.util.List;

public class Log {

    private List<String> logList = new ArrayList<>();
    private static int logIndex = 1;


    public void addLog(String log) {
        logList.add(logIndex + "# " + log);
        logIndex++;
    }

    public void printLogs() {
        System.out.println("\n@ LOGS @");
        logList.forEach(System.out::println);
        logList.clear();
        System.out.println();
    }

}
