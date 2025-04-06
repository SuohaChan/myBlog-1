package com.tree.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.tree.annotation.mySystemLog;
import com.tree.domain.Category;
import com.tree.domain.ResponseResult;
import com.tree.dto.CategoryDto;
import com.tree.enums.AppHttpCodeEnum;
import com.tree.service.CategoryService;
import com.tree.utils.BeanCopyUtils;
import com.tree.utils.WebUtils;
import com.tree.vo.CategoryVo;
import com.tree.vo.ExcelCategoryVo;
import com.tree.vo.PageVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    //---------------------------写博文-查询文章分类的接口--------------------------------
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    @mySystemLog(xxbusinessName = "查询所有分类")
    public ResponseResult listAllCategory() {
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    //----------------------------分页查询分类列表-------------------------------------

    @GetMapping("/list")
    @mySystemLog(xxbusinessName = "分页查询")
    public ResponseResult list(Category category, Integer pageNum, Integer pageSize) {
        PageVo pageVo = categoryService.selectCategoryPage(category,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }

    //-----------------------------增加文章的分类--------------------------------------

    @PostMapping
    @mySystemLog(xxbusinessName = "增加分类")
    public ResponseResult add(@RequestBody CategoryDto categoryDto){
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        categoryService.save(category);
        return ResponseResult.okResult();
    }


    @mySystemLog(xxbusinessName = "根据分类的id来删除分类")
    @DeleteMapping
    public ResponseResult remove(@RequestParam(value = "ids")String ids) {
        if (!ids.contains(",")) {
            categoryService.removeById(ids);
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                categoryService.removeById(id);
            }
        }
        return ResponseResult.okResult();
    }

    //-----------------------------修改文章的分类--------------------------------------

    @GetMapping(value = "/{id}")
    @mySystemLog(xxbusinessName = "根据分类的id来查询分类")
    //①根据分类的id来查询分类
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Category category = categoryService.getById(id);
        return ResponseResult.okResult(category);
    }

    @PutMapping
    @mySystemLog(xxbusinessName = "根据分类的id来修改分类")
    //②根据分类的id来修改分类
    public ResponseResult edit(@RequestBody Category category){
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }

    //---------------------------把分类数据写入到Excel并导出-----------------------------

    @GetMapping("/export")
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    //有无export权限

    //@PreAuthorize 是 Spring Security 提供的一个注解，用于在方法调用之前进行权限检查。
    // 它的主要作用是基于表达式的权限控制，只有当表达式的计算结果为 true 时，方法才会被允许执行；
    // 如果表达式的计算结果为 false，则会抛出 AccessDeniedException 异常，表示当前用户没有权限访问该方法。
    //注意返回值类型是void
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头，下载下来的Excel文件叫'分类.xlsx'
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> xxcategory = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(xxcategory, ExcelCategoryVo.class);
            //把数据写入到Excel中，也就是把ExcelCategoryVo实体类的字段作为Excel表格的列头
            //sheet方法里面的字符串是Excel表格左下角工作簿的名字
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("文章分类")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常,就返回失败的json数据给前端。AppHttpCodeEnum和ResponseResult是我们在huanf-framework工程写的类
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            //WebUtils是我们在huanf-framework工程写的类，里面的renderString方法是将json字符串写入到请求体，然后返回给前端
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
}
