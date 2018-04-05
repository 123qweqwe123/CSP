import React from 'react'
import PropTypes from 'prop-types'
import { Form, Button, Select, Row, Col, AutoComplete, Input, DatePicker } from 'antd'
import { FilterItem } from '../../../components'

const { RangePicker } = DatePicker
const Option = Select.Option
const ColProps = {
  xs: 24,
  sm: 12,
  style: {
    marginBottom: 16,
  },
}
const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 16,
  },
}

const Filter = ({
  filter,
  onSearch,
  nameDataSource,
  changeNameDataSource,
  form: {
    getFieldDecorator,
    getFieldsValue,
    setFieldsValue,
  },
}) => {
  const handleSubmit = () => {
    let fields = getFieldsValue()
    if (fields.loginDate && fields.loginDate.length > 0) {
      fields = {
        ...fields,
        startLoginTime: fields.loginDate[0].format('YYYY-MM-DD'),
        endLoginTime: (fields.loginDate[1]).format('YYYY-MM-DD'),
      }
      delete fields.loginDate
    }
    if (fields.logoutDate && fields.logoutDate.length > 0) {
      fields = {
        ...fields,
        startLogoutTime: fields.logoutDate[0].format('YYYY-MM-DD'),
        endLogoutTime: (fields.logoutDate[1]).format('YYYY-MM-DD'),
      }
      delete fields.logoutDate
    }
    onSearch(fields)
  }

  const handlerSearch = (val) => {
    setFieldsValue({ accountId: '' })
    changeNameDataSource(val)
  }

  const handlerSelect = (val) => {
    setFieldsValue({ accountId: val })
  }

  const { loginDate, logoutDate, isSuccess, accountName } = filter

  return (
    <Row gutter={24}>
      <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
        <FilterItem {...formItemLayout} label="登录账户">
          {getFieldDecorator('accountName', { initialValue: accountName })(
            <AutoComplete
              style={{ width: '100%' }}
              dataSource={nameDataSource}
              placeholder="输入姓名拼音首字母检索..."
              onSelect={handlerSelect}
              onSearch={handlerSearch}
              allowClear
            />
          )}
          {getFieldDecorator('accountId')(
            <Input type="hidden" />
          )}
        </FilterItem>
      </Col>
      <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
        <FilterItem {...formItemLayout} label="登录状态">
          {getFieldDecorator('isSuccess', { initialValue: isSuccess })(
            <Select style={{ width: '100%' }} allowClear placeholder="请选择登录状态">
              <Option key="1">成功</Option>
              <Option key="0">失败</Option>
            </Select>)}
        </FilterItem>
      </Col>
      <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
        <FilterItem {...formItemLayout} label="登入时间">
          {getFieldDecorator('loginDate', { initialValue: loginDate })(<RangePicker style={{ width: '100%' }} />)}
        </FilterItem>
      </Col>
      <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
        <FilterItem {...formItemLayout} label="登出时间">
          {getFieldDecorator('logoutDate', { initialValue: logoutDate })(<RangePicker style={{ width: '100%' }} />)}
        </FilterItem>
      </Col>

      <Col {...ColProps} xl={{ span: 6 }} md={{ span: 8 }}>
        <Button type="primary" onClick={handleSubmit}>查询</Button>
      </Col>
    </Row>
  )
}

Filter.propTypes = {
  form: PropTypes.any,
  filter: PropTypes.any,
  onSearch: PropTypes.func,
  nameDataSource: PropTypes.array,
  changeNameDataSource: PropTypes.func,
  setFieldsValue: PropTypes.func,
}

export default Form.create()(Filter)
