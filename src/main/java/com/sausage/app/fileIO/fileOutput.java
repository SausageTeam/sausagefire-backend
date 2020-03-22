package com.sausage.app.fileIO;

import com.sausage.app.constant.Constant;

import java.io.File;

public class fileOutput {

    public static File getAvatar(String avatarPath) {
        File avatar = new File(avatarPath);
        if (!avatar.exists()) {
            avatar = new File(Constant.DEFAULT_AVATAR_PATH);
        }
        return avatar;
    }

    public static File getFile(String filePath) {
        File driverLicenseDoc = new File(filePath);
        if (driverLicenseDoc.exists()) {
            return driverLicenseDoc;
        } else {
            return null;
        }
    }

}
