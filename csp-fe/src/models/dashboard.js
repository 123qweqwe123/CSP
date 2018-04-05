import modelExtend from 'dva-model-extend'
import _ from 'lodash'
import { parse } from 'qs'
import { query, queryUserDashboards, save } from '../services/dashboard'
import { model } from '../models/common'


export default modelExtend(model, {
  namespace: 'dashboard',
  state: {
    enableSetting: false, // 允许编辑 dashboard
    dashboards: [], // 所有的面板
    checkedDashboards: [], // 用户面板
    uncheckedDashboards: [],
    browser: [ // 测试数据，实际数据从后台动态加载
      {
        name: 'Google Chrome',
        percent: 43.3,
        status: 1,
      },
      {
        name: 'Mozilla Firefox',
        percent: 33.4,
        status: 2,
      },
      {
        name: 'Apple Safari',
        percent: 34.6,
        status: 3,
      },
      {
        name: 'Internet Explorer',
        percent: 12.3,
        status: 4,
      },
      {
        name: 'Opera Mini',
        percent: 3.3,
        status: 1,
      },
      {
        name: 'Chromium',
        percent: 2.53,
        status: 1,
      },
    ],
  },
  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen(() => {
        dispatch({ type: 'query' })
      })
    },
  },

  effects: {

    * query ({ payload }, { call, put }) {
      const data = yield call(query, parse(payload))
      const userData = yield call(queryUserDashboards, parse(payload))
      if (data.success) {
        yield put({
          type: 'querySuccess',
          payload: {
            dashboards: data.array,
            checkedDashboards: userData.array,
          },
        })
      }
    },

    * saveLayout ({ payload }, { call }) {
      const { currLayout } = payload
      const data = yield call(save, { dashboards: JSON.stringify(currLayout) })
      console.log(data)
    },

  },

  reducers: {
    querySuccess (state, action) {
      let { dashboards, checkedDashboards } = action.payload
      const uncheckedDashboards = checkedDashboards.length !== 0 ?
        dashboards.filter((d) => {
          return checkedDashboards.filter((cd) => {
            return cd.i === d.i
          }).length === 0
        }) : []
      // 默认选择所有面板
      checkedDashboards = checkedDashboards.length === 0 ? dashboards : checkedDashboards
      return {
        ...state,
        dashboards,
        checkedDashboards,
        uncheckedDashboards,
      }
    },

    changeSetting (state, action) {
      const { enableSetting } = action.payload
      return {
        ...state,
        enableSetting,
      }
    },

    changeLayout (state, action) {
      const { layout } = action.payload
      const { dashboards } = state
      // 直接修改 layout 的数据会触发布局改变事件
      const checkedDashboards = _.cloneDeep(layout)
      checkedDashboards.forEach((x) => {
        x.title = _.find(dashboards, o => o.i === x.i).title
      })
      return {
        ...state,
        ...action.payload,
        checkedDashboards,
      }
    },

    closeDashboard (state, action) {
      const { key } = action.payload
      const { checkedDashboards, uncheckedDashboards } = state
      const currDashboard = checkedDashboards.filter((d) => {
        return d.i === key
      })[0]
      const newCheckedDashboards = checkedDashboards.filter((d) => {
        return d.i !== key
      })
      uncheckedDashboards.push(currDashboard)
      return {
        ...state,
        uncheckedDashboards,
        checkedDashboards: newCheckedDashboards,
      }
    },

    openDashboard (state, action) {
      const { key } = action.payload
      const { checkedDashboards, uncheckedDashboards } = state
      const currDashboard = uncheckedDashboards.filter((d) => {
        return d.i === key
      })[0]
      const newUncheckedDashboards = uncheckedDashboards.filter((d) => {
        return d.i !== key
      })
      checkedDashboards.push(currDashboard)
      return {
        ...state,
        checkedDashboards,
        uncheckedDashboards: newUncheckedDashboards,
      }
    },

  },

})
