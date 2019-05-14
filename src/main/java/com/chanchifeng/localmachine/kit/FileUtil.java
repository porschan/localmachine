package com.chanchifeng.localmachine.kit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static ArrayList<Object> scanFiles = new ArrayList<Object>();
    private static int count=0;

    public static void main(String[] args) throws Exception {

        judeDirExists(new File("D:\\127.0.0.1数据库"));
        long currentTimeMillis = System.currentTimeMillis();
        List<String> strings = scanFilesWithRecursion("D:\\127.0.0.1数据库");
        for (int i = 0; i < strings.size(); i++) {
            System.out.println("!!" + strings.get(i));
        }
        System.out.println(strings.size() + "!!");
        System.out.println(scanFiles.size());
        System.out.println(count);
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println(currentTimeMillis2-currentTimeMillis);
    }

    private static List<String> allFile;

    /**
     * TODO:递归扫描指定文件夹下面的指定文件
     * @return ArrayList<Object>
     * @throws FileNotFoundException
     */
    public static List<String> scanFilesWithRecursion(String folderPath) throws FileNotFoundException{
        allFile = new ArrayList<>();
        File directory = new File(folderPath);
        if(!directory.isDirectory()){
            throw new FileNotFoundException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if(directory.isDirectory()){
            File [] filelist = directory.listFiles();
            for(int i = 0; i < filelist.length; i ++){
                /**如果当前是文件夹，进入递归扫描文件夹**/
                if(filelist[i].isDirectory()){
                    /**递归扫描下面的文件夹**/
                    count++;
                    scanFilesWithRecursion(filelist[i].getAbsolutePath());
                }
                /**非文件夹**/
                else{
                    scanFiles.add(filelist[i].getAbsolutePath());
                    allFile.add(filelist[i].getAbsolutePath());
//                    System.out.println(filelist[i].getAbsolutePath());
//					if (filelist[i].getName().contains("-汉译世界学术名著丛")) {
//						filelist[i].renameTo(new File("D:/Message/Book/一生必读的60本/"+filelist[i].getName().replace("-汉译世界学术名著丛", "")));
//					 }
                }
            }
        }
        return allFile;
    }


    // 判断文件是否存在
    public static void judeFileExists(File file) {

        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    // 判断文件夹是否存在
    public static void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }
}
