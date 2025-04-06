package com.tree.controller;


import com.tree.annotation.mySystemLog;
import com.tree.domain.ResponseResult;
import com.tree.domain.Tag;
import com.tree.dto.AddTagDto;
import com.tree.dto.EditTagDto;
import com.tree.dto.TagListDto;
import com.tree.service.TagService;


import com.tree.utils.BeanCopyUtils;
import com.tree.vo.PageVo;
import com.tree.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/2 0002 21:27
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    //TagService是我们在huanf-framework工程的接口
    private TagService tagService;

    //查询标签列表
    @GetMapping("/list")
    @mySystemLog(xxbusinessName = "查询标签")
    //ResponseResult是我们在huanf-framework工程的实体类
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        //pageTagList是我们在huanf-framework工程写的方法
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    //-------------------------------新增标签------------------------------------

    @PostMapping
    @mySystemLog(xxbusinessName = "添加标签")
    public ResponseResult add(@RequestBody AddTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    ////-------------------------------删除标签------------------------------------
    @DeleteMapping
    public ResponseResult remove(@RequestParam(value = "ids")String ids) {
        if (!ids.contains(",")) {
            tagService.removeById(ids);
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                tagService.removeById(id);
            }
        }
        return ResponseResult.okResult();
    }

    ////-------------------------------修改标签------------------------------------

    @GetMapping("/{id}")
    @mySystemLog(xxbusinessName = "查询标签")
    //①根据标签的id来查询标签
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping
    //②根据标签的id来修改标签
    public ResponseResult edit(@RequestBody EditTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto,Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    //---------------------------写博文-查询文章标签的接口---------------------------
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
