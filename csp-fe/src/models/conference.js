import config from 'utils/config'
import { parse } from 'qs'
import { message } from 'antd'
import _ from 'lodash'
import { routerRedux } from 'dva/router'
import { queryAutoComplete } from 'services/common'
import {
  query, create, remove, update, createConfNo,
  queryPerson, createPerson, updatePerson, removePerson, uploadPerson,
  queryCheckin, createCheckin, updateCheckin, removeCheckin, queryEvents,
  queryPlace, createPlace, removePlace, updatePlace, queryPlaceRoom, createPlaceRoom, removePlaceRoom,
  updatePlaceRoom,
  queryCourse, createCourse, updateCourse, removeCourse, createIdentity, queryConfRooms,
  queryIdentity, deleteIdentity, updateIdentity,
  queryPersonByFilter,
} from '../services/conference'


export default {

  namespace: 'conference',

  state: {
    confModalVisible: false, // 创建、修改会议
    confModalType: 'create',
    isMotion: true,
    list: [],
    currentItem: {},
    pagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null,
    },
    personType: [], // 参数字典
    idType: [],
    personModalVisible: false, // 创建、修改会议
    personModalType: 'create',
    personList: [],
    currentConfNo: '',
    currentConfId: '',
    currentPerson: {},
    personPagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null,
    },
    confNoDataSource: [],
    personUploadModalVisible: false, // 显示上传面板
    validatorErrMsg: '', // 校验上传数据异常
    fileList: [], // 上传的文件，限制只能上传一个文件
    identitys: {}, // 证件数据
    currentIdenty: {},
    identityModalVisible: false,
    checkinType: [],
    checkinModalVisible: false,
    checkinModalType: 'create',
    checkinList: [],
    currentCheckin: {},
    checkinPagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null,
    },
    confChecked: true,
    courseChecked: false,
    checkinChecked: false,
    events: [], //  所有事件
    eventsStore: {
      1: [], // 会议
      2: {}, // 课程
      3: {}, // 签到
    },
    placeList: [],
    placePagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null,
    },
    currentPlace: {},
    placeModalType: 'create',
    placeModalVisible: false,
    roomList: {},
    currentConfRooms: [], // 当前会场的房间
    placeRoomModalType: 'create',
    placeRoomModalVisible: false,
    placeDataSource: [],
    placesRooms: [],
    courseModalType: 'create',
    courseModalVisible: false,
    currentCourse: {},
    checkinTypeVisible: false,
    identityModalType: 'create',
    identityList: [], // 证件列表
    currentIdentity: {},
    layoutFileList: [],
    bottomFileList: [],
    personAutoCompleteList: [], // 已有参会人员列表
    personAutoCompleateDataSource: [],
    existPersonModalVisible: false,
    currentCheckedPerson: {},
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen((location) => {
        if (location.pathname === '/conference') {
          dispatch({
            type: 'query',
            payload: location.query,
          })

          dispatch({
            type: 'queryParaType',
            payload: {
              paraType: `${config.dict.personType},${config.dict.idType},${config.dict.checkinType}`,
            },
          })

          dispatch({
            type: 'queryEvents',
            payload: {
              type: 1,
              checked: true,
            },
          })

          dispatch({
            type: 'queryPlaceCombo',
            payload: {},
          })
        }
      })
    },
  },

  effects: {

    * query ({ payload }, { call, put }) {
      payload = parse(location.search.substr(1))
      const data = yield call(query, payload)
      if (data) {
        yield put({
          type: 'queryConfSuccess',
          payload: {
            currentConfId: payload.id,
            list: data.list,
            pagination: {
              current: Number(payload.page) || 1,
              pageSize: Number(payload.pageSize) || 10,
              total: data.total,
            },
          },
        })
      }
    },

    * delete ({ payload }, { call, put }) {
      const data = yield call(remove, { id: payload })
      if (data.success) {
        // 选中会议并且删除会议之后显示所有列表
        yield put(routerRedux.push('/conference'))
        // yield put({ type: 'query', payload: { showAll: true } })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 1,
            checked: true,
            forceupdate: true,
          },
        })
      } else {
        throw data.result.message
      }
    },

    * create ({ payload }, { call, put }) {
      const data = yield call(create, payload)
      if (data.success) {
        yield put({ type: 'hideConfModal' })
        yield put({ type: 'query' })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 1,
            checked: true,
            forceupdate: true,
          },
        })
      } else {
        throw data.result.message
      }
    },

    * update ({ payload }, { call, put }) {
      const data = yield call(update, payload)
      if (data.success) {
        yield put({ type: 'hideConfModal' })
        yield put({ type: 'query' })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 1,
            checked: true,
            forceupdate: true,
          },
        })
      } else {
        throw data.result.message
      }
    },

    * createConfNo ({ payload }, { call, put }) {
      const data = yield call(createConfNo, payload)
      if (data.success) {
        yield put({ type: 'createConfNoSuccess', payload: { confNo: data.result } })
      } else {
        throw data.result.message
      }
    },

    * queryConfNo ({ payload }, { call, put }) {
      const data = yield call(queryAutoComplete, payload)
      if (data.success) {
        yield put({ type: 'queryConfNoSuccess', payload: { confNoDataSource: data.list } })
      } else {
        throw data.result.message
      }
    },

    * queryPlaceCombo ({ payload }, { call, put }) {
      payload = { ...payload, s: config.combox.placeCombo }
      const data = yield call(queryAutoComplete, payload)
      if (data.success) {
        yield put({ type: 'queryPlaceComboSuccess', payload: { placeDataSource: data.list } })
      } else {
        throw data.result.message
      }
    },

    * queryPerson ({ payload }, { select, call, put }) {
      const currentConfId = yield select(({ conference }) => conference.currentConfId)
      if (!currentConfId) {
        message.error('请先选择会议')
        yield put({
          type: 'queryPersonSuccess',
          payload: {
            personList: [],
            personPagination: {
              current: Number(payload.page) || 1,
              pageSize: Number(payload.pageSize) || 10,
              total: 0,
            },
          },
        })
        return
      }
      payload = payload || {}
      payload.confId = currentConfId
      const data = yield call(queryPerson, payload)
      if (data) {
        yield put({
          type: 'queryPersonSuccess',
          payload: {
            personList: data.list,
            personPagination: {
              current: Number(payload.page) || 1,
              pageSize: Number(payload.pageSize) || 10,
              total: data.total,
            },
          },
        })
      }
    },

    * deletePerson ({ payload }, { call, put }) {
      const data = yield call(removePerson, payload)
      if (data.success) {
        yield put({ type: 'queryPerson' })
      } else {
        throw data.result.message
      }
    },

    * createPerson ({ payload }, { call, put }) {
      const data = yield call(createPerson, payload)
      if (data.success) {
        yield put({ type: 'hidePersonModal' })
        yield put({ type: 'queryPerson' })
      } else {
        throw data.result.message
      }
    },

    * updatePerson ({ payload }, { call, put }) {
      const data = yield call(updatePerson, payload)
      if (data.success) {
        yield put({ type: 'hidePersonModal' })
        yield put({ type: 'queryPerson' })
      } else {
        throw data.result.message
      }
    },

    * queryParaType ({ payload }, { select, put, take }) {
      yield put({
        type: 'app/queryDict',
        payload,
      })
      yield take('app/queryDict/@@end')
      const parameters = yield select(({ app }) => app.parameters)
      yield put({
        type: 'queryParaTypeSuccess',
        payload: {
          parameters,
        },
      })
    },

    * uploadPerson ({ payload }, { call, put }) {
      const data = yield call(uploadPerson, payload)
      if (data.success) {
        yield put({
          type: 'hidePersonUploadModal',
        })
        yield put({ type: 'queryPerson' })
      } else {
        yield put({
          type: 'validatorError',
          payload: {
            validatorErrMsg: data.result.message,
          },
        })
      }
    },

    * queryCheckin ({ payload }, { select, call, put }) {
      const currentConfId = yield select(({ conference }) => conference.currentConfId)
      if (!currentConfId) {
        message.error('请先选择会议')
        yield put({
          type: 'queryCheckinSuccess',
          payload: {
            checkinList: [],
            checkinPagination: {
              current: Number(payload.page) || 1,
              pageSize: Number(payload.pageSize) || 10,
              total: 0,
            },
          },
        })
        return
      }
      payload = payload || {}
      payload.confId = currentConfId
      const data = yield call(queryCheckin, payload)
      if (data) {
        yield put({
          type: 'queryCheckinSuccess',
          payload: {
            checkinList: data.list,
            checkinPagination: {
              current: Number(payload.page) || 1,
              pageSize: Number(payload.pageSize) || 10,
              total: data.total,
            },
          },
        })
      }
    },

    * deleteCheckin ({ payload }, { call, put }) {
      const data = yield call(removeCheckin, payload)
      if (data.success) {
        yield put({ type: 'queryCheckin' })
      } else {
        throw data.result.message
      }
    },

    * createCheckin ({ payload }, { call, put }) {
      const data = yield call(createCheckin, payload)
      if (data.success) {
        yield put({ type: 'hideCheckinModal' })
        yield put({ type: 'queryCheckin' })
      } else {
        throw data.result.message
      }
    },

    * updateCheckin ({ payload }, { call, put }) {
      const data = yield call(updateCheckin, payload)
      if (data.success) {
        yield put({ type: 'hideCheckinModal' })
        yield put({ type: 'queryCheckin' })
      } else {
        throw data.result.message
      }
    },

    * queryEvents ({ payload }, { select, call, put }) {
      const { eventsStore, currentConfId } = yield select(({ conference }) => conference)
      let {
        type, confId, checked, forceupdate,
      } = payload
      if (!confId && currentConfId) {
        confId = currentConfId
        payload.confId = confId
      }
      // 课程和签到必须选择会议
      if (!confId && type !== 1) {
        message.error('必须选择会议才能查看课程和签到')
        return
      }
      let currentEventArr = type === 1 ? eventsStore[type] : eventsStore[type][confId]
      // 直接从 store 里面获取数据
      if (!forceupdate && ((currentEventArr && currentEventArr.length > 0) || !checked)) {
        yield put({ type: 'queryEventsSuccess', payload: { ...payload } })
      } else if (checked || forceupdate) {
        // 当 store 没有数据且勾选或者强制刷新为 true 则向后台请求数据
        const data = yield call(queryEvents, payload)
        if (data.success) {
          yield put({ type: 'queryEventsSuccess', payload: { result: data.array, ...payload } })
        } else {
          throw data.result.message
        }
      }
    },

    * queryPlace ({ payload }, { call, put }) {
      payload = payload || {}
      const data = yield call(queryPlace, payload)
      if (data) {
        yield put({
          type: 'queryPlaceSuccess',
          payload: {
            list: data.list,
            pagination: {
              current: Number(payload.page) || 1,
              pageSize: Number(payload.pageSize) || 10,
              total: data.total,
            },
          },
        })
      }
    },

    * deletePlace ({ payload }, { call, put }) {
      const data = yield call(removePlace, { id: payload })
      if (data.success) {
        yield put({ type: 'queryPlace' })
      } else {
        throw data.result.message
      }
    },

    * createPlace ({ payload }, { call, put }) {
      const data = yield call(createPlace, payload)
      if (data.success) {
        yield put({ type: 'hidePlaceModal' })
        yield put({ type: 'queryPlace' })
      } else {
        throw data.result.message
      }
    },

    * updatePlace ({ payload }, { call, put }) {
      const data = yield call(updatePlace, payload)
      if (data.success) {
        yield put({ type: 'hidePlaceModal' })
        yield put({ type: 'queryPlace' })
      } else {
        throw data.result.message
      }
    },

    * queryPlaceRoom ({ payload }, { call, put }) {
      const data = yield call(queryPlaceRoom, payload)
      if (data) {
        yield put({
          type: 'queryPlaceRoomSuccess',
          payload: {
            id: payload.placeId,
            result: data.array,
          },
        })
      }
    },

    * deletePlaceRoom ({ payload }, { call, put }) {
      const data = yield call(removePlaceRoom, payload)
      if (data.success) {
        yield put({ type: 'queryPlaceRoom', payload })
      } else {
        throw data.result.message
      }
    },

    * createPlaceRoom ({ payload }, { call, put }) {
      const data = yield call(createPlaceRoom, payload)
      if (data.success) {
        yield put({ type: 'hidePlaceRoomModal' })
        yield put({ type: 'queryPlaceRoom', payload: { placeId: payload.placeId } })
      } else {
        throw data.result.message
      }
    },

    * updatePlaceRoom ({ payload }, { call, put }) {
      const data = yield call(updatePlaceRoom, payload)
      if (data.success) {
        yield put({ type: 'hidePlaceRoomModal' })
        yield put({ type: 'queryPlaceRoom', payload: { placeId: payload.placeId } })
      } else {
        throw data.result.message
      }
    },

    * queryConfRooms ({ payload }, { call, put }) {
      const data = yield call(queryConfRooms, payload)
      if (data.success) {
        yield put({
          type: 'queryResultSuccess',
          payload: {
            currentConfRooms: data.array,
          },
        })
      } else {
        throw data.result.message
      }
    },

    * queryCourse ({ payload }, { call, put, select }) {
      const { id } = payload
      const { currentConfId } = yield select(({ conference }) => conference)
      payload.confId = currentConfId
      let data = {}
      // 修改课程
      if (id) {
        data = yield call(queryCourse, payload)
      }
      // 查询会场对应的房间
      const currentConfRooms = yield call(queryConfRooms, payload)
      yield put({
        type: 'showCourseModal',
        payload: {
          ...payload,
          currentCourse: data.result || { confId: currentConfId },
          currentConfRooms: currentConfRooms != null ? currentConfRooms.array : [],
        },
      })
    },

    * createCourse ({ payload }, { call, put }) {
      const data = yield call(createCourse, payload)
      if (data.success) {
        yield put({ type: 'hideCourseModal' })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 2,
            checked: true,
            forceupdate: true,
          },
        })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 3,
            checked: true,
            forceupdate: true,
          },
        })
        // yield put({ type: 'queryPlace' })
      } else {
        throw data.result.message
      }
    },

    * updateCourse ({ payload }, { call, put }) {
      const data = yield call(updateCourse, payload)
      if (data.success) {
        yield put({ type: 'hideCourseModal' })
        // yield put({ type: 'queryPlace' })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 2,
            checked: true,
            forceupdate: true,
          },
        })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 3,
            checked: true,
            forceupdate: true,
          },
        })
      } else {
        throw data.result.message
      }
    },

    * deleteCourse ({ payload }, { call, put }) {
      const { confId } = payload
      const data = yield call(removeCourse, payload)
      if (data.success) {
        yield put({
          type: 'queryEvents',
          payload: {
            type: 2,
            forceupdate: true,
            confId,
          },
        })
        yield put({
          type: 'queryEvents',
          payload: {
            type: 3,
            checked: true,
            forceupdate: true,
          },
        })
      } else {
        throw data.result.message
      }
    },

    // 证件增
    * createIdentity ({ payload }, { call, put }) {
      const data = yield call(createIdentity, payload)
      if (data.success) {
        yield put({
          type: 'hideIdentityModal',
        })
        yield put({
          type: 'queryIdentity',
        })
      } else {
        throw data.result.message
      }
    },
    // 证件删
    * deleteIdentity ({ payload }, { call, put }) {
      const data = yield call(deleteIdentity, payload)
      if (data.success) {
        yield put({
          type: 'hideIdentityModal',
        })
        yield put({
          type: 'queryIdentity',
        })
      } else {
        throw data.result.message
      }
    },
    // 证件改
    * updateIdentity ({ payload }, { call, put }) {
      const data = yield call(updateIdentity, payload)
      if (data.success) {
        yield put({
          type: 'hideIdentityModal',
        })
        yield put({
          type: 'queryIdentity',
        })
      } else {
        throw data.result.message
      }
    },

    // 证件查
    * queryIdentity ({ payload }, { call, put, select }) {
      const { currentConfId } = yield select(({ conference }) => conference)
      payload = payload || { confId: currentConfId }
      const data = yield call(queryIdentity, payload)
      if (data.success) {
        yield put({
          type: 'queryIdentitySuccess',
          payload: {
            identityList: data.array,
          },
        })
      } else {
        throw data.result.message
      }
    },

    * personAutoComplete ({ payload }, { call, put }) {
      const { type } = payload
      const data = yield call(queryPersonByFilter, payload)
      if (data.success) {
        const result = data.list || []
        const personAutoCompleateDataSource = result.map((r) => {
          return {
            value: r.id,
            text: `${r.name}  ${r.idNumber}`,
            name: r.name,
          }
        })
        yield put({
          type: 'querySuccess',
          payload: {
            personAutoCompleteList: result,
            personAutoCompleateDataSource,
          },
        })
      } else {
        throw data.result.message
      }
    },

  },

  reducers: {

    queryConfSuccess (state, action) {
      const { list, pagination, currentConfId } = action.payload
      let currentConfNo
      if (currentConfId) {
        const currRecord = list.filter(x => x.id === currentConfId)[0]
        currentConfNo = currRecord.confNo
      }
      list.filter(x => x.id)
      return {
        ...state,
        list,
        currentConfId,
        currentConfNo,
        pagination: {
          ...state.pagination,
          ...pagination,
        },
      }
    },

    showConfModal (state, action) {
      return { ...state, ...action.payload, confModalVisible: true }
    },

    hideConfModal (state) {
      return { ...state, confModalVisible: false, currentItem: {} }
    },

    createConfNoSuccess (state, action) {
      const { currentItem } = state
      const { confNo } = action.payload
      currentItem.confNo = confNo
      return {
        ...state,
        currentItem,
      }
    },

    queryConfNoSuccess (state, action) {
      return {
        ...state,
        ...action.payload,
      }
    },

    queryPersonSuccess (state, action) {
      return {
        ...state,
        ...action.payload,
      }
    },

    showPersonModal (state, action) {
      return {
        ...state,
        ...action.payload,
        personModalVisible: true,
        personAutoCompleteList: [],
        personAutoCompleateDataSource: [],
      }
    },

    hidePersonModal (state) {
      return { ...state, personModalVisible: false, currentPerson: {} }
    },

    queryParaTypeSuccess (state, action) {
      const { parameters } = action.payload
      const personType = parameters[config.dict.personType]
      const idType = parameters[config.dict.idType]
      const checkinType = parameters[config.dict.checkinType]
      return {
        ...state,
        personType,
        idType,
        checkinType,
      }
    },

    showPersonUploadModal (state, action) {
      // const { currentConfId } = state
      // if (!currentConfId) {
      //   message.error('请选择会议再进行上传操作！')
      //   return {
      //     ...state,
      //   }
      // }
      return { ...state, ...action.payload, personUploadModalVisible: true }
    },

    hidePersonUploadModal (state) {
      return {
        ...state, personUploadModalVisible: false, validatorErrMsg: '', fileList: [],
      }
    },

    validatorError (state, { payload }) {
      return {
        ...state,
        ...payload,
      }
    },

    queryCheckinSuccess (state, action) {
      return {
        ...state,
        ...action.payload,
      }
    },

    uploadFile (state, { payload }) {
      return {
        ...state,
        ...payload,
      }
    },

    showCheckinModal (state, action) {
      return { ...state, ...action.payload, checkinModalVisible: true }
    },

    hideCheckinModal (state) {
      return { ...state, checkinModalVisible: false, currentCheckin: {} }
    },

    queryEventsSuccess (state, action) {
      let {
        result, type, confId, checked,
      } = action.payload
      let {
        events, eventsStore, confChecked, courseChecked, checkinChecked,
      } = state
      // type 1:会议 2:课程 3:签到
      // 将事件添加到 store
      if (result != null) {
        result.forEach((event) => {
          event.start = new Date(event.start)
          if (event.allDay) {
            // month 和 week 视图下面会少一天
            event.end = new Date(event.end + 1)
          } else {
            event.end = new Date(event.end)
          }
        })
        if (type === 2 || type === 3) {
          eventsStore[type][confId] = result
        } else if (type === 1) {
          eventsStore[type] = result
        }
      }
      // 如果当前会议为空，则删除课程和签到事件
      if (!confId) {
        _.remove(events, (e) => {
          return e.type === 2 || e.type === 3
        })
        // 将课程和签到设置为未选中
        checked = true
        checkinChecked = false
        courseChecked = false
      }
      // 当前操作的事件列表
      let currentEventArr = type === 1 ? eventsStore[type] : eventsStore[type][confId]
      // 删除非当前会议里面的事件
      if (type === 2 || type === 3) {
        _.remove(events, (e) => {
          // 当前事件不在当前会议课程事件里面
          const notInEvent2 = eventsStore[2][confId] && eventsStore[2][confId].filter(x => x.id === e.id).length === 0
          // 当前事件不在当前会议签到事件里面
          const notInEvent3 = eventsStore[3][confId] && eventsStore[3][confId].filter(x => x.id === e.id).length === 0
          // 非会议事件
          return notInEvent2 && notInEvent3 && e.type !== 1
        })
      }
      // 从 store 添加事件到 events
      if (checked) {
        events = _.unionWith(currentEventArr, events, (x1, x2) => x1.id === x2.id)
      } else {
        // 从 events 里面移除事件
        _.remove(events, (e) => {
          return currentEventArr.filter(x => x.id === e.id).length === 1 && e.id !== confId
        })
      }

      if (type === 1) {
        confChecked = checked
      } else if (type === 2) {
        courseChecked = checked
      } else if (type === 3) {
        checkinChecked = checked
      }
      // 显示哪些事件
      return {
        ...state, events, confChecked, courseChecked, checkinChecked,
      }
    },

    queryPlaceSuccess (state, action) {
      const { list, pagination } = action.payload
      return {
        ...state,
        placeList: list,
        placePagination: {
          ...state.pagination,
          ...pagination,
        },
      }
    },

    showPlaceModal (state, action) {
      return { ...state, ...action.payload, placeModalVisible: true }
    },

    hidePlaceModal (state) {
      return { ...state, placeModalVisible: false }
    },

    queryPlaceRoomSuccess (state, action) {
      const { roomList } = state
      const { result, id } = action.payload
      roomList[id] = result
      return {
        ...state,
        roomList,
      }
    },

    showPlaceRoomModal (state, action) {
      return { ...state, ...action.payload, placeRoomModalVisible: true }
    },

    hidePlaceRoomModal (state) {
      return { ...state, placeRoomModalVisible: false }
    },

    queryPlaceComboSuccess (state, action) {
      return { ...state, ...action.payload }
    },

    queryResultSuccess (state, action) {
      return { ...state, ...action.payload }
    },

    showCourseModal (state, action) {
      const { currentCourse } = action.payload
      let { checkinTypeVisible } = state
      if (currentCourse && currentCourse.isCheckin === 1) {
        checkinTypeVisible = true
      }
      return {
        ...state, ...action.payload, courseModalVisible: true, checkinTypeVisible,
      }
    },

    hideCourseModal (state) {
      return { ...state, courseModalVisible: false, checkinTypeVisible: false }
    },

    showCheckinType (state, { payload }) {
      return {
        ...state, ...payload,
      }
    },
    // 证件查询成功
    queryIdentitySuccess (state, action) {
      return {
        ...state,
        ...action.payload,
      }
    },
    // 证件模态框打开
    showIdentityModal (state, action) {
      return { ...state, ...action.payload, identityModalVisible: true }
    },
    // 证件模态框关闭
    hideIdentityModal (state) {
      return {
        ...state, identityModalVisible: false, fileList: [], fileList1: [],
      }
    },
    querySuccess (state, action) {
      return {
        ...state,
        ...action.payload,
      }
    },
    hideExistPersonModal (state) {
      return {
        ...state,
        existPersonModalVisible: false,
      }
    },
    showExistPersonModal (state) {
      return {
        ...state,
        existPersonModalVisible: true,
      }
    },
    choosePerson (state, action) {
      const { id } = action.payload
      const { personAutoCompleteList } = state
      const currentPerson = personAutoCompleteList.filter(x => x.id === id)[0]
      return {
        ...state,
        currentPerson,
      }
    },
    exportQRCode (state, action) {
      const { currentConfId } = state
      window.location.href = `/conference/exportQRCode?confId=${currentConfId}`
    },
  },

}
