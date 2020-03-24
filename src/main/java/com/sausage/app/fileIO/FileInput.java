package com.sausage.app.fileIO;

import com.sausage.app.constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileInput {

    public static void setAvatar(String avatarPath, File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BufferedImage newBufferedImage = new BufferedImage(Constant.DEFAULT_AVATAR_WIDTH,
                    Constant.DEFAULT_AVATAR_HEIGHT, BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            ImageIO.write(newBufferedImage, "jpg", new File(avatarPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFile(String filePath, File file) {
        try {
            File localFile = new File(filePath);
            if (!localFile.exists()){
                boolean create = localFile.createNewFile();
            }
            boolean rename = file.renameTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
