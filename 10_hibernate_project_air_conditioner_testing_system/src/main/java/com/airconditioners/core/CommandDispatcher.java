package com.airconditioners.core;

import com.airconditioners.core.annotations.Insert;
import com.airconditioners.core.command.Command;
import com.airconditioners.repositories.BasicAirConditionerRepository;
import com.airconditioners.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

    @Autowired
    private BasicAirConditionerRepository basicAirConditionerRepository;

    @Autowired
    private ReportRepository reportRepository;

    public Command dispatchCommand(String name, String[] tokens) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //we get the whole path of the command class
        String commandFullPath = PACKAGE + name + SUFFIX;

        //here we get the class of our command and pass the full path
        Class commandClass = Class.forName(commandFullPath);

        //here we create the constructor of our ComandClass that we have accessed with the commandFullPath
        //instead of getConstructor() we need to use getDeclaredConstructor() because the constructor in Command.class is protected
        Constructor constructor = commandClass.getDeclaredConstructor(String[].class);

        //because the constructor in Command.class is protected we set it to accessible
        constructor.setAccessible(true);
        //now we have the constructor and we can create a Command and pass our tokens
        //new instance of the constructor with the string of tokens, which will be accepted by the Command.class, which is abstract class with
        //protected constructor, all Command classes will extend the abstract Command.class and its constructor will be referenced with super()
        Command command = (Command) constructor.newInstance((Object) tokens);

        //now we inject the command using our inject method, where we pass our commandClass and our command
        this.inject(commandClass, command);
        return command;
    }

    //we need to inject the needed fields to the dispatchCommand() method
    private void inject(Class clazz, Command instance) throws IllegalAccessException {
        //we need to take the fields from tha class using reflection
        //we need to use getDeclaredFields and not getFields, because the fields are private
        Field[] commandFields = clazz.getDeclaredFields();

        //we need to define all things to be passed by reflection, using getDeclaredFields, if the fields are protected
        Field[] dispatcherFields = this.getClass().getDeclaredFields();

        //we need to check for all fields in the command whether they have the @Inject annotation
        for (Field commandField : commandFields) {
            //because the fields are private we need to set them as accessible
            commandField.setAccessible(true);

            //we check for each field whether the field does not have the annotation @Insert and if not, continue to the next field
            if (!commandField.isAnnotationPresent(Insert.class)){
                continue;
            }


            for (Field dispatcherField : dispatcherFields){
                //if the two fields are from different type, do not inject them, here we should get the command injected field
                if (!commandField.getType().equals(dispatcherField.getType())){
                    continue;
                }
                //because the fields are private we need to set them as accessible
                dispatcherField.setAccessible(true);

                //we set the commandField value using the command and the dispatchField
                commandField.set(instance, dispatcherField.get(this));
            }
        }
    }
}
