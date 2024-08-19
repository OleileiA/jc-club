package com.jingdianjichi.subject.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianjichi.subject.common.entity.Result;
import com.jingdianjichi.subject.convert.SubjectCategoryDTOConverter;
import com.jingdianjichi.subject.domain.entity.SubjectCategoryBO;
import com.jingdianjichi.subject.domain.service.SubjectCategoryDomainService;
import com.jingdianjichi.subject.dto.SubjectCategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 刷题分类
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO dto) {
        try {
            // log.isInfoEnabled() 用于检查是否需要执行 log.info() 方法。这样做的好处是，只有在 INFO 级别的日志启用时，才会进行消息的生成和拼接
            // 从而避免了在日志级别不启用的情况下执行不必要的计算。
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto: {}", JSON.toJSONString(dto));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToBoCategory(dto);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail();
        }
    }
}
