package com.airconditioners.core;

import com.airconditioners.io.Reader;
import com.airconditioners.io.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//this is our logic runner
@Component
public class Engine implements CommandLineRunner {

    @Autowired
    private Reader reader;

    @Autowired
    private Writer writer;

    @Autowired
    private CommandDispatcher commandDispatcher;


    @Override
    public void run(String... strings) throws Exception {
        while(true){
            //we read the line and split by empty spaces
            //this is the command -	RegisterStationaryAirConditioner (<manufacturer>,<model>,<energyEfficiencyRating>,<powerUsage>)
            String[] items = reader.read().split("\\s+");
            String commandName = items[0];

            //this is the second part of the item we read and we remove the brackets () -(<manufacturer>,<model>,<energyEfficiencyRating>,<powerUsage>)
            //in the end we split them by comma and have the commands
            String[] tokens = items[1].replace("(","").replace(")","").split(",");

            //if there is an exception we catch it
            try {
                //commandDispatcher.dispatchCommand will return Command and we can get access to Command.execute() that returns string
                String output = this.commandDispatcher.dispatchCommand(commandName, tokens).execute();
                //we print the message
                writer.write(output);
            }
            catch (RuntimeException ex){
                writer.write(ex.getMessage());
            }
        }
    }
}
