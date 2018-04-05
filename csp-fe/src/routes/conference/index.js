import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { routerRedux } from 'dva/router'
import { connect } from 'dva'
import { Tabs, Icon, Alert, Modal } from 'antd'
import { Page } from 'components'
import config from 'utils/config'
import List from './List'
import Filter from './Filter'
import ConfModal from './ConfModal'
import PersonList from './PersonList'
import PersonModal from './PersonModal'
import CheckinList from './CheckinList'
import PersonUploadModal from './PersonUploadModal'
import CheckinModal from './CheckinModal'
import Calendar from './Calendar'
import ConfPlaceList from './ConfPlaceList'
import ConfPlaceModal from './ConfPlaceModal'
import ConfPlaceRoomModal from './ConfPlaceRoomModal'
import ConfCoursemodal from './ConfCourseModal'
import IdentityModal from './IdentityModal'
import IdentityList from './IdentityList'
import ExistPersonModal from './ExistPersonModal'


const { TabPane } = Tabs

const Conference = ({
  location, dispatch, conference, loading,
}) => {
  const {
    list, pagination, currentItem, confModalType, isMotion, confModalVisible, confNoDataSource, placeDataSource,
    personList, personPagination, currentPerson, personModalType, personModalVisible,
    personType, idType,
    currentConfNo, currentConfId,
    personUploadModalVisible, validatorErrMsg, fileList,
    checkinList, checkinPagination, currentCheckin, checkinModalVisible, checkinType, checkinModalType,
    events, confChecked, courseChecked, checkinChecked,
    placeList, placePagination, currentPlace, placeModalType, placeModalVisible, roomList,
    currentRoom, placeRoomModalType, placeRoomModalVisible, currentConfRooms,
    placesRooms, courseModalType, courseModalVisible, currentCourse, checkinTypeVisible,
    identityModalVisible, identityModalType, currentIdentity, identitys,
    identityList, layoutFileList, bottomFileList,
    personAutoCompleteList, existPersonModalVisible, currentCheckedPerson, personAutoCompleateDataSource,
  } = conference
  const { pageSize } = pagination

  const modalProps = {
    placeDataSource,
    item: currentItem,
    visible: confModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${confModalType === 'create' ? 'conference/create' : 'conference/update'}`],
    title: `${confModalType === 'create' ? '添加会议' : '修改会议'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `conference/${confModalType}`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hideConfModal',
      })
    },
    changeConfType (value) {
      dispatch({
        type: 'conference/createConfNo',
        payload: {
          confType: value,
        },
      })
    },
    handlerSearch (val) {
      dispatch({
        type: 'conference/queryPlaceCombo',
        payload: {
          q: val,
        },
      })
    },
  }

  const listProps = {
    dataSource: list,
    loading: loading.effects['conference/query'],
    pagination,
    location,
    isMotion,
    onChange (page) {
      const { query, pathname } = location
      dispatch(routerRedux.push({
        pathname,
        search: queryString.stringify({
          ...query,
          page: page.current,
          pageSize: page.pageSize,
        }),
      }))
    },
    onDeleteItem (id) {
      dispatch({
        type: 'conference/delete',
        payload: id,
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'conference/showConfModal',
        payload: {
          confModalType: 'update',
          currentItem: item,
        },
      })
    },
    onAdd () {
      dispatch({
        type: 'conference/showConfModal',
        payload: {
          confModalType: 'create',
        },
      })
    },
    changeStatus (record) {
      dispatch({
        type: 'conference/update',
        payload: {
          id: record.id,
          status: 4, // 将会议状态更新为已完结
        },
      })
    },
    onEditIdentity (item) {
      dispatch({
        type: 'conference/queryIdentity',
        payload: item,
      })
    },
    onDeleteIdentity (item) {
      dispatch({
        type: 'conference/deleteIdentity',
        payload: item,
      })
    },
  }

  const filterProps = {
    isMotion,
    confNoDataSource,
    handlerSearch (val) {
      dispatch({
        type: 'conference/queryConfNo',
        payload: {
          q: val,
          s: config.combox.confNoCombo,
        },
      })
    },
    filter: {
      ...location.query,
    },
    onFilterChange (value) {
      dispatch(routerRedux.push({
        pathname: location.pathname,
        search: queryString.stringify({
          ...value,
          page: 1,
          pageSize,
        }),
      }))
    },
    onSearch (fieldsValue) {
      fieldsValue.keyword.length ? dispatch(routerRedux.push({
        pathname: '/user',
        query: {
          field: fieldsValue.field,
          keyword: fieldsValue.keyword,
        },
      })) : dispatch(routerRedux.push({
        pathname: '/user',
      }))
    },
  }


  const personListProps = {
    currentConfId,
    dataSource: personList,
    loading: loading.effects['conference/queryPerson'],
    pagination: personPagination,
    location,
    isMotion,
    onChange (page) {
      dispatch({
        type: 'conference/queryPerson',
        payload: {
          page: page.current,
          pageSize: page.pageSize,
        },
      })
    },
    onDeleteItem (item) {
      dispatch({
        type: 'conference/deletePerson',
        payload: {
          id: item.personId,
          type: item.personType,
          confId: currentConfId,
        },
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'conference/showPersonModal',
        payload: {
          personModalType: 'update',
          currentPerson: item,
        },
      })
    },
    onAdd () {
      dispatch({
        type: 'conference/showPersonModal',
        payload: {
          personModalType: 'create',
        },
      })
    },
    downloadTemplate () {
      dispatch({
        type: 'app/downloadTemplate',
        payload: {
          tempName: '会议服务平台-参会人员导入模板.xlsx',
        },
      })
    },
    onAddExist () {
      dispatch({
        type: 'conference/showExistPersonModal',
      })
    },
    showPersonUploadModal () {
      dispatch({
        type: 'conference/showPersonUploadModal',
      })
    },
    exportQRCode () {
      dispatch({
        type: 'conference/exportQRCode',
      })
    },
  }

  const personModalProps = {
    idType,
    personType,
    currentConfNo,
    confNoDataSource,
    currentConfId,
    personAutoCompleteList,
    personAutoCompleateDataSource,
    item: currentPerson,
    visible: personModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${personModalType === 'create' ? 'conference/createPerson' : 'conference/updatePerson'}`],
    title: `${personModalType === 'create' ? '添加人员' : '修改人员'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `conference/${personModalType}Person`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hidePersonModal',
      })
    },
    handlerSearch (val) {
      dispatch({
        type: 'conference/queryConfNo',
        payload: {
          q: val,
          s: config.combox.confNoCombo,
        },
      })
    },
    handlerSearchPerson (val, type) {
      // 只有新建的时候才能选择已有的人员
      if (personModalType !== 'create') {
        return
      }
      dispatch({
        type: 'conference/personAutoComplete',
        payload: {
          type,
          str: val,
        },
      })
    },
    choosePerson (id) {
      dispatch({
        type: 'conference/choosePerson',
        payload: {
          id,
        },
      })
    },
  }

  const personUploadModalProps = {
    currentConfId,
    currentConfNo,
    fileList,
    validatorErrMsg,
    confNoDataSource,
    visible: personUploadModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects['conference/uploadPerson'],
    title: '上传参会人员',
    wrapClassName: 'vertical-center-modal',
    okText: '上传',
    onOk (data) {
      dispatch({
        type: 'conference/uploadPerson',
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hidePersonUploadModal',
      })
    },
    handlerSearch (val) {
      dispatch({
        type: 'conference/queryConfNo',
        payload: {
          q: val,
          s: config.combox.confNoCombo,
        },
      })
    },
    uploadFile (val) {
      dispatch({
        type: 'conference/uploadFile',
        payload: {
          fileList: val,
        },
      })
    },
  }

  const existPersonModalProps = {
    idType,
    personType,
    currentConfNo,
    confNoDataSource,
    currentConfId,
    personAutoCompleteList,
    item: currentCheckedPerson,
    visible: existPersonModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects['conference/createPerson'],
    title: '添加已存在人员',
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: 'conference/createPerson',
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hideExistPersonModal',
      })
    },
    handlerSearch (val) {
      dispatch({
        type: 'conference/queryConfNo',
        payload: {
          q: val,
          s: config.combox.confNoCombo,
        },
      })
    },
  }

  const handlerCloseAlert = () => {
    const { query, pathname } = location
    delete query.id
    dispatch(routerRedux.push({
      pathname,
      search: queryString.stringify({
        ...query,
      }),
    }))
  }

  const handlerTabClick = (key) => {
    if (key === '2') {
      dispatch({
        type: 'conference/queryPerson',
        payload: {
          page: 1,
          pageSize: 10,
        },
      })
    } else if (key === '3') {
      dispatch({
        type: 'conference/queryCheckin',
        payload: {
          page: 1,
          pageSize: 10,
        },
      })
    } else if (key === '5') {
      dispatch({
        type: 'conference/queryPlace',
        payload: {
          page: 1,
          pageSize: 10,
        },
      })
    } else if (key === '6') {
      dispatch({
        type: 'conference/queryIdentity',
      })
    }
  }

  const checkinListProps = {
    dataSource: checkinList,
    loading: loading.effects['conference/quertCheckin'],
    pagination: checkinPagination,
    location,
    isMotion,
    checkinType,
    onChange (page) {
      dispatch({
        type: 'conference/queryCheckin',
        payload: {
          page: page.current,
          pageSize: page.pageSize,
        },
      })
    },
    onDeleteItem (item) {
      dispatch({
        type: 'conference/deleteCheckin',
        payload: {
          id: item.id,
          confId: currentConfId,
        },
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'conference/showCheckinModal',
        payload: {
          checkinModalType: 'update',
          currentCheckin: item,
        },
      })
    },
    onAdd () {
      dispatch({
        type: 'conference/showCheckinModal',
        payload: {
          checkinModalType: 'create',
        },
      })
    },
  }

  const checkinModalProps = {
    checkinType,
    personType,
    currentConfNo,
    confNoDataSource,
    currentConfId,
    item: currentCheckin,
    visible: checkinModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${checkinModalType === 'create' ? 'conference/createCheckin' : 'conference/updateCheckin'}`],
    title: `${checkinModalType === 'create' ? '添加签到' : '修改签到'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `conference/${checkinModalType}Checkin`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hideCheckinModal',
      })
    },
    handlerSearch (val) {
      dispatch({
        type: 'conference/queryConfNo',
        payload: {
          q: val,
          s: config.combox.confNoCombo,
        },
      })
    },
  }

  const calendarProps = {
    events,
    currentConfId,
    confChecked,
    courseChecked,
    checkinChecked,
    switchItem (type, checked) {
      dispatch({
        type: 'conference/queryEvents',
        payload: {
          type,
          checked,
          confId: currentConfId,
        },
      })
    },
    addCourse () {
      dispatch({
        type: 'conference/queryCourse',
        payload: {
          courseModalType: 'create',
        },
      })
    },
    updateCourse (event) {
      dispatch({
        type: 'conference/queryCourse',
        payload: {
          id: event.id,
          courseModalType: 'update',
        },
      })
    },
    deleteCourse (event) {
      Modal.confirm({
        title: '删除课程',
        content: '删除课程后，所有关联的签到和附件都将删除',
        okText: '确认',
        cancelText: '取消',
        onOk () {
          dispatch({
            type: 'conference/deleteCourse',
            payload: {
              id: event.id,
              confId: currentConfId,
            },
          })
        },
      })
    },
    toConf (e, confId) {
      e.stopPropagation()
      const { pathname } = location
      dispatch(routerRedux.push({
        pathname,
        search: queryString.stringify({
          id: confId,
        }),
      }))
    },
  }

  const confPlaceListProps = {
    pagination: placePagination,
    location,
    isMotion,
    roomList,
    dataSource: placeList,
    loading: loading.effects['conference/queryPlace'],
    onChange (page) {
      dispatch({
        type: 'conference/queryPlace',
        payload: {
          page: page.current,
          pageSize: page.pageSize,
        },
      })
    },
    onDeleteItem (id) {
      dispatch({
        type: 'conference/deletePlace',
        payload: id,
      })
    },
    onAdd () {
      dispatch({
        type: 'conference/showPlaceModal',
        payload: {
          placeModalType: 'create',
        },
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'conference/showPlaceModal',
        payload: {
          placeModalType: 'update',
          currentPlace: item,
        },
      })
    },
    onEditSubItem (item) {
      dispatch({
        type: 'conference/showPlaceRoomModal',
        payload: {
          placeRoomModalType: 'update',
          currentRoom: item,
        },
      })
    },
    onDeleteSubItem (subItem) {
      dispatch({
        type: 'conference/deletePlaceRoom',
        payload: {
          id: subItem.id,
          placeId: subItem.placeId,
        },
      })
    },
    onExpand (isExpand, record) {
      if (isExpand) {
        dispatch({
          type: 'conference/queryPlaceRoom',
          payload: { placeId: record.id },
        })
      }
    },
    onAddRoom (record) {
      dispatch({
        type: 'conference/showPlaceRoomModal',
        payload: {
          currentRoom: {
            placeId: record.id,
          },
          placeRoomModalType: 'create',
        },
      })
    },
  }

  const confPlaceModalProps = {
    item: placeModalType === 'create' ? {} : currentPlace,
    visible: placeModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${placeModalType === 'create' ? 'conference/createPlace' : 'conference/updatePlace'}`],
    title: `${placeModalType === 'create' ? '添加会场' : '修改会场'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `conference/${placeModalType}Place`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hidePlaceModal',
      })
    },
  }

  const courseModalProps = {
    checkinTypeVisible,
    placesRooms,
    currentConfRooms,
    personType,
    item: currentCourse,
    visible: courseModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${courseModalType === 'create' ? 'conference/createCourse' : 'conference/updateCourse'}`],
    title: `${placeModalType === 'create' ? '添加课程' : '修改课程'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `conference/${courseModalType}Course`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hideCourseModal',
      })
    },

    changeCheckinVisible (val) {
      dispatch({
        type: 'conference/showCheckinType',
        payload: {
          checkinTypeVisible: val,
        },
      })
    },
  }

  const confPlaceRoomModalProps = {
    item: currentRoom,
    visible: placeRoomModalVisible,
    maskClosable: false,
    confirmLoading: loading.effects[`${placeRoomModalType === 'create' ? 'conference/createPlaceRoom' : 'conference/updatePlaceRoom'}`],
    title: `${placeRoomModalType === 'create' ? '添加房间' : '修改房间'}`,
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `conference/${placeRoomModalType}PlaceRoom`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({
        type: 'conference/hidePlaceRoomModal',
      })
    },
  }
  // 这里是getIdentity创建的地方
  const identityModalProps = {
    layoutFileList,
    bottomFileList,
    identityList,
    personType,
    maskClosable: false,
    title: `${identityModalType === 'create' ? '添加' : '修改'}证件模板`,
    visible: identityModalVisible,
    item: currentIdentity,
    confirmLoading: loading.effects[`${identityModalType === 'create' ? 'conference/create' : 'conference/update'}Identity`],
    wrapClassName: 'vertical-center-modal',
    onOk (data) {
      dispatch({
        type: `conference/${identityModalType}Identity`,
        payload: data,
      })
    },
    onCancel () {
      dispatch({ type: 'conference/hideIdentityModal' })
    },
    uploadLayoutFile (val) {
      dispatch({
        type: 'conference/uploadFile',
        payload: {
          layoutFileList: val,
        },
      })
    },
    uploadBottomFile (val) {
      dispatch({
        type: 'conference/uploadFile',
        payload: {
          bottomFileList: val,
        },
      })
    },
  }

  const identityListProps = {
    dataSource: identityList,
    loading: loading.effects['conference/queryIdentity'],
    location,
    onDeleteItem (item) {
      dispatch({
        type: 'conference/deleteIdentity',
        payload: {
          id: item.id,
          confId: currentConfId,
        },
      })
    },
    onEditItem (item) {
      dispatch({
        type: 'conference/showIdentityModal',
        payload: {
          identityModalType: 'update',
          currentIdentity: {
            ...item,
            confNo: currentConfNo,
          },
        },
      })
    },
    onAdd () {
      dispatch({
        type: 'conference/showIdentityModal',
        payload: {
          identityModalType: 'create',
          layoutFileList: [],
          bottomFileList: [],
          currentIdentity: {
            confNo: currentConfNo,
            confId: currentConfId,
          },
        },
      })
    },
    download (fileId) {
      console.log(fileId)
    },
  }

  // {`当前会议编号：${currentConfNo}`}
  return (
    <Page inner>
      {currentConfNo &&
        <Alert
          message={<div>当前会议编号：<span style={{ color: '#108ee9', fontWeight: 'bold' }}>{currentConfNo}</span></div>}
          type="success"
          closable
          onClose={handlerCloseAlert}
          style={{ marginBottom: 10 }}
          closeText="所有会议"
        />}
      <Filter {...filterProps} />
      <Tabs onTabClick={handlerTabClick}>
        <TabPane tab={<span><Icon type="bars" />会议列表</span>} key="1">
          <List {...listProps} />
          {confModalVisible && <ConfModal {...modalProps} />}
        </TabPane>
        <TabPane tab={<span><Icon type="calendar" />日程管理</span>} key="4">
          <Calendar {...calendarProps} />
          {courseModalVisible && <ConfCoursemodal {...courseModalProps} />}
        </TabPane>
        <TabPane tab={<span><Icon type="edit" />会场维护</span>} key="5">
          <ConfPlaceList {...confPlaceListProps} />
          {placeModalVisible && <ConfPlaceModal {...confPlaceModalProps} />}
          {placeRoomModalVisible && <ConfPlaceRoomModal {...confPlaceRoomModalProps} />}
        </TabPane>
        <TabPane disabled={!currentConfId} tab={<span><Icon type="usergroup-add" />参会人员管理</span>} key="2">
          <PersonList {...personListProps} />
          {personModalVisible && <PersonModal {...personModalProps} />}
          {existPersonModalVisible && <ExistPersonModal {...existPersonModalProps} />}
          {personUploadModalVisible && <PersonUploadModal {...personUploadModalProps} />}
        </TabPane>
        <TabPane disabled={!currentConfId} tab={<span><Icon type="form" />签到任务安排</span>} key="3">
          <CheckinList {...checkinListProps} />
          {checkinModalVisible && <CheckinModal {...checkinModalProps} />}
        </TabPane>
        <TabPane disabled={!currentConfId} tab={<span><Icon type="idcard" />证件模板维护</span>} key="6">
          <IdentityList {...identityListProps} />
          {identityModalVisible && <IdentityModal {...identityModalProps} />}
        </TabPane>
      </Tabs>
    </Page>
  )
}

Conference.propTypes = {
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.any,
  conference: PropTypes.object,
}

export default connect(({ conference, loading }) => ({ conference, loading }))(Conference)
