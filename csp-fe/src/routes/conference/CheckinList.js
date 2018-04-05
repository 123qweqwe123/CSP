import React from 'react'
import PropTypes from 'prop-types'
import { Table, Modal, Row, Col, Button, Icon } from 'antd'
import queryString from 'query-string'
import { DropOption } from '../../components'

const { confirm } = Modal

const List = ({
  onDeleteItem, onEditItem, isMotion, location, checkinType, showPersonUploadModal, loading, onAdd, ...tableProps
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

  let CHECKIN_TYPE = []
  checkinType.map((x) => {
    CHECKIN_TYPE[parseInt(x.code)] = x.value
  })

  const PERSON_TYPE = {
    1: '来宾',
    2: '讲师',
    3: '工作人员',
  }

  const columns = [
    {
      title: '签到编号',
      dataIndex: 'checkinNo',
    }, {
      title: '签到类型',
      dataIndex: 'checkinType',
      render: text => CHECKIN_TYPE[text],
    }, {
      title: '签到名称',
      dataIndex: 'checkinName',
    }, {
      title: '开始时间',
      dataIndex: 'startTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd HH:mm') : ''),
    }, {
      title: '结束时间',
      dataIndex: 'endTime',
      render: text => (text ? new Date(text).format('yyyy-MM-dd HH:mm') : ''),
    }, {
      title: '签到人员',
      render: (text, record) => {
        let result = ''
        if (record.personType !== null) {
          if (record.personType.includes('1')) {
            result += `${PERSON_TYPE[1]}  `
          }
          if (record.personType.includes('2')) {
            result += `${PERSON_TYPE[2]}  `
          }
          if (record.personType.includes('3')) {
            result += `${PERSON_TYPE[3]}  `
          }
        } else {
          result += record.v1 !== null ? `${PERSON_TYPE[1]}  ` : ''
          result += record.v2 !== null ? `${PERSON_TYPE[2]}  ` : ''
          result += record.v3 !== null ? `${PERSON_TYPE[3]}  ` : ''
        }
        return result
      },
    }, {
      title: '备注',
      dataIndex: 'remark',
      render: (text, record) => {
        if (record.checkinType === 4) {
          return ''
        }
        return text
      },
    }, {
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
  checkinType: PropTypes.array,
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  loading: PropTypes.bool,
  onAdd: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  showPersonUploadModal: PropTypes.func,
}

export default List
