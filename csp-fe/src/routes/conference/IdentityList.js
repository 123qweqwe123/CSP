
import React from 'react'
import PropTypes from 'prop-types'
import { Table, Modal, Row, Col, Button, Icon } from 'antd'
import queryString from 'query-string'
import { DropOption } from '../../components'

const { confirm } = Modal

const List = ({
  onDeleteItem, onEditItem, isMotion, location, showPersonUploadModal, loading, onAdd, download, ...tableProps
}) => {
  location.query = queryString.parse(location.search)
  const handleMenuClick = (record, e) => {
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

  const PERSON_TYPE = {
    1: '来宾',
    2: '讲师',
    3: '工作人员',
  }

  const columns = [
    {
      title: '人员类型',
      dataIndex: 'identityType',
      render: text => PERSON_TYPE[text],
    }, {
      title: '布局文件',
      dataIndex: 'layoutfileId',
      render: text => <span onClick={() => download(text)}>下载</span>,
    }, {
      title: '占位符',
      dataIndex: 'layoutAttrName',
    }, {
      title: '查询语句',
      dataIndex: 'layoutQuerySql',
    }, {
      title: '查询参数',
      dataIndex: 'layoutQueryParameter',
    }, {
      title: '底板文件',
      dataIndex: 'bottonfileId',
      render: text => <span onClick={() => download(text)}>下载</span>,
    }, {
      title: '占位符',
      dataIndex: 'bottomAttrName',
    }, {
      title: '查询语句',
      dataIndex: 'bottomQuerySql',
    }, {
      title: '查询参数',
      dataIndex: 'bottomQueryParameter',
    },
    {
      title: '操作',
      width: 100,
      render: (text, record) => {
        if (record.checkinType === 4) {
          return ''
        }
        return <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '1', name: '修改' }, { key: '2', name: '删除' }]} />
      },
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
        rowKey={record => record.id}
      />
    </div>
  )
}

List.propTypes = {
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  loading: PropTypes.bool,
  onAdd: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  showPersonUploadModal: PropTypes.func,
  download: PropTypes.func,
}

export default List
