package conf;

import java.io.*;
import java.util.*;

public class ReadConfig {
    private static final String FILENAME = "sort.config";
    private static Properties properties;
    private static Config config;

    public ReadConfig() throws IOException {
        this.properties = new Properties();
        this.config = new Config();
    }

    public static Config readConfig() throws IOException {
        // Checking config file exists
        File confFile = new File(FILENAME);
        if (!confFile.exists())
            writeConfig();

        // Load configs
        FileInputStream fis = new FileInputStream(FILENAME);
        properties.load(fis);

        // Specified array input
        String[] strArray = properties.getProperty("array.define").split(",");
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++)
            intArray[i] = Integer.parseInt(strArray[i]);

        // Reading properties into the config
        config.sortAll = properties.getProperty("alg.sortall").toLowerCase().equals("true");
        config.algorithm = Algorithms.valueOf(properties.getProperty("alg.name"));
        config.definedArray = properties.getProperty("array.defined").toLowerCase().equals("true");
        config.array = intArray;

        return config;

    }

    private static void writeConfig() throws IOException {
        // If sort.config not exists, write the new file with predefined configs
        String[] algsArray = Arrays.stream(Algorithms.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        StringBuilder algs = new StringBuilder();
        List<String> configData = new ArrayList<>();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FILENAME));

        for (String s : algsArray)
            algs.append(s).append(" ");

        configData.add("# Sort with all algorithms");
        configData.add("alg.sortall=false");
        configData.add("# If not, specify the algorithm : " + algs);
        configData.add("alg.name=" + algsArray[0]);
        configData.add("# Define array");
        configData.add("array.defined=false");
        configData.add("# If not, define the array");
        configData.add("array.define=10,4,5,8,1,8,3,6");

        algs = new StringBuilder();
        for (String s : configData)
            algs.append(s).append("\n");

        bos.write(algs.toString().getBytes());
        bos.close();
    }
}
