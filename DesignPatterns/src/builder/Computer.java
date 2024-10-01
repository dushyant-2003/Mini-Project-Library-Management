package builder;


public class Computer {
    private String cpu;
    private String ram;
    private String storage;

    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public void setRAM(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void displayInfo() {
           System.out.println("Computer Configuration:" +
                           "\nCPU: " + cpu +
                           "\nRAM: " + ram +
                           "\nStorage: " + storage + "\n");
    }
}

