package designpatterns.template;

public class MainTemplateDesignPattern {

    public static void main(String[] args) {

        ProcessAllocation allocation = new ProcessAllocationImpl();
        allocation.proceedToAllocate();

    }
}
