import React from 'react'
import PropTypes from 'prop-types'
import { Form, Button, Row, Col, Input, DatePicker, Icon, AutoComplete, message } from 'antd'
import styles from './Filter.less'

const FormItem = Form.Item

const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 16,
  },
}

const Filter = ({
  onFilterChange,
  confNoDataSource,
  handlerSearch,
  loading,
  form: {
    getFieldDecorator,
    getFieldsValue,
    setFieldsValue,
  },
}) => {
  const handleSubmit = () => {
    let fields = getFieldsValue()
    if (fields.transNo && !fields.id) {
      message.warning('会议编号不存在')
    }
    if (fields.transNo) {
      delete fields.transNo
    }
    if (!fields.id) {
      setFieldsValue({ transNo: undefined })
    }
    if (fields.startDate) {
      fields.startDate = fields.startDate.format('YYYY-MM-DD')
    }
    onFilterChange(fields)
  }

  // 清空
  const handlerChange = (val) => {
    if (val && val.length === 32) {
      setFieldsValue({ id: val })
    } else {
      setFieldsValue({ id: undefined })
    }
  }

  return (
    <Form layout="horizontal" className={{ [styles.form]: true }}>
      <Row gutter={24}>
        <Col xs={12} md={8}>
          <FormItem {...formItemLayout} label="会议编号">
            {getFieldDecorator('transNo', {})(
              <AutoComplete
                style={{ width: '100%' }}
                dataSource={confNoDataSource}
                onSearch={handlerSearch}
                onChange={handlerChange}
                allowClear
              />
            )}
          </FormItem>
          <FormItem {...formItemLayout} style={{ display: 'none' }}>
            {getFieldDecorator('id')(
              <Input type="hidden" />
            )}
          </FormItem>
        </Col>
        <Col xs={12} md={8}>
          <FormItem {...formItemLayout} label="会议日期">
            {getFieldDecorator('startDate', {})(<DatePicker style={{ width: '100%' }} />)}
          </FormItem>
        </Col>
        <Col xs={24} md={6} style={{ marginBottom: 16 }}>
          <Button type="primary" onClick={handleSubmit} loading={loading}>查询</Button>
        </Col>
      </Row>
    </Form>)
}

Filter.propTypes = {
  loading: PropTypes.any,
  form: PropTypes.object,
  filter: PropTypes.object,
  confNoDataSource: PropTypes.array,
  handlerSearch: PropTypes.func,
  onFilterChange: PropTypes.func,
}

export default Form.create()(Filter)
