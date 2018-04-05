import queryString from 'query-string'
import { message } from 'antd'
import { query, create, remove, update } from '../../services/sys/exlink'


export default {

  namespace: 'exlink',

  state: {
    list: [],
    currentItem: {},
    modalVisible: false,
    modalType: 'create',
    linkType: [],
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
        if (location.pathname === '/sys/exlink') {
          dispatch({
            type: 'query',
            payload: queryString.parse(location.search),
          })

          dispatch({
            type: 'queryLinkType',
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

    * create ({ payload }, { call, put }) {
      const data = yield call(create, payload.data)
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

    * update ({ payload }, { call, put }) {
      const data = yield call(update, payload.data)
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

    * delete ({ payload }, { call, put }) {
      const data = yield call(remove, { id: payload.id })
      if (data.success) {
        message.success('删除成功')
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
        message.success('删除失败')
      }
    },

    * queryLinkType (action, { select, put, take }) {
      const paraType = 'T002'
      yield put({
        type: 'app/queryDict',
        payload: {
          paraType,
        },
      })
      // 正常情况下，yield 只会等到 dispatch 了 action，不会等到 state 改变
      // 使用 tak'e 等待上一个 effect 执行完毕
      yield take('app/queryDict/@@end')
      const parameters = yield select(({ app }) => app.parameters)
      yield put({
        type: 'queryLinkTypeSuccess',
        payload: {
          linkType: parameters[paraType],
        },
      })
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
      const { record } = action.payload
      return {
        ...state,
        modalVisible: true,
        currentItem: record,
        modalType: record.id !== undefined ? 'update' : 'create',
      }
    },

    hideModal (state) {
      return { ...state, modalVisible: false }
    },

    queryLinkTypeSuccess (state, action) {
      const { linkType } = action.payload
      return {
        ...state,
        linkType,
      }
    },

  },

}
