import React from 'react'
import PropTypes from 'prop-types'
import queryString from 'query-string'
import { Table, Row, Col, Divider } from 'antd'
import styles from './List.less'
import AnimTableBody from '../../../components/DataTable/AnimTableBody'

const List = ({ isMotion, location, updateDict, ...tableProps }) => {
  location.query = queryString.parse(location.search)
  const { logList, download } = tableProps
  const type = {
    99: '手机端安装包',
    98: 'PC端补丁包',
  }

  const handleSubMenuClick = (record) => {
    download(record.fileId)
  }
  const expandedRowRender = (record) => {
    const subColumns = [
      {
        title: 'fileId',
        dataIndex: 'fileId',
        
        className: styles.hidden,
      }, {
        title: '补丁包历史版本号',
        dataIndex: 'dictVersion',
       
      },
      {
        title: '补丁包历史说明',
        dataIndex: 'remark',
        render: text => (<pre>{text}</pre>),
      },
      {
        title: '文件大小',
        dataIndex: 'value1',
      },
      {
        title: '操作',
        dataIndex: 'operator',
        width: 200,
        render: (text, subRecord) => (
          <span>
            <a href="javascript:void(0)" onClick={() => handleSubMenuClick(subRecord)}>下载</a>
          </span>
        ),
      },
    ]

    return (
      <Table
        rowKey={r => r.id}
        columns={subColumns}
        dataSource={logList[record.dictType]}
      />
    )
  }

  const columns = [
    {
      title: 'fileId',
      dataIndex: 'fileId',
      className: styles.hidden,
    }, {
      title: 'id',
      dataIndex: 'id',
      className: styles.hidden,
    }, {
      title: '补丁包类别',
      dataIndex: 'dictType',
      render: text => type[text],
    }, {
      title: '补丁包版本号',
      dataIndex: 'dictVersion',
    }, {
      title: '版本说明',
      dataIndex: 'remark',
    },
    {
      title: '文件大小',
      dataIndex: 'value1',
    },
    {
      title: '操作',
      dataIndex: 'operator',
      width: 150,
      render: (text, record) => (
        <span>
          <a href="javascript:void(0)" onClick={() => updateDict(record)}>更新版本</a>
          <Divider type="vertical" />
          <a href="javascript:void(0)" onClick={() => download(record.fileId)}>下载</a>
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
            expandedRowRender={expandedRowRender}
            columns={columns}
            rowKey={record => record.id}
            getBodyWrapper={getBodyWrapper}
            pagination={false}
          />
        </Col>
      </Row>
    </div>
  )
}

List.propTypes = {
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  updateDict: PropTypes.func,
}

export default List
