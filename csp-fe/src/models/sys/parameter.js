import _ from 'lodash'
import { parse } from 'qs'
import { message } from 'antd'
import { queryAutoComplete } from '../../services/common'
import {
  query, create, remove, update,
  queryParameter, createParameter, removeParameter, updateParameter, sortParameter,
} from '../../services/sys/parameter'

export default {

  namespace: 'parameter',

  state: {
    list: [],
    currentItem: {},
    roles: [],
    nameDataSource: [],
    modalVisible: false,
    modalType: 'create',
    isMotion: true,
    pagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      total: null,
    },
    subList: {},
    currentSubItem: {},
    parameterModalType: 'create',
    parameterModalVisible: false,
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen((location) => {
        if (location.pathname === '/sys/parameter') {
          dispatch({
            type: 'query',
            payload: location.query,
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
          type: 'querySuccess',
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

    * delete ({ payload }, { call, put }) {
      const data = yield call(remove, { id: payload })
      if (data.success) {
        yield put({ type: 'query' })
      } else {
        throw data.result.message
      }
    },

    * create ({ payload }, { call, put }) {
      const data = yield call(create, payload)
      if (data.success) {
        yield put({ type: 'hideModal' })
        yield put({ type: 'query' })
      } else {
        throw data.result.message
      }
    },

    * update ({ payload }, { select, call, put }) {
      const id = yield select(({ parameter }) => parameter.currentItem.id)
      const newParatype = { ...payload, id }
      const data = yield call(update, newParatype)
      if (data.success) {
        yield put({ type: 'hideModal' })
        yield put({ type: 'query' })
      } else {
        throw data.result.message
      }
    },

    * queryParameter ({ payload }, { call, put }) {
      payload = payload || {}
      const data = yield call(queryParameter, payload)
      if (data) {
        yield put({
          type: 'queryParameterSuccess',
          payload: {
            typeCode: payload.typeCode,
            result: data.list,
          },
        })
      }
    },

    * deleteParameter ({ payload }, { call, put }) {
      const data = yield call(removeParameter, { id: payload })
      if (data.success) {
        yield put({ type: 'queryParameter' })
      } else {
        throw data.result.message
      }
    },

    * createParameter ({ payload }, { call, put }) {
      const data = yield call(createParameter, payload)
      if (data.success) {
        yield put({ type: 'hideParameterModal' })
        yield put({ type: 'query' })
      } else {
        throw data.result.message
      }
    },

    * updateParameter ({ payload }, { call, put }) {
      const data = yield call(updateParameter, payload)
      if (data.success) {
        yield put({ type: 'hideParameterModal' })
        yield put({ type: 'query' })
      } else {
        throw data.result.message
      }
    },

    * sortParameter ({ payload }, { select, call, put }) {
      const subList = yield select(({ parameter }) => parameter.subList)
      const { record, subRecord, operator } = payload
      let subRecordList = subList[record.code]
      const index = _.findIndex(subRecordList, x => x.id === subRecord.id)
      let siblingId
      if (operator === 'up') {
        if (subRecordList[0].id === subRecord.id) {
          message.error('不能往上移动')
          return
        }
        const prevRecord = subRecordList[index - 1]
        siblingId = prevRecord.id
        const prevSequence = prevRecord.sequence
        prevRecord.sequence = subRecord.sequence
        subRecordList[index] = prevRecord
        subRecord.sequence = prevSequence
        subRecordList[index - 1] = subRecord
      } else if (operator === 'down') {
        if (subRecordList[subRecordList.length - 1].id === subRecord.id) {
          message.error('不能往下移动')
          return
        }
        const nextRecord = subRecordList[index + 1]
        siblingId = nextRecord.id
        const nextSequence = nextRecord.sequence
        nextRecord.sequence = subRecord.sequence
        subRecordList[index] = nextRecord
        subRecord.sequence = nextSequence
        subRecordList[index + 1] = subRecord
      }
      subList[record.code] = subRecordList
      const data = yield call(sortParameter, { id: subRecord.id, siblingId })
      if (data.success) {
        yield put({ type: 'sortParameterSuccess', payload: { subList } })
      } else {
        throw data.result.message
      }
    },

    * change ({ payload }, { call, put }) {
      const data = yield call(queryAutoComplete, payload)
      if (data) {
        yield put({
          type: 'changeNameDataSource',
          payload: {
            nameDataSource: data.list,
          },
        })
      }
    },

  },

  reducers: {

    querySuccess (state, action) {
      const { list, pagination } = action.payload
      return {
        ...state,
        list,
        pagination: {
          ...state.pagination,
          ...pagination,
        },
      }
    },

    showModal (state, action) {
      return { ...state, ...action.payload, modalVisible: true }
    },

    hideModal (state) {
      return { ...state, modalVisible: false }
    },

    switchIsMotion (state) {
      localStorage.setItem('antdAdminUserIsMotion', !state.isMotion)
      return { ...state, isMotion: !state.isMotion }
    },

    queryParameterSuccess (state, action) {
      const { subList } = state
      const { result, typeCode } = action.payload
      subList[typeCode] = result
      return {
        ...state,
        subList,
      }
    },

    sortParameterSuccess (state, action) {
      const { subList } = action.payload
      return {
        ...state,
        subList,
      }
    },

    showParameterModal (state, action) {
      let { record, currentSubItem } = action.payload
      currentSubItem = currentSubItem || {}
      if (record) {
        currentSubItem.typeCode = record.code
      }
      return {
        ...state, ...action.payload, currentSubItem, parameterModalVisible: true,
      }
    },

    hideParameterModal (state) {
      return { ...state, parameterModalVisible: false }
    },

    changeNameDataSource (state, action) {
      const { nameDataSource } = action.payload
      return {
        ...state,
        nameDataSource,
      }
    },

  },

}
