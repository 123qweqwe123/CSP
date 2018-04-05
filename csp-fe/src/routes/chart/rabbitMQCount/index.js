import React from 'react'
import PropTypes from 'prop-types'
import { connect } from 'dva'
import { stomp } from 'utils'
import MsgCountChart from './MsgCountChart'

const { Stomp } = stomp

const RabbitMQCount = ({ dispatch, rabbitMQCount }) => {
  const { data } = rabbitMQCount
  let dateIntegralPoint = new Date()
  let minutes = dateIntegralPoint.getMinutes()
  minutes = Math.ceil(minutes / 15) * 15
  dateIntegralPoint.setMinutes(minutes)
  // 定时任务
  setTimeout(() => {
    setInterval(() => {
      dispatch({
        type: 'rabbitMQCount/addMsg',
        payload: {
          msgData: '',
          msgType: '',
        },
      })
    }, 15 * 60 * 1000)
  }, dateIntegralPoint - new Date())

  // 接收rabbitMQ消息
  const ws = new WebSocket('ws://10.24.10.173:15674/ws')
  const client = Stomp.over(ws)
  const onConnect = function () {
    // data.body是接收到的数据 订阅的队列名称不能用/,使用%2F转义符
    client.subscribe('/queue/csp.web_dev', (msg) => {
      dispatch({
        type: 'rabbitMQCount/addMsg',
        payload: {
          msgData: msg.body,
          msgType: msg.headers.destination,
        },
      })
    })
  }
  const onError = function () {
    console.error('error')
  }
  client.connect('guest', 'guest', onConnect, onError, '/')

  const chartProps = {
    data: data.sort((obj1, obj2) => {
      return obj1.time.localeCompare(obj2.time)
    }),
  }

  return (
    <div className="content-inner">
      <MsgCountChart {...chartProps} />
    </div>
  )
}

RabbitMQCount.propTypes = {
  dispatch: PropTypes.func,
  rabbitMQCount: PropTypes.object,
}

export default connect(({ rabbitMQCount }) => ({ rabbitMQCount }))(RabbitMQCount)
