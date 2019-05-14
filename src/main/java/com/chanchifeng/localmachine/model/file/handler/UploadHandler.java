package com.chanchifeng.localmachine.model.file.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chanchifeng.localmachine.common.staticDB.UPLOAD_FILE_URL;
import static com.chanchifeng.localmachine.kit.FileUtil.judeDirExists;
import static com.chanchifeng.localmachine.kit.FileUtil.scanFilesWithRecursion;

@RequestMapping("/file")
@RestController
public class UploadHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHandler.class);

    @PostMapping("/upload")
    @ResponseBody
    public ModelAndView upload(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("redirect:/page/filePage");

        if (file.isEmpty()) {
            return modelAndView.addObject("message","上传失败，请选择文件");
//            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
//        String filePath = "/Users/itinypocket/workspace/temp/";
        String filePath = UPLOAD_FILE_URL + "\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
//            return "上传成功";
            return modelAndView.addObject("message","上传成功");
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
//        return "上传失败！";
        return modelAndView.addObject("message","上传失败");
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public Map<String,Object> files(){
        Map<String,Object> map = new HashMap<>();

        judeDirExists(new File(UPLOAD_FILE_URL));
        List<String> strings = null;
        try {
            strings = scanFilesWithRecursion(UPLOAD_FILE_URL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        map.put("files",strings);

        return map;
    }
}
