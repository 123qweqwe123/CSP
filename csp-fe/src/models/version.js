import { parse } from 'qs'
import query, { update } from '../services/version'
import { queryAutoComplete } from '../services/common'

export default {

  namespace: 'version',

  state: {
    list: [],
    currentItem: {},
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
  },

  subscriptions: {
    setup({ dispatch, history }) {
      history.listen((location) => {
        if (location.pathname === '/client/versionmanage') {
          dispatch({
            type: 'query',
            payload: location.query,
          })
        }
      })
    },
  },

  effects: {

    * query({ payload }, { call, put }) {
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

    * update({ payload }, { call, put }) {
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
      } else {
        throw data.result.message
      }
    },
  },

  reducers: {
    querySuccess(state, action) {
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
  },
}
