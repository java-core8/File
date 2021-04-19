package ru.tcreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Main {
    public static StringBuilder logPathInstalling = new StringBuilder(); // строит лог рапаковки

    public static void main(String[] args) throws IOException {

        String path = "/home/azathoth/project/java/"; // путь до корня распаковки
        String gamesPath = path + "Games/"; // корень
        File file = new File(gamesPath);
        boolean isCreated = file.mkdir();
        if (isCreated) {
            String[] subDirArray = {
                    "src", "res", "savegames", "temp"
            };
            if(createAllSubDirect(subDirArray, gamesPath)) {
                subDirArray = new String[]{
                        "main", "test"
                };
                String srcPath = gamesPath + "src/";
                if(createAllSubDirect(subDirArray, srcPath)) {
                    File mainFile = new File(srcPath, "main.java");
                    File testFile = new File(srcPath, "test.java");
                    try {
                        boolean iscreateMainFile = mainFile.createNewFile();
                        boolean iscreateTestFile = testFile.createNewFile();
                        if(iscreateMainFile && iscreateTestFile) {
                            logPathInstalling.append("Файлы ")
                                    .append(mainFile.getName())
                                    .append(" и ")
                                    .append(testFile.getName())
                                    .append(" созданы ")
                                    .append("\n");
                        }
                    } catch (IOException e) {
                        logPathInstalling.append(e.getMessage()).append("\n");
                    }

                }
                String resPath = gamesPath + "res/";
                subDirArray = new String[]{
                        "drawables", "vectors", "icons"
                };
                createAllSubDirect(subDirArray, resPath);
                File tempTextFile = new File(gamesPath + "temp/", "temp.txt");
                try {
                    tempTextFile.createNewFile();
                    boolean rewriteFile = false;
                    try(FileWriter writer = new FileWriter(tempTextFile, rewriteFile)) {
                        logPathInstalling.append("Распаковка завершена");
                        writer.write(logPathInstalling.toString());
                    } catch (IOException e) {
                        logPathInstalling.append("запись в файл не удалась: ")
                                .append(e.getMessage());
                        System.out.println(logPathInstalling.toString());
                    }

                } catch (IOException e) {
                    logPathInstalling.append("Файл не создан: ").append(e.getMessage());
                }


            }
        } else {
            logPathInstalling.append("Папка ").append(file.getName()).append(" не была сознада \n");
        }



    }

    public static Boolean createAllSubDirect(String[] subDirArray, String path) {
        for (String subDirectoryName : subDirArray) {
            File newSubDir = new File(path, subDirectoryName);
            if(newSubDir.mkdir()) {
                logPathInstalling.append("Папка ")
                        .append(newSubDir.getName())
                        .append(" создана по адресу ")
                        .append(path)
                        .append("\n");
            } else {
                logPathInstalling.append("Папка ")
                        .append(newSubDir.getName())
                        .append(" не создана ");
                return false;
            }
        }
        return true;
    }


}
