import queryString from 'query-string'
import { query } from '../../services/sys/loginLog'
import { queryAutoComplete } from '../../services/common'

export default {

  namespace: 'loginLog',

  state: {
    list: [],
    nameDataSource: [],
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
    setup ({ dispatch, history }) {
      history.listen((location) => {
        if (location.pathname === '/sys/loginlog') {
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

    changeNameDataSource (state, action) {
      const { nameDataSource } = action.payload
      return {
        ...state,
        nameDataSource,
      }
    },

  },

}
