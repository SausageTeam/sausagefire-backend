package com.sausage.app.fileIO;

import com.sausage.app.constant.Constant;

import java.io.File;

public class fileOut {

    public static File getAvatar(int employeeId){
        String avatar_path = String.format(Constant.EMPLOYEE_AVATAR_PATH, employeeId);
        File avatar = new File(avatar_path);
        if (!avatar.exists()) {
            avatar = new File(Constant.DEFAULT_AVATAR_PATH);
        }
        return avatar;
    }

}
