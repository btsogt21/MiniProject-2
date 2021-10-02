import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

/**Test-case list generator for implementation of ListInterface against a known, correct implementation.*/

public class CreateList {

    private int              totalOps;
    private Random           random;
    private PrintStream      output;
    private int              line;
    private ListInterface<Integer> list;

    public static void main (String[] args) {
        if (args.length != 3) {
            showUsageAndExit();
        }
        // Extract the arguments.
        int    numOps         = 0;
        long   seed           = 0;
        String outputPathname = args[2];
        try {
            numOps = Integer.parseUnsignedInt(args[0]);
            seed   = Long.parseUnsignedLong(args[1]);
        } catch (NumberFormatException e) {
            showUsageAndExit();
        }
        // Create the list-creator and start it.
        CreateList creator = new CreateList(numOps, seed, outputPathname);
        creator.go();
    }

    public CreateList (int numOps, long seed, String outputPathname) {

        totalOps = numOps;
        random   = new Random(seed);
        line     = 0;
        list     = new ListWrapper<Integer>();

        File outputFile = new File(outputPathname);
        try {
            output = new PrintStream(outputFile);
        } catch (FileNotFoundException e) {
            System.err.printf("ERROR: Could not open file %s\n", outputPathname);
            showUsageAndExit();
        }
    }

    private void go () {
        // Write up to the request number of instructions.
        for (line = 1; line <= totalOps; line = line + 1) {
            switch (random.nextInt(5)) {

                case 0:
                    add();
                    break;

                case 1:
                    remove();
                    break;

                case 2:
                    get();
                    break;

                case 3:
                    set();
                    break;

                case 4:
                    size();
                    break;

                default:
                    System.err.printf("ERROR: Invalid operation number ?!\n");
                    System.exit(1);
            }
        }
    }

    private static void showUsageAndExit () {
        System.err.printf("USAGE: java CreateList <# ops>\n"+"<random seed>\n"+"<output pathname>\n" );
        System.exit(1);
    }

    private void add () {
        // Choose random index and value
        int index = getRandomIndex();
        int value = getRandomValue();
        // try and apply them to a real list, to keep its size updated.
        try {
            list.add(index, value);
        }
        catch (IndexOutOfBoundsException | IllegalStateException e) {
        }
        // print operations
        output.printf("%6s %9d %9d\n", "add", index, value);
    }

    private void remove () {

        // Choose random index
        int index = getRandomIndex();

        // try and apply it to a real list, to keep its size updated.
        try {
            list.remove(index);
        }
        catch (IndexOutOfBoundsException e) {
        }
        // print operations
        output.printf("%6s %9d\n", "remove", index);
    }

    private void get () {

        // Choose random index.
        int index = getRandomIndex();

        // print operations
        output.printf("%6s %9d\n", "get", index);

    }

    private void set () {

        // Choose random index and value.
        int index = getRandomIndex();
        int value = getRandomValue();

        // print operations
        output.printf("%6s %9d %9d\n", "set", index, value);

    }

    private void size () {
        output.printf("%6s\n", "size");
    }

    private int getRandomIndex () {

        // Pick a random index between 1/4 of the size below 0 and 1/4 of the
        // size above the length. Good chance of invalid indices being tested
        int size       = list.size();
        int lowerIndex = -(size / 4);
        int upperIndex = size + (size / 4);
        int indexRange = upperIndex - lowerIndex;
        int index      = random.nextInt(indexRange + 1) + lowerIndex;
        return index;

    }

    private int getRandomValue () {

        // The choice of random value isn't important here as we just want values that are reasonably distinct.
        return random.nextInt(totalOps);

    }
}
