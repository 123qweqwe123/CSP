import { createAction, NavigationActions, Storage } from '../utils'
import * as confService from '../services/conference'
import { routerReducer } from '../router'

export default {
  namespace: 'home',
  state: {
    list: [], // 会议列表
    checkinList: [],  // 签到列表
    visible: true,  // 显示扫描按钮
    ...routerReducer(),
  },
  reducers: {
    updateState(state, { payload }) {
      return { ...state, ...payload }
    },
    queryConferencesSuccess({ payload }, { call, put }) {

    }
  },
  effects: {
    *queryConferences({ payload }, { select, call, put }) {
      const { val } = payload || {}
      let conferences = yield call(Storage.get, 'conferences')
      console.log(11, conferences)
      if (conferences == null) {
        payload = { pageSize: 30, ...payload}
        const result = yield call(confService.conferences, payload)
        console.log('result', result)
        if (result.success) {
          conferences = result.list
          Storage.set('conferences', conferences)
        }
      }
      conferences = conferences || []
      if (val) {
        conferences = conferences.filter(conf => conf.confName.indexOf(val) !== -1 || conf.confNo.indexOf(val) !== -1)
      }
      yield put({
        type: 'updateState',
        payload: {
          list: conferences,
        }
      })
    },
  },
  subscriptions: {
    setup({ dispatch }) {
      console.log('init home...')
      dispatch({ type: 'queryConferences' })
    },
  },
}
