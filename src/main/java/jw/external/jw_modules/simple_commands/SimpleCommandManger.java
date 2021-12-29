package jw.external.jw_modules.simple_commands;


import jw.external.jw_modules.events.EventBase;
import jw.fancy_fishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleCommandManger extends EventBase {
    private HashMap<String, SimpleCommand> commands;
    private static SimpleCommandManger instance;

    private static SimpleCommandManger getInstance() {
        if (instance == null) {
            instance = new SimpleCommandManger();
            instance.commands = new HashMap<>();
        }
        return instance;
    }

    @Override
    public void onPluginStart(PluginEnableEvent event) {

    }

    @Override
    public void onPluginStop(PluginDisableEvent event) {
        var instance= getInstance();
        for(var command:instance.commands.values())
        {
            instance.unregisterBukkitCommand(command);
        }
        instance.commands.clear();
    }

    public static boolean register(SimpleCommand command) {
        var instance = getInstance();
        if (instance.commands.containsKey(command.getName())) {
            return false;
        }
        if(! instance.registerBukkitCommand(command))
        {
            return false;
        }
        instance.commands.put(command.getName(), command);
       return true;
    }

    public static boolean unregister(SimpleCommand command) {
        var instance = getInstance();
        if (!instance.unregisterBukkitCommand(command)) {
            return false;
        }
        instance.commands.remove(command.getName());
        return true;
    }


    private boolean registerBukkitCommand(SimpleCommand simpleCommand) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
           return commandMap.register(Main.getPlugin().getName(), simpleCommand);
        } catch (Exception e) {
           // FluentPlugin.logException("Unable to register command " + simpleCommand.getName(), e);
            return false;
        }
    }

    private boolean unregisterBukkitCommand(SimpleCommand command) {
        try {
            Object result = getPrivateField(Bukkit.getPluginManager(), "commandMap");
            SimpleCommandMap commandMap = (SimpleCommandMap) result;
            var field = SimpleCommandMap.class.getDeclaredField("knownCommands");
            field.setAccessible(true);
            var map = field.get(commandMap);
            field.setAccessible(false);
            HashMap<String, Command> knownCommands = (HashMap<String, Command>) map;
            command.unregister(commandMap);
            knownCommands.remove(command.getName(),command);
            for (String alias : command.getAliases()) {
                if (!knownCommands.containsKey(alias))
                    continue;

                if (!knownCommands.get(alias).toString().contains(command.getName())) {
                    continue;
                }
                knownCommands.remove(alias);
            }
            return true;
        } catch (Exception e) {
          //  FluentPlugin.logException("Unable to unregister command " + command.getName(), e);
            return false;
        }
    }

    public static List<String> getAllServerCommandsName() {
        List<String> result = new ArrayList<>();
        try {

            Object commandMap = getPrivateField(Bukkit.getPluginManager(), "commandMap");
            SimpleCommandMap simpleCommandMap = (SimpleCommandMap) commandMap;
            return simpleCommandMap.getCommands().stream().map(c -> c.getName()).toList();
        } catch (Exception e) {
           // FluentPlugin.logException("can't get all commands names", e);
        }
        return result;
    }
    public static List<Command> getAllServerCommands() {
        try {

            Object commandMap = getPrivateField(Bukkit.getPluginManager(), "commandMap");
            SimpleCommandMap simpleCommandMap = (SimpleCommandMap) commandMap;
            return simpleCommandMap.getCommands().stream().toList();
        } catch (Exception e) {
         //   FluentPlugin.logException("can't get all commands", e);
        }
        return new ArrayList<>();
    }

    private static Object getPrivateField(Object object, String field)throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }
}
