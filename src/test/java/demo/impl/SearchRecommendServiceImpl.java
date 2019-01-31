package demo.impl;

import com.cxd.baseservice.impl.BaseServiceImpl;
import com.cxd.baseservice.mapper.BaseMapper;
import com.cxd.baseservice.util.ServiceResp;
import demo.SearchRecommendService;
import demo.entity.CfgSearchRecommend;
import demo.mapper.CfgSearchRecommendMapper;
import demo.po.CfgSearchRecommendExample;
import demo.vo.SearchRecommendVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wenter on 2019/1/31.
 */
@Service("searchRecommendService")
public class SearchRecommendServiceImpl extends BaseServiceImpl<SearchRecommendVO, CfgSearchRecommend, CfgSearchRecommendExample, Long>
        implements SearchRecommendService<SearchRecommendVO, Long> {

    @Autowired
    private CfgSearchRecommendMapper mapper;

    @Override
    public BaseMapper<CfgSearchRecommend, CfgSearchRecommendExample, Long> getMapper() {
        return mapper;
    }

    @Override
    public ServiceResp<Boolean> offline(Long id) {
        return null;
    }
}
