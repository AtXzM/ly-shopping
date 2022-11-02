package com.atxin.glmall.commodit.service.impl;

import com.atxin.common.constant.CommoditConstant;
import com.atxin.glmall.commodit.dao.AttrAttrgroupRelationDao;
import com.atxin.glmall.commodit.dao.AttrGroupDao;
import com.atxin.glmall.commodit.dao.CategoryDao;
import com.atxin.glmall.commodit.dao.entity.AttrAttrgroupRelationEntity;
import com.atxin.glmall.commodit.dao.entity.AttrGroupEntity;
import com.atxin.glmall.commodit.dao.entity.CategoryEntity;
import com.atxin.glmall.commodit.service.CategoryService;
import com.atxin.glmall.commodit.vo.AttrGroupRelationVo;
import com.atxin.glmall.commodit.vo.AttrRespVo;
import com.atxin.glmall.commodit.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atxin.common.utils.PageUtils;
import com.atxin.common.utils.Query;

import com.atxin.glmall.commodit.dao.AttrDao;
import com.atxin.glmall.commodit.dao.entity.AttrEntity;
import com.atxin.glmall.commodit.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Autowired
    AttrAttrgroupRelationDao relationDao;
    @Autowired
    AttrGroupDao attrGroupDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    CategoryService categoryService;
    //分页查询
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }
    //规格参数  属性补充
    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId,String type) {
        QueryWrapper<AttrEntity> queryWrapper=new QueryWrapper<AttrEntity>().eq("attr_type",
                "base".equalsIgnoreCase(type)?CommoditConstant.AttrEnum.ATTR_TYPE_BASE.getCode():CommoditConstant.AttrEnum.ATTR_TYPE_SALE.getCode());;
        if(catelogId!=0){
            queryWrapper.eq("catelog_Id",catelogId);

        }
        String key=(String)params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((wrapper)->{
                wrapper.eq("attr_id",key).or().like("attr_name",key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records=page.getRecords();
        List<AttrRespVo> respVos = records.stream().map((attrEntity) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            //设置所属分类以及所属分组
            if("base".equalsIgnoreCase(type)){
                AttrAttrgroupRelationEntity attrId = relationDao.selectOne((new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId())));
                if (attrId != null&&attrId.getAttrGroupId()!=null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }


            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(respVos);
        return pageUtils;
    }
//规格参数  属性参数(点击修改 )回显
    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
       AttrRespVo respVo=new AttrRespVo();
       AttrEntity attrEntity=this.getById(attrId);
       //将attrEntity 属性拷贝到respVo里面
       BeanUtils.copyProperties(attrEntity,respVo);
       if(attrEntity.getAttrType()
       ==CommoditConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
           AttrAttrgroupRelationEntity attrgroupRelation
                   = relationDao.selectOne
                   (new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
           if(attrgroupRelation!=null) {
               respVo.setAttrGroupId(attrgroupRelation.getAttrGroupId());
               AttrGroupEntity attrGroupEntity
                       = attrGroupDao.selectById(attrgroupRelation.getAttrGroupId());
               if(attrGroupEntity!=null)
                   respVo.setGroupName(attrGroupEntity.getAttrGroupName());
           }
       }
       //分组信息

   //分类信息
        Long catelogId =attrEntity.getCatelogId();
   Long[] catelogPath=categoryService.findCatelogPath(catelogId);
   respVo.setCatelogPath(catelogPath);
   CategoryEntity categoryEntity=categoryDao.selectById(catelogId);
   if(categoryEntity!=null)
   respVo.setCatelogName(categoryEntity.getName());
   return respVo;
    }
    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity=new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        this.updateById(attrEntity);
        if(attrEntity.getAttrType()
                ==CommoditConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            //统计当前attrid有没有关联的分组属性
            Integer count
                    = relationDao.selectCount
                    (new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            if(count>0){
                //修改分组关联
                relationDao.update(relationEntity,
                        new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attr.getAttrId()));
            }
            //否则新增
            else relationDao.insert(relationEntity);
        }
        //1 修改分组关联

    }
//保存 规格参数与销售属性
    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity=new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        //保存基本数据
        this.save(attrEntity);
        //保存关联关系
        if(attr.getAttrType()== CommoditConstant.AttrEnum.ATTR_TYPE_BASE.getCode()
        &&attr.getAttrGroupId()!=null){
            AttrAttrgroupRelationEntity relationEntity=new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationDao.insert(relationEntity);
        }

    }
//根据分组id查找关联的所有基本属性
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        if(attrIds == null || attrIds.size() == 0){
            return null;
        }
        Collection<AttrEntity> attrEntities = this.listByIds(attrIds);
        return (List<AttrEntity>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] vos) {
//        relationDao.delete(new QueryWrapper<>().eq("attr_id",))
        List<AttrAttrgroupRelationEntity> entities = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        relationDao.deleteBatchRelation(entities);
    }

    @Override
    /**
     * 获取当前分组没有关联的所有属性
     * @param params
     * @param attrgroupId
     * @return
     */

    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        //1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        Long catelogId = attrGroupEntity.getCatelogId();
        //2、当前分组只能关联别的分组没有引用的属性
        //2.1)、当前分类下的其他分组
        List<AttrGroupEntity> group = attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        List<Long> collect = group.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());

        //2.2)、这些分组关联的属性
        List<AttrAttrgroupRelationEntity> groupId = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());

        //2.3)、从当前分类的所有属性中移除这些属性；
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).eq("attr_type",CommoditConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if(attrIds!=null && attrIds.size()>0){
            wrapper.notIn("attr_id", attrIds);
        }
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.eq("attr_id",key).or().like("attr_name",key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), wrapper);

        PageUtils pageUtils = new PageUtils(page);

        return pageUtils;
    }

    @Override
    public List<Long> selectSearchAttrs(List<Long> attrIds) {
        List<Long> searchAttrIds = this.baseMapper.selectSearchAttrIds(attrIds);

        return searchAttrIds;
    }


}