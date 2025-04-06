package com.tree.cronjob;

import com.tree.domain.Article;
import com.tree.service.ArticleService;
import com.tree.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author 35238
 * @date 2023/8/1 0001 12:43
 */
@Component
//通过定时任务实现每隔3分钟把redis中的浏览量更新到mysql数据库中
public class UpdateViewCount {

    @Autowired
    //操作redis。RedisCache是我们在huanf-framework工程写的工具类
    private RedisCache redisCache;

    @Autowired
    //操作数据库。ArticleService是我们在huanf-framework工程写的接口
    private ArticleService articleService;


    // 定义一个 ThreadLocal 用于标记是否为定时任务更新浏览量
    private static final ThreadLocal<Boolean> IS_VIEW_COUNT_UPDATE_TASK = ThreadLocal.withInitial(() -> false);


    //每隔3分钟，把redis的浏览量数据更新到mysql数据库
    @Scheduled(cron = "0/10 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量，注意得到的viewCountMap是HashMap双列集合
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        //让双列集合调用entrySet方法即可转为单列集合，然后才能调用stream方法
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                //把最终数据转为List集合
                .collect(Collectors.toList());
        //把获取到的浏览量更新到mysql数据库中。updateBatchById是mybatisplus提供的批量操作数据的接口
        articleService.updateBatchById(articles);
        //方便在控制台看打印信息
        System.out.println("redis的文章浏览量数据已更新到数据库，现在的时间是: "+ LocalTime.now());
    }

    // 提供一个静态方法用于获取标记
    public static boolean isViewCountUpdateTask() {
        return IS_VIEW_COUNT_UPDATE_TASK.get();
    }
}

