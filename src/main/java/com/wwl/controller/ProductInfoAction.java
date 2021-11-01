package com.wwl.controller;

import com.github.pagehelper.PageInfo;
import com.wwl.pojo.ProductInfo;
import com.wwl.pojo.vo.ProductInfoVo;
import com.wwl.service.ProductInfoService;
import com.wwl.utils.FileNameUtil;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //分页每页的记录数
    public static final int PAGE_SIZE=5;
    //转存的文件名
    String saveFileName="";

    @Autowired
    ProductInfoService productInfoService;

    //查询商品不分页
    /*@RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }*/

    //查询商品分页(全局刷新）
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo<ProductInfo> info;
        //清空saveFileName
        saveFileName = "";
        //判断是否有条件
        Object vo = request.getSession().getAttribute("proVo");
        if(vo!=null){
            info = productInfoService.selectCondition((ProductInfoVo)vo,PAGE_SIZE);
            request.getSession().removeAttribute("proVo");
        }else{
            //得到第一页的数据
            info = productInfoService.splitPage(1,PAGE_SIZE);
        }
        request.setAttribute("info",info);
        return "product";
    }

    //查询商品分页
    /*@ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(int page, HttpSession session){
        //清空saveFileName
        saveFileName = "";
        //获取数据
        PageInfo<ProductInfo> info = productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }*/

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public String ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //清空saveFileName
        saveFileName = "";
        //获取生成的UUID,和上传图片的后缀,拼接起来就是最后存储文件的名称
        saveFileName = FileNameUtil.getUUIDFileName()
                + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveFileName;
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        //补齐空缺的两个属性值
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int num = -1;
        try {
            num = productInfoService.save(info);
            if(num>0){
                request.setAttribute("msg","添加成功");
            }else{
                request.setAttribute("msg","添加失败");
            }
            //清空saveFileName
            saveFileName = "";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","添加失败");
        }
        return "forward:/prod/split.action";
    }

    @RequestMapping("/delete")
    public String delete(int pId,ProductInfoVo vo,HttpSession session,HttpServletRequest request){
        int num = -1;
        try {
            num = productInfoService.delete(pId);
            if(num>0){
                request.setAttribute("msg","删除成功");
            }else{
                request.setAttribute("msg","删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","删除失败");
        }
        session.setAttribute("proVo",vo);
        return "forward:/prod/split.action";
    }

    //对更新商品信息的回响
    @RequestMapping("/one")
    public String select(int pId,ProductInfoVo vo,HttpServletRequest request,HttpSession session){
        //清空saveFileName
        saveFileName = "";
        //将page放入会话作用域中
        ProductInfo info = productInfoService.selectById(pId);
        request.setAttribute("prod",info);
        //将条件放入作用域中
        session.setAttribute("proVo",vo);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        int num=-1;
        //判断有没有更新图片
        if(!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        try {
            num = productInfoService.update(info);
            if(num>0){
                request.setAttribute("msg","更新成功");
            }else{
                request.setAttribute("msg","更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","更新失败");
        }
            //清空saveFileName
            saveFileName = "";
        return "forward:/prod/split.action";
    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(String str1,ProductInfoVo vo,HttpServletRequest request,HttpSession session){
        int num = -1;
        //把字符串转化为数组
        String[] ids = str1.split(",");
        try {
            num = productInfoService.deleteBatch(ids);
            if(num>0){
                request.setAttribute("msg","批量删除成功");
            }else{
                request.setAttribute("msg","批量删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","批量删除失败");
        }
        session.setAttribute("proVo",vo);
        return "forward:/prod/split.action";
    }

    //ajax分页
    @RequestMapping("/condition")
    @ResponseBody
    public void selectCondition(ProductInfoVo vo,HttpSession session){
        //清空saveFileName
        saveFileName = "";
        PageInfo<ProductInfo> info = productInfoService.selectCondition(vo,PAGE_SIZE);
        session.setAttribute("info",info);
    }
}
