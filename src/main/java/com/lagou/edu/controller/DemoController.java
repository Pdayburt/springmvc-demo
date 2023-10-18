package com.lagou.edu.controller;

import com.lagou.edu.pojo.QueryVo;
import com.lagou.edu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/demo")
public class DemoController {


    /**
     * SpringMVC在handle方法上传入Map,Model和ModelMap参数，并向这些参数中保存数据（放入请求域中），
     * 都可以在页面获取到
     *
     * 它们之间是什么关系？
     * 运行时的机体类型都是BindingAwareModelMap，相当于给BindingAwareModel中保存的数据都会放在请求域中
     *
     * Map jdk的接口
     * Model  spring的接口
     * ModelMap extends LinkedHashMap
     *
     * BindingAwareModelMap  extends ModelMap implements Model
     */



    /**
     * springMVC的异常处理机制（异常处理器）
     * 只能捕获这个类中的异常
     * @return
     */
//    @ExceptionHandler(ArithmeticException.class)
//    public void handleException(ArithmeticException arithmeticException, HttpServletResponse response) {
//        try {
//            response.getWriter().write(arithmeticException.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * http://localhost:8080/demo/handle01
     */
    @RequestMapping("/handle01")
    public ModelAndView handle01(){

        int c = 1/0;
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        /**
         * modelAndView ：封装了数据和页面信息
         * modelAndView.addObject其实就是向请求域中request.setAttribute("date",date)
         */
        modelAndView.addObject("date",date);
        /**
         * 设置视图信息 封装跳转的页面
         */
        modelAndView.setViewName("success");
        return modelAndView;
    }
    /**
     * http://localhost:8080/demo/handle11
     */
    @RequestMapping("/handle11")
    public String handle11(ModelMap modelMap){
        Date date = new Date();
        modelMap.addAttribute("date",date);
        return "success";
    }


    /**
     * http://localhost:8080/demo/handle12
     */
    @RequestMapping("/handle12")
    public String handle11(Model model){
        Date date = new Date();
        model.addAttribute("date",date);
        return "success";
    }

    @RequestMapping("/handle13")
    public String handle13(Map<String ,Object> map){
        Date date = new Date();
        map.put("date",date);
        return "success";
    }



    @RequestMapping("/handle03")
    public ModelAndView handle03(@RequestParam("ids") Integer id){

        System.out.println("id====>"+id);
        Date date = new Date();

        return new ModelAndView();
    }

    @RequestMapping("/handle04")
    public ModelAndView handle04(User user){

        System.out.println("user====>"+user);
        Date date = new Date();

        return new ModelAndView();
    }

    @RequestMapping("/handle05")
    public ModelAndView handle05(QueryVo queryVo){

        System.out.println("queryVo====>"+queryVo);
        Date date = new Date();

        return new ModelAndView();
    }


    /**
     * 接受日期格式的需要类型转化器 默认的是String
     * @param birthday
     * @return
     */

    @RequestMapping("/handle06")
    public ModelAndView handle06(Date birthday){

        System.out.println("birthday====>"+birthday);
        Date date = new Date();

        return new ModelAndView();
    }
    @RequestMapping(value = "/handle/{id}",method = RequestMethod.GET)
    public ModelAndView handleGet(@PathVariable("id") Integer id){
        System.out.println("id----->"+id);
        Date date = new Date();
        return new ModelAndView();
    }


    @RequestMapping(value = "/handle07",method = RequestMethod.POST)
    /**
     * 添加了 @ResponseBody ，不再走视图解析器而是等同于response直接输出数据
     */
    @ResponseBody
    public User handle07(@RequestBody User user){
        System.out.println("user----->"+user);
        user.setName("张三丰");
        return user;
    }


    @PostMapping("upload")
    public String upload (MultipartFile uploadFile, HttpSession session) throws IOException {

        String originalFilename = uploadFile.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        String newName = UUID.randomUUID().toString()+"."+ext;

        //按照日期生成文件夹 /uploads/yyyy-MM-dd
        String realPath = session.getServletContext().getRealPath("/uploads");
        System.out.println("realPath----------->"+realPath);
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File folder = new File(realPath + "/" + datePath);

        if (! folder.exists()){
            folder.mkdirs();
        }

        uploadFile.transferTo(new File(folder,newName));
        return "success";
    }





}
