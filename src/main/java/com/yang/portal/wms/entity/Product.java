package com.yang.portal.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@TableName("PRODUCT")
@Document(indexName = "wms_product", createIndex = false)
@Accessors(chain = true)
public class Product extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @Id
    private Long id;
    /**
     * 商品名称
     */
    @Field(name = "NAME", analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Keyword)
    private String name;
    /**
     * 商品编码
     */
    @Field(name = "CODE", analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Keyword)
    private String code;

}

