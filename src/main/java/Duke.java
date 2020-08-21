import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n" +
                "\n";
        String output;
        String input[] = new String[100];
        int i = 0;
        System.out.println(greetings);
        Scanner in = new Scanner(System.in);
        String currentInput = in.nextLine();

        while (!currentInput.equals("bye")){
            if(currentInput.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                for (int m = 0; m < i; m++) {
                    System.out.println("     " + (m+1) + ". " + input[m]);
                }
                System.out.println("    ____________________________________________________________\n");
            }
            else {
                input[i] = currentInput;
                output = "    ____________________________________________________________\n" +
                        "     added: " + input[i] + "\n" +
                        "    ____________________________________________________________\n";
                System.out.println(output);
                i++;
            }
            currentInput = in.nextLine();
        }

        output = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(output);

    }
}
