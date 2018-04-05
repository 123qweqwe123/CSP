import React from 'react'
import PropTypes from 'prop-types'
import { Table, Modal, Row, Button, Col, Icon } from 'antd'
import AnimTableBody from 'components/DataTable/AnimTableBody'
import { DropOption } from 'components'

const confirm = Modal.confirm

const ConfPlaceList = ({ onAdd, onDeleteItem, loading, onEditItem, isMotion, location, roomList, onAddRoom, handlerUp, onEditSubItem, onDeleteSubItem, handlerDown, ...tableProps }) => {
  const handleMenuClick = (record, e) => {
    if (e.key === '1') {
      onEditItem(record)
    } else if (e.key === '2') {
      confirm({
        title: '确定删除当前数据?',
        onOk () {
          onDeleteItem(record.id)
        },
      })
    } else if (e.key === '0') {
      onAddRoom(record)
    }
  }

  const handleSubMenuClick = (record, e) => {
    if (e.key === '1') {
      onEditSubItem(record)
    } else if (e.key === '2') {
      confirm({
        title: '确定删除当前数据?',
        onOk () {
          onDeleteSubItem(record)
        },
      })
    }
  }

  const columns = [
    {
      title: '会场编号',
      dataIndex: 'placeNo',
    },
    {
      title: '会场名称',
      dataIndex: 'placeName',
    },
    {
      title: '会场地址',
      dataIndex: 'placeAddress',
    },
    {
      title: '操作',
      width: 200,
      render: (text, record) => (
        <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '0', name: '添加房间' }, { key: '1', name: '修改' }, { key: '2', name: '删除' }]} />
      ),
    },
  ]

  const expandedRowRender = (record) => {
    const subColumns = [
      { title: '房间编号', dataIndex: 'roomNo' },
      { title: '房间名称', dataIndex: 'roomName' },
      { title: '房间地址', dataIndex: 'roomAddress' },
      {
        title: '操作',
        dataIndex: 'operator',
        render: (text, subRecord) => (
          <DropOption onMenuClick={e => handleSubMenuClick(subRecord, e)} menuOptions={[{ key: '1', name: '修改' }, { key: '2', name: '删除' }]} />
        ),
      },
    ]

    return (
      <Table
        rowKey={r => r.id}
        columns={subColumns}
        dataSource={roomList[record.id]}
        pagination={false}
      />
    )
  }

  return (
    <div>
      <Row>
        <Col xs={12} md={8} style={{ paddingLeft: 12, marginBottom: 16 }}>
          <Button type="primary" onClick={onAdd} loading={loading} ><Icon type="plus" />新建</Button>
        </Col>
      </Row>
      <Table
        {...tableProps}
        bordered
        columns={columns}
        expandedRowRender={expandedRowRender}
        rowKey={record => record.id}
      />
    </div>
  )
}

ConfPlaceList.propTypes = {
  onDeleteItem: PropTypes.func,
  onAdd: PropTypes.func,
  onEditItem: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  roomList: PropTypes.object,
  handlerUp: PropTypes.func,
  handlerDown: PropTypes.func,
  onAddParameter: PropTypes.func,
  onEditSubItem: PropTypes.func,
  onDeleteSubItem: PropTypes.func,
  loading: PropTypes.any,
  onAddRoom: PropTypes.func,
}

export default ConfPlaceList
