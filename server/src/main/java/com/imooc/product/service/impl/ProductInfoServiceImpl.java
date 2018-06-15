package com.imooc.product.service.impl;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.enums.ProductStatus;
import com.imooc.product.enums.ResultEnum;
import com.imooc.product.exception.ProductException;
import com.imooc.product.pojo.ProductInfo;
import com.imooc.product.repositories.ProductInfoRepository;
import com.imooc.product.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@CacheConfig(cacheNames = "product")
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
//    @Cacheable(key = "123")
    public ProductInfo findOne(String productId) {
        return repository.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
//    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public List<ProductInfoOutput> findByProductIdList(List<String> productIdList) {
        List<ProductInfo> productInfoList =repository.findByProductIdIn(productIdList);
        return productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
    }

//    @Override
//    public void increaseStock(List<CartDto> cartDtoList) {
//        for(CartDto cartDto : cartDtoList){
//            ProductInfo productInfo = repository.findOne(cartDto.getProductId());
//            if(productInfo == null){
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
//            productInfo.setProductStock(result);
//            repository.save(productInfo);
//        }
//    }
//
    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for(DecreaseStockInput decreaseStockInput : decreaseStockInputList){
            Optional<ProductInfo> productInfoOptional = repository.findById(decreaseStockInput.getProductId());
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if(result < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
//
//    @Override
//    public ProductInfo onSaleProduct(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if(productInfo == null){
//            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if(productInfo.getProductStatus().equals(ProductStatus.UP)){
//            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//        productInfo.setProductStatus(ProductStatus.UP.getCode());
//        ProductInfo result = repository.save(productInfo);
//        if(result == null){
//            throw new SellException(ResultEnum.PRODUCT_UPDATE_ERROR);
//        }
//        return result;
//    }
//
//    @Override
//    public ProductInfo offSaleProduct(String productId) {
//        ProductInfo productInfo = repository.findOne(productId);
//        if(productInfo == null){
//            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//        }
//        if(productInfo.getProductStatus().equals(ProductStatus.DOWN)){
//            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
//        }
//        productInfo.setProductStatus(ProductStatus.DOWN.getCode());
//        ProductInfo result = repository.save(productInfo);
//        if(result == null){
//            throw new SellException(ResultEnum.PRODUCT_UPDATE_ERROR);
//        }
//        return result;
//    }


}
