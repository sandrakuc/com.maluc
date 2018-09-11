package com.maluc.command;

public interface CommandHandler <T extends Command, V> {
    V handle(T command) throws Exception;
}
