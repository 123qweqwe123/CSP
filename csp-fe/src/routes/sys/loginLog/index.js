import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { connect } from 'dva'
import { routerRedux } from 'dva/router'
import { Tabs, Icon } from 'antd'
import List from './List'
import Filter from './Filter'

const { TabPane } = Tabs

const LoginLog = ({
  location, dispatch, loginLog, loading,
}) => {
  const {
    list, pagination, isMotion, nameDataSource,
  } = loginLog
  const { pageSize } = pagination

  const onTabChange = (key) => {
    if (key === '2') {
      dispatch(routerRedux.push({
        pathname: '/sys/datasync',
        status: key,
      }))
    } else if (key === '3') {
      dispatch(routerRedux.push({
        pathname: '/sys/schedule',
        status: key,
      }))
    } else if (key === '4') {
      dispatch(routerRedux.push({
        pathname: '/rqreport',
        status: key,
      }))
    } else if (key === '5') {
      dispatch(routerRedux.push({
        pathname: '/sys/operateLog',
        status: key,
      }))
    }
  }

  const listProps = {
    dataSource: list,
    loading: loading.effects['loginLog/queryLoginLog'],
    pagination,
    location,
    isMotion,
    onChange (page) {
      const { query, pathname } = location
      dispatch(routerRedux.push({
        pathname,
        search: queryString.stringify({
          ...query,
          page: page.current,
          pageSize: page.pageSize,
        }),
      }))
    },
  }

  const filterProps = {
    isMotion,
    nameDataSource,
    filter: {
      ...location.query,
    },
    onSearch (value) {
      dispatch(routerRedux.push({
        pathname: location.pathname,
        search: queryString.stringify({
          ...value,
          page: 1,
          pageSize,
        }),
      }))
    },
    changeNameDataSource (val) {
      dispatch({
        type: 'loginLog/change',
        payload: {
          q: val, // 查询字符串
          s: 'ac1', // 查询标识
        },
      })
    },
  }

  return (
    <div className="content-inner">
      <Tabs defaultActiveKey="1" onTabClick={e => onTabChange(e)}>
        <TabPane tab={<span><Icon type="lock" />登录日志</span>} key="1">
          <Filter {...filterProps} />
          <List {...listProps} />
        </TabPane>
        <TabPane tab={<span><Icon type="sync" />数据同步</span>} key="2" />
        <TabPane tab={<span><Icon type="schedule" />数据监控</span>} key="3" />
        <TabPane tab={<span><Icon type="file" />快逸报表</span>} key="4" />
        <TabPane tab={<span><Icon type="file" />操作日志</span>} key="5" />
      </Tabs>
    </div>
  )
}

LoginLog.propTypes = {
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.object,
  loginLog: PropTypes.any,
}

export default connect(({ loginLog, loading }) => ({ loginLog, loading }))(LoginLog)
