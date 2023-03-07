package com.yang.portal.wms.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BaseEntity {

    //描述
    @Field(name = "DESCRIPTION", analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Keyword)
    private String description;
    //是否删除
    @TableLogic
    @Field(name = "IS_DELETE", type = FieldType.Boolean)
    private Boolean isDelete = Boolean.FALSE;

    //乐观锁
    @Version
    private Integer revision;
    //创建人ID
    @Field(name = "CREATED_ID", type = FieldType.Long)
    private Long createdId;
    //创建人
    @Field(name = "CREATED_BY", analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Keyword)
    private String createdBy;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT + 8")
    @Field(name = "CREATED_TIME", type = FieldType.Date)
    private LocalDateTime createdTime;
    //更新人ID
    @Field(name = "UPDATED_ID", type = FieldType.Long)
    private Long updatedId;
    //更新人
    @Field(name = "UPDATED_BY", analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Keyword)
    private String updatedBy;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT + 8")
    @Field(name = "UPDATED_TIME", type = FieldType.Date)
    private LocalDateTime updatedTime;
}
