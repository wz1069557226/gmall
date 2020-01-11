package com.atguigu.gmall.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {

        String imgUrl = "http://192.168.190.128";

        ClassPathResource classPathResource = new ClassPathResource("tracker.conf");

        String path = classPathResource.getPath();

        try {
            ClientGlobal.init(path);

            TrackerClient trackerClient = new TrackerClient();

            TrackerServer trackerServer = trackerClient.getTrackerServer();

            StorageClient storageClient = new StorageClient(trackerServer, null);

            //获取上传的文件
            byte[] bytes = multipartFile.getBytes();

            //获取后缀
            String originalFilename = multipartFile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i + 1);

            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);

            for (String uploadInfo : uploadInfos) {
                imgUrl += "/" + uploadInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}