
package com.example.test.web;

import com.alibaba.fastjson.JSON;
import com.example.test.utils.CotrollerFuction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;



@Slf4j
@Controller
@SpringBootApplication
@Api(tags = "Http重定向相关接口")
public class RedirectGetAndPostControllerApplication {


    @Autowired
    CotrollerFuction cotrollerFuction;

    @ApiOperation("http重定向结束接口")
    @ResponseBody
    @RequestMapping(value="/redirectEnd" , method = {RequestMethod.GET,RequestMethod.POST})
    public String index(HttpServletRequest request) {
        return "重定向访问! " + JSON.toJSONString(request.getParameterMap());
    }

    @ApiOperation("http重定向入口接口")
    @ApiImplicitParams(
        {
          @ApiImplicitParam(name = "loginNo", value = "登录名", required = false , dataType = "String"),
          @ApiImplicitParam(name = "merchantName", value = "商户名", required = false , dataType = "String"),
          @ApiImplicitParam(name = "merchantShortName", value = "商户简称", required = false , dataType = "String"),
          @ApiImplicitParam(name = "contactPhone", value = "手机号", required = false , dataType = "String"),
          @ApiImplicitParam(name = "prodCode", value = "产品码", required = false , dataType = "String"),
          @ApiImplicitParam(name = "rosefinchUsername", value = "平台用户名", required = false , dataType = "String"),
          @ApiImplicitParam(name = "count", value = "生成个数", required = false , dataType = "String")
        })
    @RequestMapping(value="/redirectGetAndPost" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost( HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost1";
    }


    @ApiOperation("http重定向跳转接口1")
    @RequestMapping(value="/redirectGetAndPost1" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost1(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost2";
    }

    @ApiOperation("http重定向跳转接口2")
    @RequestMapping(value="/redirectGetAndPost2" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost2(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost3";
    }

    @ApiOperation("http重定向跳转接口3")
    @RequestMapping(value="/redirectGetAndPost3" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost3(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost4";
    }

    @ApiOperation("http重定向跳转接口4")
    @RequestMapping(value="/redirectGetAndPost4" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost4(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost5";
    }

    @ApiOperation("http重定向跳转接口5")
    @RequestMapping(value="/redirectGetAndPost5" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost5(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost6";
    }

    @ApiOperation("http重定向跳转接口6")
    @RequestMapping(value="/redirectGetAndPost6" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost6(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost7";
    }

    @ApiOperation("http重定向跳转接口7")
    @RequestMapping(value="/redirectGetAndPost7" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost7(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);

        return "redirect:/redirectGetAndPost8";
    }

    @ApiOperation("http重定向跳转接口8")
    @RequestMapping(value="/redirectGetAndPost8" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost8(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost9";
    }

    @ApiOperation("http重定向跳转接口9")
    @RequestMapping(value="/redirectGetAndPost9" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost9(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost10";
    }

    @ApiOperation("http重定向跳转接口10")
    @RequestMapping(value="/redirectGetAndPost10" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost10(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {
        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost11";
    }

    @ApiOperation("http重定向跳转接口11")
    @RequestMapping(value="/redirectGetAndPost11" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost11(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        //return "redirect:/index";
        return "redirect:/redirectGetAndPost12";
    }


    @ApiOperation("http重定向跳转接口12")
    @RequestMapping(value="/redirectGetAndPost12" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost12(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost13";
    }


    @ApiOperation("http重定向跳转接口13")
    @RequestMapping(value="/redirectGetAndPost13" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost13(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost14";
    }

    @ApiOperation("http重定向跳转接口14")
    @RequestMapping(value="/redirectGetAndPost14" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost14(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost15";
    }

    @ApiOperation("http重定向跳转接口15")
    @RequestMapping(value="/redirectGetAndPost15" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost15(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost16";
    }

    @ApiOperation("http重定向跳转接口16")
    @RequestMapping(value="/redirectGetAndPost16" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost16(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost17";
    }
    @ApiOperation("http重定向跳转接口17")
    @RequestMapping(value="/redirectGetAndPost17" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost17(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost18";
    }

    @ApiOperation("http重定向跳转接口18")
    @RequestMapping(value="/redirectGetAndPost18" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost18(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost19";
    }

    @ApiOperation("http重定向跳转接口19")
    @RequestMapping(value="/redirectGetAndPost19" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost19(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost20";
    }

    @ApiOperation("http重定向跳转接口20")
    @RequestMapping(value="/redirectGetAndPost20" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost20(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectGetAndPost21";
    }

    @ApiOperation("http重定向跳转接口21")
    @RequestMapping(value="/redirectGetAndPost21" , method = {RequestMethod.GET,RequestMethod.POST})
    public String redirectGetAndPost21(Model model, HttpServletRequest httpRequest, RedirectAttributes attr) {

        cotrollerFuction.printMessage(httpRequest,attr);
        return "redirect:/redirectEnd";
    }




}

