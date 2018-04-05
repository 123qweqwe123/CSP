import React from 'react'
import PropTypes from 'prop-types'
import { Table, Modal, Row, Col, Button, Icon } from 'antd'
import queryString from 'query-string'
import { DropOption } from '../../components'
import styles from './PersonList.less'

const { confirm } = Modal

const List = ({
  onDeleteItem, onEditItem, isMotion, location, currentConfId,
  showPersonUploadModal, loading, onAdd, downloadTemplate, ...tableProps
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

  const PERSON_BY_TYPE = {
    1: '来宾',
    2: '讲师',
    3: '工作人员',
  }

  const ID_BY_TYPE = {
    1: '身份证',
    2: '护照',
    3: '其他',
  }

  const GENDER_BY_TYPE = {
    1: '男',
    2: '女',
    3: '未知',
  }

  const QRCODE_BY_TYPE = {
    "0": '否',
    "1": '是',
  }

  const columns = [
    {
      title: '名字',
      width: 100,
      dataIndex: 'name',
      fixed: 'left',
    }, {
      title: '人员类型',
      dataIndex: 'personType',
      width: 100,
      render: text => PERSON_BY_TYPE[text],
      fixed: 'left',
    }, {
      title: '证件编号',
      dataIndex: 'idNumber',
      key: 'idNumber',
    }, {
      title: '证件类型',
      dataIndex: 'idType',
      key: 'idType',
      render: text => ID_BY_TYPE[text],
    }, {
      title: '生成相同二维码',
      dataIndex: 'sameQrcode',
      render: text => QRCODE_BY_TYPE[text],
    }, {
      title: '性别',
      dataIndex: 'gender',
      render: text => GENDER_BY_TYPE[text],

    }, {
      title: '生日',
      dataIndex: 'birthday',
      render: text => (text ? new Date(text).format('yyyy-MM-dd') : ''),
    }, {
      title: '手机号',
      dataIndex: 'tel',
    }, {
      title: '邮箱',
      dataIndex: 'email',
    }, {
      title: '单位',
      dataIndex: 'workplace',
    }, {
      title: '部门',
      dataIndex: 'department',
    }, {
      title: '专业',
      dataIndex: 'major',
    }, {
      title: '学历',
      dataIndex: 'degree',
    }, {
      title: '职务',
      dataIndex: 'duty',
    }, {
      title: '省',
      dataIndex: 'province',
    }, {
      title: '市',
      dataIndex: 'city',
    }, {
      title: '县',
      dataIndex: 'county',
    }, {
      title: '地址',
      dataIndex: 'address',
    }, {
      title: '操作',
      width: 100,
      render: (text, record) => (
        <DropOption onMenuClick={e => handleMenuClick(record, e)} menuOptions={[{ key: '1', name: '修改' }, { key: '2', name: '删除' }]} />
      ),
      fixed: 'right',
    },
  ]

  const exportQRCode = () => {
    Modal.info({
      title: '点击导出对应类型的人员',
      content: (
        <div>
          <Button onClick={() => { window.open(`${BASE_URL}/conferences/exportQRCode?confId=${currentConfId}&type=1`) }}>来宾</Button> &nbsp;&nbsp;
          <Button onClick={() => { window.open(`${BASE_URL}/conferences/exportQRCode?confId=${currentConfId}&type=2`) }}>讲师</Button>&nbsp;&nbsp;
          <Button onClick={() => { window.open(`${BASE_URL}/conferences/exportQRCode?confId=${currentConfId}&type=3`) }}>工作人员</Button>
        </div>
      ),
      okText: '关闭',
      onOk () {},
    })
  }

  return (
    <div>
      <Row>
        <Col xs={24} md={24} style={{ paddingLeft: 12, marginBottom: 16 }}>
          <Button type="primary" onClick={onAdd} loading={loading} style={{ marginLeft: 10 }}><Icon type="user-add" />新建</Button>
          <Button type="primary" onClick={showPersonUploadModal} loading={loading} style={{ marginLeft: 10 }}><Icon type="file-excel" />导入</Button>
          <Button type="primary" onClick={downloadTemplate} loading={loading} style={{ marginLeft: 10 }}><Icon type="download" />导入模板下载</Button>
          <Button type="primary" onClick={exportQRCode} loading={loading} style={{ marginLeft: 10 }}><Icon type="download" />导出二维码</Button>
        </Col>
      </Row>
      <Table
        {...tableProps}
        bordered
        columns={columns}
        rowKey={record => record.personId}
        scroll={{ x: '150% ' }}
      />
    </div>
  )
}

List.propTypes = {
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
  showPersonUploadModal: PropTypes.func,
}

export default List
