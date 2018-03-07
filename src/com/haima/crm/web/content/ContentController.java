package com.haima.crm.web.content;

import com.haima.crm.entity.Content;
import com.haima.crm.entity.TzParams;
import com.haima.crm.entity.User;
import com.haima.crm.service.content.IContentService;
import com.haima.crm.service.user.IUserService;
import com.haima.crm.util.poi.ExportExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 内容管理
 *
 * @author: haima
 * @fileName: ContentController.java
 * @date: 2018年2月14日 下午8:31:58
 */

@Controller
@RequestMapping("/sys/content")
public class ContentController {

    @Autowired
    private IContentService contentService;

    @Autowired
    private IUserService userService;

    @RequestMapping("list")
    public String list() {
        return "content/list";
    }

    @RequestMapping("template")
    public ModelAndView template(TzParams params) {
        ModelAndView modelAndView = new ModelAndView();
        List<Content> contents = contentService.find(params);
        Long count = contentService.count(params);

        if (count > 0) {
            for (Content con : contents) {
                Long uid = con.getUserId();
                User u = userService.get(uid);
                modelAndView.addObject("uname", u.getUname());
            }
        }
        modelAndView.addObject("datas", contents);
        modelAndView.addObject("itemCount", count);
        modelAndView.setViewName("content/template");

        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Content content) {
        int code = contentService.update(content);
        if (code == 1)
            return "success";
        else
            return "fail";

    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(TzParams params) {
        int code = contentService.delete(params);
        if (code == 1)
            return "success";
        else
            return "fail";

    }

    @ResponseBody
    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/temp/");
            String fileName = create(request);
            File file = new File(realPath + fileName);
            if (!file.exists()) {
                return;
            }
            //HttpHeaders headers = new HttpHeaders();
            //headers.set("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            //headers.set("Content-Disposition","attachment;filename=" + fileName);
            //headers.set("Pragma","no-cache");
            // headers.set("Cache-Control","no-cache");
            //headers.setExpires(0);

            response.reset();
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");//xlsx
            //response.setContentType("application/vnd.ms-excel;charset=utf-8");//xls
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            //InputStream in = request.getInputStream();
            //BufferedInputStream bis = new BufferedInputStream(in);

            FileInputStream fis = new FileInputStream(realPath + fileName);
            OutputStream out = response.getOutputStream();

            //workbook.write(response.getOutputStream());
            //response.flushBuffer();
            byte[] b = new byte[4096];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                out.write(b, 0, len);
            }

            out.close();
            fis.close();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("content/add");
        return modelAndView;
    }


    private String create(HttpServletRequest request) throws Exception {
        List<Content> contents = contentService.findAll();

        String title = ("内容 - h-crm");
        String[] rowsName = new String[]{"序号", "ID", "内容标题", "删除状态", "置顶状态", "精华状态", "评论状态", "发布状态", "创建人", "创建时间"};
        List<Object[]> dataList = new ArrayList<>();
        Object[] objs;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (contents != null && contents.size() > 0) {
            Long i = 0L;
            for (Content content : contents) {

                objs = new Object[rowsName.length];
                objs[0] = i;
                objs[1] = content.getId();
                objs[2] = content.getTitle();
                objs[3] = content.getIsDelete() == 1 ? "是" : "否";
                objs[4] = content.getIsTop() == 1 ? "是" : "否";
                objs[5] = content.getIsPush() == 1 ? "是" : "否";
                objs[6] = content.getIsComment() == 1 ? "是" : "否";
                objs[7] = content.getIssuance() == 1 ? "是" : "否";
                Long uid = content.getUserId();
                User user = userService.get(uid);
                objs[8] = user.getUname();
                String date = df.format(content.getCreateTime());
                objs[9] = date;
                dataList.add(objs);
                ++i;
            }

            String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xlsx";
            fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");

            HSSFWorkbook workbook = ExportExcelUtil.createWorkbook(title, rowsName, dataList);

            String realPath = request.getSession().getServletContext().getRealPath("/upload/temp/");

            File file = new File(realPath + fileName);
            workbook.write(file);
            workbook.close();

            System.out.println(fileName + " 《《《《《");

            return fileName;
        } else {
            return null;
        }
    }
}
