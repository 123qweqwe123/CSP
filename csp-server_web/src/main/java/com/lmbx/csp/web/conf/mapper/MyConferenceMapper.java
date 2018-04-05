package com.lmbx.csp.web.conf.mapper;

import com.lmbx.csp.data.conf.domain.CspConference;
import com.lmbx.csp.web.conf.filter.ConferenceCheckinFilter;
import com.lmbx.csp.web.conf.filter.ConferenceFilter;
import com.lmbx.csp.web.conf.filter.ConferencePersonFilter;
import com.lmbx.csp.web.conf.filter.ConferencePlaceFilter;
import com.lmbx.csp.web.conf.vo.CspConfCheckinVO;
import com.lmbx.csp.web.conf.vo.CspMainPersonVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/27 上午9:29
 */
@Mapper
public interface MyConferenceMapper {

    List<CspConference> selectConferenceByFilter(ConferenceFilter filter);

    String selectMaxConfNo();

    List<CspMainPersonVO>  selectConfPerson(ConferencePersonFilter filter);

    List<CspConfCheckinVO> selectConfCheckinByFilter(ConferenceCheckinFilter filter);

    List<?> selectPlaceByFilter(ConferencePlaceFilter filter);

    List<?> selectMainPersonByFilter(ConferencePersonFilter filter);
}
