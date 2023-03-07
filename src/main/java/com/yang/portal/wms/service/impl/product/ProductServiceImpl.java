package com.yang.portal.wms.service.impl.product;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yang.portal.core.CoreConstant;
import com.yang.portal.security.core.jwt.JWTToken;
import com.yang.portal.wms.entity.Product;
import com.yang.portal.wms.esRepository.ProductEsRepository;
import com.yang.portal.wms.mapper.ProductMapper;
import com.yang.portal.wms.service.ProductService;
import com.yang.portal.wms.vo.product.ProductInsertVo;
import com.yang.portal.wms.vo.product.ProductUpsertVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductEsRepository productEsRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void insertProduct(ProductInsertVo productInsertVo, JWTToken jwtToken) {
        Product product = new Product();
        BeanUtils.copyProperties(productInsertVo, product);
        product.setCreatedBy(jwtToken.getUserPrincipal().getNickName())
                .setCreatedId(jwtToken.getUserPrincipal().getUserId());
        productMapper.insert(product);
    }

    @Override
    public void upsertProduct(Long id, ProductUpsertVo productUpsertVo, JWTToken jwtToken) {
        Product product = productMapper.selectById(id);
        Optional.of(productUpsertVo.getName()).ifPresent(product::setName);
        Optional.of(productUpsertVo.getDescription()).ifPresent(product::setDescription);
        product.setUpdatedBy(jwtToken.getUserPrincipal().getNickName())
                .setUpdatedId(jwtToken.getUserPrincipal().getUserId());
        productMapper.updateById(product);
    }

    @Override
    public void search() {
    }

    @Override
    public Product searchProductByCode(String code) {
        return productEsRepository.findOneByCodeAndIsDelete(code, CoreConstant.IS_DELETE);
    }

    @Override
    public void delete(Long id, JWTToken jwtToken) {
        LambdaUpdateWrapper<Product> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Product::getId,id)
                .eq(Product::getIsDelete,CoreConstant.IS_DELETE)
                .set(Product::getIsDelete,!CoreConstant.IS_DELETE)
                .set(Product::getUpdatedId,jwtToken.getUserPrincipal().getUserId())
                .set(Product::getUpdatedBy,jwtToken.getUserPrincipal().getNickName())
                .set(Product::getUpdatedTime, LocalDateTime.now());
        productMapper.update(null,lambdaUpdateWrapper);
    }
}
