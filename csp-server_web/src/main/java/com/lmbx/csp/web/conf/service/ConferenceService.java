package com.lmbx.csp.web.conf.service;

import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.web.conf.filter.*;
import com.lmbx.csp.web.conf.vo.ConfEventVO;
import com.lmbx.csp.web.conf.vo.IdentityVO;

import java.io.File;
import java.util.List;

/**
 * Description:
 * <p>
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/27 上午9:22
 */
public interface ConferenceService {

    /**
     * 查询会议
     *
     * @param filter
     * @return
     */
    List<?> queryConferences(ConferenceFilter filter);

    /**
     * 创建会议
     *
     * @param conference
     */
    void createConference(CspConference conference);

    /**
     * 定时任务，用来更新会议的状态
     */
    void setConfStatus();

    /**
     * 更新会议
     *
     * @param conference
     */
    void updateConference(CspConference conference);

    /**
     * 删除会议
     *
     * @param id
     */
    void deleteConference(String id);

    /**
     * 创建新的会议编号
     *
     * @return
     */
    String createConferenceNo(String confType);

    void savePerson(CspMainPerson person, String confId);

    void deletePerson(String id, String confId, Short type);

    void updateConfPerson(CspMainPerson person, String confId);

    List<?> queryConfPersons(String confId, ConferencePersonFilter filter);

    void uploadConfPerson(String file, String confId);

    void deleteConfCheckin(String id, String confId);

    void updateConfCheckin(CspConfCheckin checkin, String confId, String personType);

    void createConfCheckin(CspConfCheckin checkin, String confId, String personType);

    List<?> queryConfCheckins(String confId, ConferenceCheckinFilter filter);

    List<ConfEventVO> queryEvents(ConferenceEventFilter filter);

    void deletePlaceRoom(String id, String placeId);

    void updatePlaceRoom(CspConfPlaceRoom room, String placeId);

    void createPlaceRoom(CspConfPlaceRoom room, String placeId);

    List<?> queryPlaceRoom(String placeId);

    void deletePlace(String id);

    void updatePlace(CspConfPlace place);

    void createPlace(CspConfPlace place);

    List<?> queryPlace(ConferencePlaceFilter filter);

    List<?> queryAllPlacesAndRooms();

    void createConfCourse(CspConfCourse course, String confId, String roomId, String file);

    void updateConfCourse(CspConfCourse course, String confId, String roomId, String file);

    void deleteConfCourse(String id, String confId);

    CspConfCourse queryConfCourse(String confId, String id);

    /**
     * 证件增加
     *
     * @param identityDTO
     * @param confId
     */
    void createConfIdentity(IdentityVO identity, String confId);

    /**
     * 证件修改
     *
     * @param identityDTO
     * @param confId
     */
    void updateConfIdentity(IdentityVO identity, String confId);

    /**
     * 证件查询
     *
     * @param confId
     * @return
     */
    List<?> queryConfIdentity(String confId);

    /**
     * 证件删除
     *
     * @param confId
     * @return
     */

    void deleteIdentity(String confId, String id);

    List<?> queryConfRooms(String confId);

    List<?> personAutoComplete(ConferencePersonFilter filter);

    File exportQRCode(String confId, Short type);
}
