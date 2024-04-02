package designpatterns.template;

public interface ProcessAllocation {

    default void proceedToAllocate() {
        String s1 = validateItemTrackers();
        String s2 = insertAllocationMetaData(s1);
        insertAllocationDetails(s2, "Done");
    }

    void insertAllocationDetails(String s2, String done);

    String insertAllocationMetaData(String s1);

    String validateItemTrackers();

}
