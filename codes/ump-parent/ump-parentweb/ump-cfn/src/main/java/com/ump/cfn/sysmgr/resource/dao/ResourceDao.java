package com.ump.cfn.sysmgr.resource.dao;

import java.util.List;

import com.ump.cfn.sysmgr.resource.model.Resource;
import com.ump.core.base.dao.BaseDao;

public interface ResourceDao extends BaseDao<Resource> {
    public List<Resource> findResourcesByUserName(String userCode);
}
