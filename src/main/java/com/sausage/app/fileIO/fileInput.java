package com.sausage.app.fileIO;

import com.sausage.app.constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class fileInput {

    public static void updateAvatar(int employeeId, File file) {
        String avatarPath = String.format(Constant.EMPLOYEE_AVATAR_PATH, employeeId);
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

}
