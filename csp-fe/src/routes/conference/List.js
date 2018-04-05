import React from 'react'
import PropTypes from 'prop-types'
import classnames from 'classnames'
import queryString from 'query-string'
import { Table, Modal, Row, Col, Button, Icon } from 'antd'
import { Link } from 'dva/router'
import AnimTableBody from 'components/DataTable/AnimTableBody'
import { DropOption } from 'components'
import styles from './List.less'

const confirm = Modal.confirm

const List = ({
  onDeleteItem, onEditItem, changeStatus, onEditIdentity, onDeleteIdentity, isMotion, location, onAdd, loading, ...tableProps
}) => {
  location.query = queryString.parse(location.search)
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
    } else if (e.key === '4') {
      Modal.confirm({
        title: '会议完结',
        content: '确认会议进入完结状态?',
        okText: '确认',
        cancelText: '取消',
        onOk () {
          changeStatus(record)
        },
      })
    } else if (e.key === '3') {
      onEditIdentity(record)
    } else if (e.key === '5') {
      confirm({
        title: '确定删除证件数据?',
        onOk () {
          onDeleteIdentity(record)
        },
      })
    }
  }


  const getMenuOption = (record) => {
    if (record.status === 3) {
      return [{ key: '1', name: '修改' }, { key: '2', name: '删除' }, { key: '4', name: '会议完结' }]
    }
    return [{ key: '1', name: '修改' }, { key: '2', name: '删除' }]
  }

  const confStatus = {
    1: '准备中',
    2: '进行中',
    3: '结束',
    4: '完结',
  }

  const columns = [
    {
      title: '会议编号',
      dataIndex: 'confNo',
      key: 'confNo',
      render: (text, record) => <Link to={`conference?id=${record.id}`}>{text}</Link>,
    }, {
      title: '主持人',
      dataIndex: 'confHost',
      key: 'confHost',
    }, {
      title: '会议名称',
      dataIndex: 'confName',
      key: 'confName',
    }, {
      title: '主办单位',
      dataIndex: 'confOrganiser',
      key: 'confOrganiser',
    }, {
      title: '协办单位',
      dataIndex: 'confCoOrganiser',
      key: 'confCoOrganiser',
    }, {
      title: '会议主题',
      dataIndex: 'confTopic',
      key: 'confTopic',
    }, {
      title: '开始日期',
      dataIndex: 'startTime',
      key: 'startTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd') : ''),
    }, {
      title: '结束日期',
      dataIndex: 'endTime',
      key: 'endTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd') : ''),
    }, {
      title: '会议地点',
      dataIndex: 'confPlace',
      key: 'confPlace',
    }, {
      title: '会议状态',
      dataIndex: 'status',
      key: 'status',
      render: text => (confStatus[text]),
    },
    {
      title: '操作',
      key: 'operation',
      width: 100,
      render: (text, record) => {
        if (record.status !== 4) {
          return <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={getMenuOption(record)} />
        }
        return ''
      },
    },
  ]


  return (
    <div>
      <Row>
        <Col xs={12} md={8} style={{ paddingLeft: 12, marginBottom: 16 }}>
          <Button type="primary" onClick={onAdd} loading={loading} ><Icon type="plus" />新建</Button>
        </Col>
      </Row>
      <Table
        {...tableProps}
        className={classnames({ [styles.table]: true, [styles.motion]: isMotion })}
        bordered
        columns={columns}
        simple
        rowKey={record => record.id}
      />
    </div>
  )
}

List.propTypes = {
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  changeStatus: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  onAdd: PropTypes.func,
  loading: PropTypes.any,
}

export default List
