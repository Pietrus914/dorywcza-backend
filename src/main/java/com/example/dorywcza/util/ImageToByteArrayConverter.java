package com.example.dorywcza.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageToByteArrayConverter {
    public static byte[] convertImageToByteArray(File file) throws IOException {
        BufferedImage bImage = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bImage, getFileExtension(file.getName()), baos );
        byte [] data = baos.toByteArray();
        return data;
    }

    private static String getFileExtension(String fileName){
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    public static File convertByteArrayToFile(byte[] bytes, String fileName) throws IOException {
        ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
        BufferedImage bImage =ImageIO.read(baos);
        File file = new File(fileName);
        ImageIO.write(bImage, getFileExtension(fileName), file);
        return file;
    }


}
