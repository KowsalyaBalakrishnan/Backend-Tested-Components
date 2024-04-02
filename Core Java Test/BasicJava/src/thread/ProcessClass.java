package thread;

import java.util.List;
import java.util.Set;

public class ProcessClass implements Runnable{

    private List<Integer> list;

    private Set<Integer> finalList;

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finalList.addAll(list);

    }

    public ProcessClass(List<Integer> list, Set<Integer> finalList) {
        this.list = list;
        this.finalList = finalList;
    }
}
