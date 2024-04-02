package designpatterns.template;

public class ProcessAllocationImpl implements ProcessAllocation {

    @Override
    public void insertAllocationDetails(String s2, String done) {
        System.out.println(s2 + "\n" + "Inserting Details Information" + "\n" + done);
    }

    @Override
    public String insertAllocationMetaData(String s1) {
        return s1 + "\n" + "Inserting MetaData Information";
    }

    @Override
    public String validateItemTrackers() {
        return "Validating Item Trackers Data";
    }
}
