package com.sunup.noend.util;

import lombok.Cleanup;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author lime
 * 注：压缩后的文件不能放在压缩目录下 否则会造成无限循环压缩错误
 */
public class FileCompressionTools {
    static final int BUFFER = 8192;

    /**
     * 使用递归算法将一个文件夹压缩成Zip文件
     *
     * @param srcPath
     * @param dstPath
     * @throws IOException
     */
    public static void compress(String srcPath, String dstPath) throws IOException {
        File srcFile = new File(srcPath);
        File dstFile = new File(dstPath);
        if (!srcFile.exists()) {
            throw new FileNotFoundException(srcPath + "不存在！");
        }

        @Cleanup FileOutputStream out = new FileOutputStream(dstFile);
        @Cleanup CheckedOutputStream cos = new CheckedOutputStream(out, new CRC32());
        @Cleanup ZipOutputStream zipOut = new ZipOutputStream(cos);
        String baseDir = "";
        compress(srcFile, zipOut, baseDir);
    }

    private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    /**
     * 压缩一个目录
     */
    private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException {
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            compress(files[i], zipOut, baseDir + dir.getName() + "/");
        }
    }

    /**
     * 压缩一个文件
     */
    private static void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (!file.exists()) {
            return;
        }

        @Cleanup BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        ZipEntry entry = new ZipEntry(baseDir + file.getName());
        zipOut.putNextEntry(entry);
        int count;
        byte[] data = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            zipOut.write(data, 0, count);
        }


    }

    /**
     * 将Zip文件加压缩出来，包含所有文件和文件夹到目标目录
     *
     * @param zipFile
     * @param dstPath
     * @throws IOException
     */
    public static void decompress(String zipFile, String dstPath) throws IOException {
        File pathFile = new File(dstPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        @Cleanup ZipFile zip = new ZipFile(zipFile);
        @Cleanup InputStream in = null;
        @Cleanup OutputStream out = null;
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            in = zip.getInputStream(entry);
            String outPath = (dstPath + "/" + zipEntryName).replaceAll("\\*", "/");

            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }

            out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }

        }
    }

//    public static void main(String[] args) throws Exception{
//        String targetFolderPath = "D:\\db";
//        String rawZipFilePath = "/Users/fred/zipFile/raw.zip";
//        String newZipFilePath = "D:\\test.zip";
//
//        //将Zip文件解压缩到目标目录
//        FileCompressionTools.decompress(rawZipFilePath , targetFolderPath);
//
//        //将目标目录的文件压缩成Zip文件
//        FileCompressionTools.compress(targetFolderPath , newZipFilePath);
//    }
}
