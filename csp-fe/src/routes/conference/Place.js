import React from 'react'
import PropTypes from 'prop-types'
import { Table, Modal, Row, Col, Button, Icon } from 'antd'
import queryString from 'query-string'
import { DropOption } from '../../components'

const confirm = Modal.confirm

const List = ({ onDeleteItem, onEditItem, isMotion, location, showPersonUploadModal, loading, onAdd, ...tableProps }) => {
  location.query = queryString.parse(location.search)
  const handleMenuClick = (record, e) => {
    console.log(record)
    if (e.key === '1') {
      onEditItem(record)
    } else if (e.key === '2') {
      confirm({
        title: '确定删除当前数据?',
        onOk () {
          onDeleteItem(record)
        },
      })
    }
  }

  const columns = [
    {
      title: '会场编号',
      dataIndex: 'checkinNo',
    }, {
      title: '主持人',
      dataIndex: 'confHost',
    }, {
      title: '会议主题',
      dataIndex: 'confTopic',
    }, {
      title: '会议描述',
      dataIndex: 'confDescription',
    }, {
      title: '会议地点',
      dataIndex: 'confPlace',
    }, {
      title: '开始时间',
      dataIndex: 'startTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd HH:mm') : ''),
    }, {
      title: '结束时间',
      dataIndex: 'endTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd HH:mm') : ''),
    }, {
      title: '操作',
      width: 100,
      render: (text, record) => (
        <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '1', name: '修改' }, { key: '2', name: '删除' }]} />
      ),
    },
  ]

  return (
    <div>
      <Row>
        <Col xs={12} md={8} style={{ paddingLeft: 12, marginBottom: 16 }}>
          <Button type="primary" onClick={onAdd} loading={loading} style={{ marginLeft: 10 }}><Icon type="plus" />新建</Button>
        </Col>
      </Row>
      <Table
        {...tableProps}
        bordered
        columns={columns}
        rowKey={record => record.personId}
      />
    </div>
  )
}

List.propTypes = {
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  onAdd: PropTypes.func,
  loading: PropTypes.bool,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  showPersonUploadModal: PropTypes.func,
}

export default List
