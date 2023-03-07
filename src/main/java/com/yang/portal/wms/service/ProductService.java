package com.yang.portal.wms.service;

import com.yang.portal.security.core.jwt.JWTToken;
import com.yang.portal.wms.entity.Product;
import com.yang.portal.wms.vo.product.ProductInsertVo;
import com.yang.portal.wms.vo.product.ProductUpsertVo;

/**
 * 商品表(Product)表服务接口
 *
 * @author makejava
 * @since 2023-02-19 21:29:48
 */
public interface ProductService {

    void insertProduct(ProductInsertVo productInsertVo, JWTToken jwtToken);

    void upsertProduct(Long id, ProductUpsertVo productUpsertVo, JWTToken jwtToken);

    void search();

    Product searchProductByCode(String code);

    void delete(Long id, JWTToken jwtToken);

}
