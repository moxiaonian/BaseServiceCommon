package demo;

import com.cxd.baseservice.BaseService;
import com.cxd.baseservice.util.ServiceResp;

import java.io.Serializable;

/**
 * Created by wenter on 2019/1/31.
 */
public interface SearchRecommendService<SearchRecommendVO, Long extends Serializable> extends BaseService<SearchRecommendVO, Long> {

    ServiceResp<Boolean> offline(Long id);
}
