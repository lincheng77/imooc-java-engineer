package cn.edkso.multithreaded.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownLoader {

    public void download(String source, String targetDir) {
        if (!new File(targetDir).exists()) new File(targetDir).mkdirs();

        InputStream in = null;
        OutputStream out = null;

        try {
            in = new URL(source).openConnection().getInputStream();
            File target = new File(targetDir, source.substring(source.lastIndexOf("/")));
            out = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }
            System.out.println("下载完成!");
            System.out.println("source路径 = " + source);
            System.out.println("target目录 = " + targetDir);
            System.out.println("文件大小 = " + target.length() + "字节");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        DownLoader downLoader = new DownLoader();
        downLoader.download("https://class.imooc.com/static/module/marketpage2020/img/java2023.png",
                "E:/code/java/learn/imooc-java-engineer/week6-7/part05-multithreaded_downloader/download");
    }
}
