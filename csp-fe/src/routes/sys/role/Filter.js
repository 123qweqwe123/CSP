import React from 'react'
import PropTypes from 'prop-types'
import { Form, Button, Row, Col, Input, AutoComplete } from 'antd'

/**
 * autoComplete 插件动态数据源只能通过修改 state
 */

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
    if (fields.searchId) {
      fields.searchName = dataSourceMap.get(fields.searchId)
    }
    onFilterChange(fields)
  }

  const handlerSearch = (val) => {
    setFieldsValue({ searchId: '' })
    changeNameDataSource(val)
  }

  const handlerSelect = (val) => {
    setFieldsValue({ searchId: val })
  }

  const { name } = filter

  return (
    <Row gutter={24}>
      <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
        {getFieldDecorator('searchName', { initialValue: name })(
          <AutoComplete
            style={{ width: '100%' }}
            dataSource={nameDataSource}
            placeholder="输入角色拼音首字母检索..."
            onSelect={handlerSelect}
            onSearch={handlerSearch}
            allowClear
          />
        )}
        {getFieldDecorator('searchId')(
          <Input type="hidden" />
        )}
      </Col>
      <Col {...ColProps} xl={{ span: 18 }} md={{ span: 16 }}>
        <Button type="primary" onClick={handleSubmit}>查询</Button>
        <div style={{ display: 'flex', justifyContent: 'space-between', float: 'right' }}>
          <Button onClick={onAdd}>添加</Button>
        </div>
      </Col>
    </Row>
  )
}

Filter.propTypes = {
  onAdd: PropTypes.func,
  isMotion: PropTypes.bool,
  switchIsMotion: PropTypes.func,
  form: PropTypes.object,
  filter: PropTypes.object,
  onFilterChange: PropTypes.func,
  nameDataSource: PropTypes.any,
  changeNameDataSource: PropTypes.func,
}

export default Form.create()(Filter)
