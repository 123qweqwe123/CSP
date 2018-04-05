import React from 'react'
import classnames from 'classnames'
import { connect } from 'dva'
import { Timeline } from 'antd'
import styles from './index.less'


const Changelog = () => {
  return (
    <div className={classnames({ 'content-inner': true, [styles.timeline]: true })} >
      <Timeline>
        <Timeline.Item>
          <h2>0.1.0</h2>
          <p><code>2017-05-18</code></p>
          <ul>
            <li>项目启动</li>
          </ul>
        </Timeline.Item>
        <Timeline.Item>
          <h2>0.9.0</h2>
          <p><code>2017-09-14</code></p>
          <ul>
            <li>项目开始试运行</li>
          </ul>
        </Timeline.Item>
      </Timeline>
    </div>
  )
}

Changelog.propTypes = {
}

export default connect(({ loading }) => ({ loading }))(Changelog)
