package com.lmbx.csp.web.conf.controller;

import com.lmbx.csp.data.conf.domain.*;
import com.lmbx.csp.data.main.domain.CspMainPerson;
import com.lmbx.csp.web.conf.filter.*;
import com.lmbx.csp.web.conf.service.ConferenceService;
import com.lmbx.csp.web.conf.vo.ConfEventVO;
import com.lmbx.csp.web.conf.vo.IdentityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Description:
 * <p>
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/27 上午9:06
 */
@RestController
@RequestMapping("/conferences")
public class ConfController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping
    public List<?> queryConferences(ConferenceFilter filter) {
        return conferenceService.queryConferences(filter);
    }

    @PostMapping
    public void createConference(CspConference conference) {
        conferenceService.createConference(conference);
    }

    @PatchMapping
    public void updateConference(CspConference conference) {
        conferenceService.updateConference(conference);
    }

    @DeleteMapping
    public void deleteConference(String id) {
        conferenceService.deleteConference(id);
    }

    @GetMapping("/createConfNo")
    public String createConferenceNo(String confType) {
        return conferenceService.createConferenceNo(confType);
    }

    @GetMapping("/{confId}/persons")
    public List<?> queryConfPerson(@PathVariable(value = "confId") String confId, ConferencePersonFilter filter) {
        return conferenceService.queryConfPersons(confId, filter);
    }

    @PostMapping("/{confId}/persons")
    public void createConfPerson(@Valid CspMainPerson person, @PathVariable(value = "confId") String confId) {
        conferenceService.savePerson(person, confId);
    }

    @PatchMapping("/{confId}/persons")
    public void updateConfPerson(@Valid CspMainPerson person, @PathVariable(value = "confId") String confId) {
        conferenceService.updateConfPerson(person, confId);
    }

    @DeleteMapping("/{confId}/persons")
    public void deleteConfPerson(String id, @PathVariable(value = "confId") String confId, Short type) {
        conferenceService.deletePerson(id, confId, type);
    }

    @PostMapping("/{confId}/persons/upload")
    public void uploadConfPerson(String file, @PathVariable(value = "confId") String confId) {
        conferenceService.uploadConfPerson(file, confId);
    }

    @GetMapping("/{confId}/checkins")
    public List<?> queryConfCheckin(@PathVariable(value = "confId") String confId, ConferenceCheckinFilter filter) {
        return conferenceService.queryConfCheckins(confId, filter);
    }

    @PostMapping("/{confId}/checkins")
    public void createConfCheckin(CspConfCheckin checkin, @PathVariable(value = "confId") String confId,
                                  String personType) {
        conferenceService.createConfCheckin(checkin, confId, personType);
    }

    @PatchMapping("/{confId}/checkins")
    public void updateConfCheckin(CspConfCheckin checkin, @PathVariable(value = "confId") String confId,
                                  String personType) {
        conferenceService.updateConfCheckin(checkin, confId, personType);
    }

    @DeleteMapping("/{confId}/checkins")
    public void deleteConfCheckin(String id, @PathVariable(value = "confId") String confId) {
        conferenceService.deleteConfCheckin(id, confId);
    }

    @GetMapping("/events")
    public List<ConfEventVO> queryEvents(ConferenceEventFilter filter) {
        return conferenceService.queryEvents(filter);
    }

    @GetMapping("/places")
    public List<?> queryPlace(ConferencePlaceFilter filter) {
        return conferenceService.queryPlace(filter);
    }

    @PostMapping("/places")
    public void createPlace(CspConfPlace place) {
        conferenceService.createPlace(place);
    }

    @PatchMapping("/places")
    public void updatePlace(CspConfPlace place) {
        conferenceService.updatePlace(place);
    }

    @DeleteMapping("/places")
    public void deletePlace(String id) {
        conferenceService.deletePlace(id);
    }

    @GetMapping("/places/{placeId}/rooms")
    public List<?> queryPlaceRoom(@PathVariable(value = "placeId") String placeId) {
        return conferenceService.queryPlaceRoom(placeId);
    }

    @PostMapping("/places/{placeId}/rooms")
    public void createPlaceRoom(CspConfPlaceRoom room, @PathVariable(value = "placeId") String placeId) {
        conferenceService.createPlaceRoom(room, placeId);
    }

    @PatchMapping("/places/{placeId}/rooms")
    public void updatePlaceRoom(CspConfPlaceRoom room, @PathVariable(value = "placeId") String placeId) {
        conferenceService.updatePlaceRoom(room, placeId);
    }

    @DeleteMapping("/places/{placeId}/rooms")
    public void deletePlaceRoom(String id, @PathVariable(value = "placeId") String placeId) {
        conferenceService.deletePlaceRoom(id, placeId);
    }

    @GetMapping("/places/rooms")
    public List<?> queryAllPlacesAndRooms() {
        return conferenceService.queryAllPlacesAndRooms();
    }

    @GetMapping("/{confId}/courses")
    public CspConfCourse queryConfCourse(@PathVariable(value = "confId") String confId, String id) {
        return conferenceService.queryConfCourse(confId, id);
    }

    /**
     * @param course 课程实体
     * @param confId 会议 ID
     * @param roomId 会场房间号
     * @param file 上传的文件列表
     */
    @PostMapping("/{confId}/courses")
    public void createConfCourse(CspConfCourse course, @PathVariable(value = "confId") String confId, String roomId,
                                 String file) {
        conferenceService.createConfCourse(course, confId, roomId, file);
    }

    @PatchMapping("/{confId}/courses")
    public void updateConfCourse(CspConfCourse course, @PathVariable(value = "confId") String confId, String roomId,
                                 String file) {
        conferenceService.updateConfCourse(course, confId, roomId, file);
    }

    @DeleteMapping("/{confId}/courses")
    public void deleteConfCourse(String id, @PathVariable(value = "confId") String confId) {
        conferenceService.deleteConfCourse(id, confId);
    }

    /**
     * 证件新增
     *
     * @param identity
     * @param confId
     */
    @PostMapping("/{confId}/identity")
    public void createConfIdentity(IdentityVO identity, @PathVariable(value = "confId") String confId) {
        conferenceService.createConfIdentity(identity, confId);
    }

    /**
     * 证件删除
     *
     * @param confId
     */

    @DeleteMapping("/{confId}/identity")
    public void deleteIdentity(@PathVariable(value = "confId") String confId, String id) {
        conferenceService.deleteIdentity(confId, id);
    }

    /**
     * 证件修改
     *
     * @param identity
     * @param confId
     */
    @PatchMapping("/{confId}/identity")
    public void updateConfIdentity(IdentityVO identity, @PathVariable(value = "confId") String confId) {
        conferenceService.updateConfIdentity(identity, confId);
    }

    /**
     * 证件查
     *
     * @param confId
     */
    @GetMapping("/{confId}/identity")
    public List<?> queryConfIdentity(@PathVariable(value = "confId") String confId) {
        return conferenceService.queryConfIdentity(confId);
    }

    @GetMapping("/{confId}/rooms")
    public List<?> queryConfRooms(@PathVariable(value = "confId") String confId) {
        return conferenceService.queryConfRooms(confId);
    }

    @GetMapping("/personAutoComplete")
    public List<?> personAutoComplete(ConferencePersonFilter filter) {
        return conferenceService.personAutoComplete(filter);
    }

    @GetMapping("/exportQRCode")
    public ResponseEntity<Resource> exportQRCode(String confId, Short type) throws IOException {
        File zipFile = conferenceService.exportQRCode(confId, type);
        String fileName = new String(zipFile.getName().getBytes(System.getProperty("file.encoding")), "ISO-8859-1");
        Resource resource = new FileSystemResource(zipFile);
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + fileName
                + "\"").contentType(MediaType.parseMediaType("application/octet-stream")).contentLength(resource.contentLength()).body(resource);
    }

}
