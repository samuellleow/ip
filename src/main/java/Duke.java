import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n" +
                "\n";
        System.out.println(greetings);

        String output;
        String[] inputs = new String[100];
        Task[] t = new Task[100];
        int totalInputsEntered = 0;
        String taskDoneDescription = "";
        Scanner in = new Scanner(System.in);
        String currentInput = in.nextLine();

        while (!currentInput.equals("bye")) {
            if(currentInput.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                for (int i = 0; i < totalInputsEntered; i++) {
                    System.out.println("     " + (i+1) + ".[" + t[i].getStatusIcon() + "] " + inputs[i]);
                }
                System.out.println("    ____________________________________________________________\n");
            } else if(currentInput.contains("done")) {
                String[] delimitedInputs = currentInput.split(" ");
                int itemIndex = Integer.parseInt(delimitedInputs[1]);
                for(int i = 0; i < totalInputsEntered; i++) {
                    if(itemIndex == i + 1) {
                        taskDoneDescription = t[i].description;
                        t[i].taskDone();
                    }
                }
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        "       [✓] " + taskDoneDescription + "\n" +
                        "    ____________________________________________________________\n");

            } else {
                inputs[totalInputsEntered] = currentInput;
                t[totalInputsEntered] = new Task(currentInput);
                output = "    ____________________________________________________________\n" +
                        "     added: " + inputs[totalInputsEntered] + "\n" +
                        "    ____________________________________________________________\n";
                System.out.println(output);
                totalInputsEntered++;
            }
            currentInput = in.nextLine();
        }

        output = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(output);

    }

}
