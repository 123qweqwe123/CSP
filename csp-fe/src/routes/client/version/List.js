import React from 'react'
import PropTypes from 'prop-types'
import { Table,Row, Col, Modal } from 'antd'
import classnames from 'classnames'
import AnimTableBody from '../../../components/DataTable/AnimTableBody'
import { DropOption } from '../../../components'
import styles from './List.less'

const confirm = Modal.confirm

const List = ({ updateDicVersion, onDeleteItem, onEditItem, isMotion, location, ...tableProps }) => {

  const columns = [
    {
      title: 'id',
      dataIndex: 'id',
      key: 'id', 
      className: styles.hidden,
    },
    {
      title: '会议编号',
      dataIndex: 'lccCode',
      key: 'lccCode',
    }, {
      title: 'fileId',
      dataIndex: 'fileId',
      key: 'fileId',  
     className: styles.hidden,

    }, {
      title: '版本号',
      dataIndex: 'dictVersion',
      key: 'dictVersion',
    }, {
      title: '类型',
      dataIndex: 'dictName',
      key: 'dictName',
    }, {
      title: '操作',
      key: 'operation',
      render: (text, record) => (
        <span>
          <a href="javascript:void(0)" onClick={() => updateDicVersion(record)}>修改版本号</a>
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
  updateDicVersion: PropTypes.func,
}

export default List
