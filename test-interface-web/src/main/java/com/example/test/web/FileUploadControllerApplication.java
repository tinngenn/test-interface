package com.example.test.web;

import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
@Api(tags = "Http文件上传测试接口")
public class FileUploadControllerApplication {

    @Autowired
    CotrollerFuction cotrollerFuction;

    @ApiOperation("Http文件上传测试接口")
    @RequestMapping(value= "/fileUpload" , method = {RequestMethod.GET,RequestMethod.POST})
    public String upload(@RequestParam("files") MultipartFile file,
                         Model model, HttpServletRequest httpRequest) {

        cotrollerFuction.printMessage(httpRequest);

        if (file.isEmpty()) {
            model.addAttribute("message", "请选择一个文件上传！");
            //redirectAttributes.addFlashAttribute("message", "Please select a files to upload");
            return "fileUploadStatus";
        }

        try {
            // Get the files and save it somewhere
            byte[] bytes = file.getBytes();
            // UPLOADED_FOLDER 文件本地存储地址
            File fileCheck = new File("./fileUpload/");
            if (!fileCheck.isDirectory()) {
                fileCheck.mkdir();
            }
            Path path = Paths.get("./fileUpload/" + file.getOriginalFilename());
            Files.write(path, bytes);
            model.addAttribute("message", "文件上传成功，文件名：" + file.getOriginalFilename() );


        } catch (IOException e) {
            log.info(e.toString());
            e.printStackTrace();
        }
        return "fileUploadStatus";

    }
}