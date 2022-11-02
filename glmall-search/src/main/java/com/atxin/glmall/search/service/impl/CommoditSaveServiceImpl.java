package com.atxin.glmall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atxin.common.to.es.SkuEsModel;
import com.atxin.glmall.search.config.GlmallElasticSearchConfig;
import com.atxin.glmall.search.constant.EsConstant;
import com.atxin.glmall.search.service.CommoditSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



@Slf4j
@Service
public class CommoditSaveServiceImpl implements CommoditSaveService {

    @Autowired
    private RestHighLevelClient esRestClient;

    @Override
    public boolean commoditStatusUp(List<SkuEsModel> skuEsModels) throws IOException {

        //1.在es中建立索引，建立号映射关系（doc/json/commodit-mapping.json）

        //2. 在ES中保存这些数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            //构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.COMMODIT_INDEX);
            indexRequest.id(skuEsModel.getSkuId().toString());
            String jsonString = JSON.toJSONString(skuEsModel);
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        //批量保存所有数据
        BulkResponse bulk = esRestClient.bulk(bulkRequest, GlmallElasticSearchConfig.COMMON_OPTIONS);

        //TODO 如果批量错误
        boolean hasFailures = bulk.hasFailures();

        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
            return item.getId();
        }).collect(Collectors.toList());

        log.info("商品上架成功：{}",collect);

        return hasFailures;
    }
}
