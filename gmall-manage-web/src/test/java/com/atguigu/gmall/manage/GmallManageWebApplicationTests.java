package com.atguigu.gmall.manage;


import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootTest
public class GmallManageWebApplicationTests {


    @Test
    public void contextLoads() throws IOException, MyException {

        ClassPathResource classPathResource = new ClassPathResource("tracker.conf");

        String path = classPathResource.getPath();

        ClientGlobal.init(path);

        TrackerClient trackerClient = new TrackerClient();

        TrackerServer trackerServer = trackerClient.getTrackerServer();

        StorageClient storageClient = new StorageClient(trackerServer, null);

        String[] uploadInfos = storageClient.upload_file("d:/test.jpg", "jpg", null);

        String url = "http://192.168.190.128";

        for (String uploadInfo : uploadInfos) {
            url += "/" + uploadInfo;
        }

        System.out.println(url);
    }

}
