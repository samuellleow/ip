import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String greetings = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n" +
                "\n";
        String output;
        System.out.println(greetings);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")){
            output = "    ____________________________________________________________\n" +
                    "     " + input + "\n" +
                    "    ____________________________________________________________\n";
            System.out.println(output);
            input = in.nextLine();
        }

        output = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(output);

    }
}
