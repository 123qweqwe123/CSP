export default {
  namespace: 'rabbitMQCount',
  state: {
    data: [],
  },

  subscriptions: {
    setup ({ dispatch, history }) {
      history.listen((location) => {
        if (location.pathname === '/chart/rabbitMQCount') {
          dispatch({
            type: 'addMsg',
            payload: {
              msgData: 'initData',
              destination: 'initData',
            },
          })
        }
      })
    },
  },

  effects: {
    * addMsg ({ payload }, { put }) {
      const { msgData, msgType } = payload
      yield put({
        type: 'querySuccess',
        payload: {
          msgData,
          msgType,
        },
      })
    },
  },

  reducers: {
    // 数据获取成功后更新state
    querySuccess (state, action) {
      let { data } = state
      const { msgData, msgType } = action.payload
      const nowTime = new Date()
      let minutes = nowTime.getMinutes()
      let hours = nowTime.getHours()
      minutes = Math.ceil(minutes / 15) * 15
      if (minutes === 60 || minutes === 0) {
        minutes = '00'
        if (hours === 23) {
          hours = '00'
        }
        hours += 1
      }
      if (msgData === 'initData') {
        data = []
        const suffix = ['00', '15', '30', '45']
        let minutesIndex = suffix.indexOf(minutes.toString())
        for (let i = 0; i < 8; i++) {
          data.push({
            time: `${(Array(2).join('0') + hours).slice(-2)}:${suffix[minutesIndex]}`,
            person: 0,
            register: 0,
            checkin: 0,
          })

          minutesIndex--
          if (minutesIndex < 0) {
            minutesIndex = 3
          }
          if (suffix[minutesIndex] === '45') {
            hours--
          }
          if (hours < 0) {
            hours = 23
          }
        }
      } else {
        const timeStr = `${hours}:${minutes}`
        let isAdd = false
        data.forEach((obj) => {
          if (obj.time === timeStr) {
            if (msgType.indexOf('csp.person.json') !== -1) {
              obj.person += 1
            } else if (msgType.indexOf('csp.register.json') !== -1) {
              obj.register += 1
            } else if (msgType.indexOf('csp.checkin.json') !== -1) {
              obj.checkin += 1
            }
            isAdd = true
          }
        })
        if (!isAdd) {
          if (msgType.indexOf('csp.person.json') !== -1) {
            data.push({
              time: timeStr,
              person: 1,
              register: 0,
              checkin: 0,
            })
          } else if (msgType.indexOf('csp.register.json') !== -1) {
            data.push({
              time: timeStr,
              person: 0,
              register: 1,
              checkin: 0,
            })
          } else if (msgType.indexOf('csp.checkin.json') !== -1) {
            data.push({
              time: timeStr,
              person: 0,
              register: 0,
              checkin: 1,
            })
          } else {
            data.push({
              time: timeStr,
              person: 0,
              register: 0,
              checkin: 0,
            })
          }
        }
      }

      return { ...state, data }
    },
  },
}
