package com.sausage.app.fileIO;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class URIConvert {

    public static String getUri(int employeeId, String fileName){
        String filePath = String.format("/api/downloadFile/%d/%s", employeeId, fileName);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(filePath)
                .toUriString();
    }

}
