package com.codefans.security.win32virus;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/4/21.
 */
public class RiskWin32ScriptVirusScanner {

    private List<String> filePaths = new ArrayList<String>();
    private int scanFileCount;
    private int virusFileCount;

    public static void main(String[] args) {
        RiskWin32ScriptVirusScanner virusScanner = new RiskWin32ScriptVirusScanner();
        virusScanner.scan();
    }

    public void scan() {

        String rootDir = "H:\\letvProjects\\";

        scan(rootDir);

        print(filePaths);

        System.out.println("共扫描:" + scanFileCount + "个文件.");
        System.out.println("病毒文件共:" + virusFileCount + "个");

        String destPath = "F:\\riskFiles\\";
        copyTo(destPath);

    }

    public void scan(String... rootDir) {
        scan(Arrays.asList(rootDir));
    }

    public void scan(List<String> rootDir) {
        for(String root: rootDir) {
            scan(root);
        }
    }

    public void scan(String rootDir) {
        scan(new File(rootDir));
    }

    public void scan(File rootFile ) {
        if(rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            for(File file : files) {
                if(file.isDirectory()) {
                    scan(new File(rootFile.getAbsolutePath() + File.separator + file.getName()));
                } else {
                    scanVirus(file);
                }
            }
        }
    }

    public void scanVirus(File file) {
        try {
            scanFileCount++;
//            if(file.getName().equals("paste-type.html")) {
//                System.out.println(file.getAbsoluteFile());
//            }
            Scanner sc = new Scanner(file);
            String line = "";
            boolean existsVBScript = false;
            boolean existsSvchostExt = false;
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                if(line.contains("VBScript")) {
                    existsVBScript = true;
                }
                if(line.contains("svchost.exe")) {
                    existsSvchostExt = true;
                }
                if(existsVBScript && existsSvchostExt) {
                    filePaths.add(file.getAbsolutePath());
                    virusFileCount++;
                    break;
                }

            }
            sc.close();
            sc = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void copyTo(String destPath) {

        for(int i = 0; i < filePaths.size(); i ++) {
            String filePath = filePaths.get(i);
            String destFileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            File sourceFile = new File(filePath);
            File destFile = new File(destPath + File.separator + destFileName + ".txt");
            if(destFile.exists()) {
                destFile = new File(destPath + File.separator + destFileName + "_" + (i+1) + ".txt");
            }
            copy(sourceFile, destFile);
        }

    }

    public void copy(File source, File dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(dest);
            byte[] bytes = new byte[1024];
            int n = 0;

            while((n = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, n);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                    fis = null;
                }
                if(fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void print(List<String> strList) {
        for(String str : strList) {
            System.out.println(str);
        }

    }
}
