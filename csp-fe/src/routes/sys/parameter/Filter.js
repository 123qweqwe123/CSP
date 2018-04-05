import React from 'react'
import PropTypes from 'prop-types'
import { Form, Button, Row, Col, Input, AutoComplete } from 'antd'


const ColProps = {
  xs: 24,
  sm: 12,
  style: {
    marginBottom: 16,
  },
}

const Filter = ({
  nameDataSource,
  onFilterChange,
  onAdd,
  changeNameDataSource,
  filter,
  form: {
    getFieldDecorator,
    getFieldsValue,
    setFieldsValue,
  },
}) => {
  let dataSourceMap = new Map()
  nameDataSource.map((x) => {
    dataSourceMap.set(x.value, x.text)
  })

  const handleSubmit = () => {
    let fields = getFieldsValue()
    if (fields.typeCode) {
      fields.typeName = dataSourceMap.get(fields.typeCode)
    }
    onFilterChange(fields)
  }

  const handlerSearch = (val) => {
    setFieldsValue({ typeCode: '' })
    changeNameDataSource(val)
  }

  const handlerSelect = (val) => {
    setFieldsValue({ typeCode: val })
  }

  const { name } = filter

  return (
    <div>
      <Row gutter={24}>
        <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
          {getFieldDecorator('typeName', { initialValue: name })(<AutoComplete
            style={{ width: '100%' }}
            dataSource={nameDataSource}
            placeholder="输入参数类型名称拼音首字母检索..."
            onSelect={handlerSelect}
            onSearch={handlerSearch}
            allowClear
          />)}
          {getFieldDecorator('typeCode')(<Input type="hidden" />)}
        </Col>
        <Col {...ColProps} xl={{ span: 18 }} md={{ span: 16 }}>
          <Button type="primary" onClick={handleSubmit}>查询</Button>
        </Col>
      </Row>
      <Row>
        <Col {...ColProps} xl={{ span: 18 }} md={{ span: 16 }}>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <Button onClick={onAdd}>添加</Button>
          </div>
        </Col>
      </Row>
    </div>
  )
}

Filter.propTypes = {
  onAdd: PropTypes.func,
  isMotion: PropTypes.bool,
  switchIsMotion: PropTypes.func,
  form: PropTypes.object,
  filter: PropTypes.object,
  onFilterChange: PropTypes.func,
  nameDataSource: PropTypes.array,
  changeNameDataSource: PropTypes.func,
}

export default Form.create()(Filter)
