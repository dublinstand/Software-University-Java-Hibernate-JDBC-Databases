package com.airconditioners.core;

import com.airconditioners.core.command.Command;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Component
public class CommandDispatcher {

    //we need to have the path to the class for each command class
    //this is the constant for the package where this Command.class is at we will put there all our commands
    //we use reflection for doing the injections
    //this should get us to com.airconditioners.core.command.
    //this will help us get the whole name of the class from our commands
    private static final String PACKAGE = Command.class.getPackage().getName() + ".";

    //this is the end of all Commands' classes, in between Package and Suffix we'll pass the command name
    private static final String SUFFIX = "Command";

    Command dispatchCommand(String name, String[] tokens) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //we get the whole path of the command class
        String commandFullPath = PACKAGE + name + SUFFIX;

        //here we get the class of our command and pass the full path
        Class commandClass = Class.forName(commandFullPath);

        //here we create the constructor of our ComandClass that we have accessed with the commandFullPath
        Constructor constructor = commandClass.getConstructor(String[].class);

        //because the constructor is protected we set it to accessible - Command.class
        constructor.setAccessible(true);
        //now we have the constructor and we can create a Command and pass our tokens
        //new instance of the constructor with the string of tokens, which will be accepted by the Command.class, which is abstract class with
        //protected constructor, all Command classes will extend the abstract Command.class and its constructor will be referenced with super()
        Command command = (Command) constructor.newInstance((Object) tokens);


        return null;
    }
}
