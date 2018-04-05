import queryString from 'query-string'
import { queryDictVersions, queryLogs, update } from '../services/pageversion'

export default {

  namespace: 'dictVersion',

  state: {
    list: [],
    logList: [],
    currentItem: {},
    updateId: null,
    isDisable: false,
    isMotion: false,
    isUpload: false,
    pagination: {
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
      current: 1,
      size: 'small',
      total: null,
    },
    modalVisible: false,
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen((location) => {
        if (location.pathname === '/client/pagemanage') {
          dispatch({
            type: 'query',
            payload: queryString.parse(location.search),
          })
        }
      })
    },
  },

  effects: {

    * query ({ payload }, { call, put }) {
      payload = { ...payload, dictType: 'isUpload' }
      const data = yield call(queryDictVersions, payload)
      if (data) {
        yield put({
          type: 'querySuccess',
          payload: {
            list: data.array,
            pagination: {
              current: Number(payload.page) || 1,
              pageSize: Number(payload.pageSize) || 10,
              total: data.total,
            },
          },
        })
      }
    },

    * queryVersionLog ({ payload }, { call, put }) {
      const data = yield call(queryLogs, payload)
      const { dictType } = payload
      if (data) {
        yield put({
          type: 'queryVersionLogSuccess',
          payload: {
            dictType,
            result: data.array,
          },
        })
      }
    },

    * updateVersion ({ payload }, { call, put }) {
      const data = yield call(update, payload)
      if (data.success) {
        yield put({
          type: 'hideModal',
        })
        yield put({
          type: 'query',
          payload: {
            page: Number(payload.page) || 1,
            pageSize: Number(payload.pageSize) || 10,
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

    queryVersionLogSuccess (state, action) {
      const { result, dictType } = action.payload
      const { logList } = state
      logList[dictType] = result
      return {
        ...state,
        logList,
      }
    },

    showModal (state, action) {
      const { id, isUpload } = action.payload
      return {
        ...state,
        isUpload,
        updateId: id,
        modalVisible: true,
      }
    },

    hideModal (state) {
      return { ...state, modalVisible: false, isDisable: false }
    },

    isAble (state) {
      return { ...state, isDisable: false }
    },

    isDisable (state) {
      return { ...state, isDisable: true }
    },

    changeUpload (state, action) {
      const { isUpload } = action.payload
      return { ...state, isUpload }
    },

  },


}
