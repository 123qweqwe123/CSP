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
  onFilterChange,
  filter,
  form: {
    getFieldDecorator,
    getFieldsValue,
    setFieldsValue,
  },
}) => {
  const handleSubmit = () => {
    let fields = getFieldsValue()
    onFilterChange(fields)
  }

  const { name } = filter
  return (
    <Row gutter={24}>
      <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
        {getFieldDecorator('lccCode', { initialValue: name })(
          <AutoComplete
            style={{ width: '100%' }}
            placeholder="输入会议编号..."
            allowClear
          />
        )}
        {getFieldDecorator('searchId')(
          <Input type="hidden" />
        )}
      </Col>
      <Col {...ColProps} xl={{ span: 18 }} md={{ span: 16 }}>
        <Button type="primary" onClick={handleSubmit}>查询</Button>
      </Col>
    </Row>
  )
}

Filter.propTypes = {
  isMotion: PropTypes.bool,
  switchIsMotion: PropTypes.func,
  form: PropTypes.object,
  filter: PropTypes.object,
  onFilterChange: PropTypes.func,
}

export default Form.create()(Filter)
