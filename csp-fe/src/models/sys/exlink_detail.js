import pathToRegexp from 'path-to-regexp'
import { query } from '../../services/sys/exlink'

export default {

  namespace: 'exlinkdetail',

  state: {
    display: 'initial',
    width: '',
    height: '',
    url: '',
    position: 'relative',
    allowFullScreen: true,
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen((location) => {
        const match = pathToRegexp('/exlink/:exlinkId').exec(location.pathname)
        if (match) {
          dispatch({ type: 'query', payload: { id: match[1] } })
        }
      })
    },
  },

  effects: {

    * query ({ payload }, { call, put }) {
      const data = yield call(query, payload)
      if (data.success) {
        yield put({
          type: 'querySuccess',
          payload: data.list[0],
        })
      } else {
        throw data.result.message
      }
    },
  },

  reducers: {

    querySuccess (state, action) {
      const data = action.payload
      return {
        ...state,
        ...data,
      }
    },
  },


}
