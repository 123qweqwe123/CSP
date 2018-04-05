import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { Table, Badge, Row, Col } from 'antd'
import AnimTableBody from '../../../components/DataTable/AnimTableBody'

const List = ({ isMotion, location, ...tableProps }) => {
  location.query = queryString.parse(location.search)
  const columns = [
    {
      title: '登录账户',
      dataIndex: 'loginName',
    },
    {
      title: '机器IP',
      dataIndex: 'machineIp',
    },
    {
      title: '是否登录成功',
      dataIndex: 'isSuccess',
      render: (text) => {
        if (text === 1) {
          return <Badge status="success" text="成功" />
        }
        return <Badge status="error" text="失败" />
      },
    },
    {
      title: '登录时间',
      dataIndex: 'loginTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd HH:mm:ss') : ''),
    },
    {
      title: '登出时间',
      dataIndex: 'logoutTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd HH:mm:ss') : ''),
    },
    {
      title: '备注',
      dataIndex: 'remark',
    },
  ]

  const getBodyWrapperProps = {
    page: location.query.page,
    current: tableProps.pagination.current,
  }

  const getBodyWrapper = body => (isMotion ? <AnimTableBody {...getBodyWrapperProps} body={body} /> : body)

  return (
    <div>
      <Row gutter={24}>
        <Col span={24}>
          <Table
            {...tableProps}
            bordered
            columns={columns}
            rowKey={record => record.id}
            getBodyWrapper={getBodyWrapper}
          />
        </Col>
      </Row>
    </div>
  )
}

List.propTypes = {
  isMotion: PropTypes.bool,
  location: PropTypes.object,
}

export default List
