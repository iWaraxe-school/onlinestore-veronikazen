package helpers.operations;

public class QuitOperation extends Operation {

    @Override
    public boolean checkOperation(String command){
        if (command.equalsIgnoreCase("quit")){
            printOutput();
        }
        return false;
    }

    @Override
    public String operationOutput() {
        return "Successful quit";
    }

    @Override
    public void printOutput() {
        System.out.println(operationOutput());
    }
}
