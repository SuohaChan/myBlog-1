package com.tree.vo;



import com.tree.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/4 0004 19:41
 * 动态路由
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {
    private List<Menu> menus;
}