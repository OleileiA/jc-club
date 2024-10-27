package com.jingdianjichi.subject.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
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
import java.util.List;

/**
 * 刷题分类
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /*
    * 增加分类
    * */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO dto) {

        try {
            // log.isInfoEnabled() 用于检查是否需要执行 log.info() 方法。这样做的好处是，只有在 INFO 级别的日志启用时，才会进行消息的生成和拼接
            // 从而避免了在日志级别不启用的情况下执行不必要的计算。
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto: {}", JSON.toJSONString(dto));
            }

            // 参数的校验
            Preconditions.checkNotNull(dto.getCategoryName(), "分类名字不能为空");
            Preconditions.checkNotNull(dto.getCategoryType(), "分类类型不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToBoCategory(dto);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /*
    * 更新
    * */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO dto) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.update.dto: {}", JSON.toJSONString(dto));
            }

            Preconditions.checkNotNull(dto.getId(), "id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToBoCategory(dto);
            subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /*
    * 删除
    * */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO dto) {

        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.delete.dto: {}", JSON.toJSONString(dto));
            }

            Preconditions.checkNotNull(dto.getId(), "id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToBoCategory(dto);

            subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /*
    * 查询分类列表
    * */
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {

        try {

            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryPrimaryCategory.dto: {}", JSON.toJSONString(subjectCategoryDTO));
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "parentId不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToBoCategory(subjectCategoryDTO);

            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);

            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertBOListToDTOList(subjectCategoryBOList);

            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
