package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class MessageHandler {
    private final Path PATH_ARRAY_TASK =
            Paths.get("/Users/user/IdeaProjects/ChatBot/src/main/java/org/example/filesTask/Array.txt");

    private final Path PATH_STRING_TASK =
            Paths.get("/Users/user/IdeaProjects/ChatBot/src/main/java/org/example/filesTask/Strings.txt");


    public String returnMessage (String message) {

      switch (message.toLowerCase()){
          case "/start" -> {
              return "Для получения задач напишите тему задачи.\nНапример:\nМассивы,\nСтроки ";
          }
          case "массивы" -> {
              return readRandomTaskInFile(PATH_ARRAY_TASK);
          }
          case "строки" -> {
              return readRandomTaskInFile(PATH_STRING_TASK);
          }
          default -> {
              return "Не верная команда";
          }
      }
    }


    private String readRandomTaskInFile(Path path){
        try {
           String tasks =  Files.readString(path);
          String [] tasksArray =  tasks.split(";");
          return tasksArray[new Random().nextInt(tasksArray.length)];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
