import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { Link } from 'dva/router'
import { Table, Button, Modal, Row, Col } from 'antd'
import AnimTableBody from '../../../components/DataTable/AnimTableBody'

const confirm = Modal.confirm
const List = ({ isMotion, location, updateReport, deleteReport, onAdd, ...tableProps }) => {
  location.query = queryString.parse(location.search)
  const ColProps = {
    xs: 24,
    style: {
      marginBottom: 16,
    },
  }
  const handleMenuClick = (record) => {
    confirm({
      title: '确定删除当前数据?',
      onOk () {
        deleteReport(record.id)
      },
    })
  }

  const columns = [
    {
      title: 'URL',
      dataIndex: 'url',
      render: (text, record) => <Link to={`/exlink/${record.id}`}>{text}</Link>,
    },
    {
      title: '宽度',
      dataIndex: 'width',
    },
    {
      title: '高度',
      dataIndex: 'height',
    },
    {
      title: '显示',
      dataIndex: 'display',
    },
    {
      title: '位置',
      dataIndex: 'position',
    },
    {
      title: '是否全屏',
      dataIndex: 'allowfullscreen',
      render: text => (text.toString() === '1' ? '是' : '否'),
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd') : ''),
    },
    {
      title: '操作人',
      dataIndex: 'createName',
    },
    {
      title: '说明',
      dataIndex: 'remark',
      render: text => (<pre>{text}</pre>),
    },
    {
      title: '操作',
      width: 100,
      dataIndex: 'operator',
      render: (text, record) => (
        <span>
          <a href="javascript:void(0)" onClick={() => updateReport(record)}>修改</a>
          <span className="ant-divider" />
          <a href="javascript:void(0)" onClick={() => handleMenuClick(record)}>删除</a>
        </span>
      ),
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
        <Col {...ColProps} xl={{ span: 24 }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', float: 'left' }}>
            <Button type="primary" onClick={onAdd}>新增</Button>
          </div>
        </Col>
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
  updateReport: PropTypes.func,
  onAdd: PropTypes.func,
  deleteReport: PropTypes.func,
}

export default List
