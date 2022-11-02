package com.atxin.glmall.search.service;

import com.atxin.common.to.es.SkuEsModel;


import java.io.IOException;
import java.util.List;


public interface CommoditSaveService {

    boolean commoditStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
