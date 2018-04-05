import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { Table, Modal, Icon } from 'antd'
import AnimTableBody from '../../../components/DataTable/AnimTableBody'
import { DropOption } from '../../../components'

const confirm = Modal.confirm

const List = ({ onDeleteItem, onEditItem, isMotion, location, subList, onAddParameter, handlerUp, onEditSubItem, onDeleteSubItem, handlerDown, ...tableProps }) => {
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
    } else if (e.key === '0') {
      onAddParameter(record)
    }
  }

  const handleSubMenuClick = (record, e) => {
    if (e.key === '1') {
      onEditSubItem(record)
    } else if (e.key === '2') {
      confirm({
        title: '确定删除当前数据?',
        onOk () {
          onDeleteSubItem(record.id)
        },
      })
    }
  }

  const columns = [
    {
      title: '参数类型码',
      dataIndex: 'code',
    }, {
      title: '参数类型名称',
      dataIndex: 'value',
    }, {
      title: '备注',
      dataIndex: 'remark',
    },
    {
      title: '状态',
      dataIndex: 'isValid',
      render: text => (text === 1 ? '有效' : '无效'),
    },
    {
      title: '操作',
      width: 200,
      render: (text, record) => (
        <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '0', name: '添加参数' }, { key: '1', name: '修改' }, { key: '2', name: '删除' }]} />
      ),
    },
  ]

  const getBodyWrapperProps = {
    page: location.query.page,
    current: tableProps.pagination.current,
  }

  const expandedRowRender = (record) => {
    const subColumns = [
      { title: '参数代码', dataIndex: 'code' },
      { title: '参数值', dataIndex: 'value' },
      { title: '序号', dataIndex: 'sequence' },
      {
        title: '操作',
        dataIndex: 'operator',
        render: (text, subRecord) => (
          <div>
            <Icon type="arrow-up" style={{ cursor: 'pointer' }} onClick={() => handlerUp(record, subRecord)} />
            <Icon type="arrow-down" style={{ cursor: 'pointer', marginLeft: 20, marginRight: 10 }} onClick={() => handlerDown(record, subRecord)} />
            <DropOption onMenuClick={e => handleSubMenuClick(subRecord, e)} menuOptions={[{ key: '1', name: '修改' }, { key: '2', name: '删除' }]} />
          </div>
        ),
      },
    ]

    return (
      <Table
        rowKey={r => r.id}
        columns={subColumns}
        dataSource={subList[record.code]}
        pagination={false}
      />
    )
  }

  return (
    <div>
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

List.propTypes = {
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  subList: PropTypes.object,
  handlerUp: PropTypes.func,
  handlerDown: PropTypes.func,
  onAddParameter: PropTypes.func,
}

export default List
