import React from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { Form, Input, Modal, Select, DatePicker, AutoComplete } from 'antd'

const FormItem = Form.Item
const Option = Select.Option

const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
}

const modal = ({
  checkinType,
  personType,
  currentConfNo,
  currentConfId,
  confNoDataSource,
  handlerSearch,
  onOk,
  changeConfType,
  item = {},
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
    setFieldsValue,
  },
  ...modalProps
}) => {
  const handleOk = () => {
    validateFields((errors) => {
      if (errors) {
        return
      }
      const data = {
        ...getFieldsValue(),
      }
      if (data.startTime) {
        data.startTime = data.startTime.format('YYYY-MM-DD HH:mm')
      }
      if (data.endTime) {
        data.endTime = data.endTime.format('YYYY-MM-DD HH:mm')
      }
      onOk(data)
    })
  }

  const modalOpts = {
    ...modalProps,
    onOk: handleOk,
  }

  // 清空
  const handlerChange = (val) => {
    if (val && val.length === 32) {
      setFieldsValue({ confId: val })
    } else {
      setFieldsValue({ confId: undefined })
    }
  }

  const checkConfNo = (rule, value, callback) => {
    const { confId } = getFieldsValue()
    if (!confId) {
      callback('请输入正确的会议编号')
    } else {
      callback()
    }
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="ID" hasFeedback {...formItemLayout} style={{ display: 'none' }}>
          {getFieldDecorator('id', {
            initialValue: item.id,
          })(<Input disabled />)}
        </FormItem>
        <FormItem label="会议编号" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confNo', {
            initialValue: item.confNo || currentConfNo,
            rules: [{ required: true, message: '会议编号必须选择' },
              { validator: checkConfNo }],
          })(
            <AutoComplete
              style={{ width: '100%' }}
              dataSource={confNoDataSource}
              onSearch={handlerSearch}
              onChange={handlerChange}
              allowClear
            />
          )}
        </FormItem>
        <FormItem label="会议ID" hasFeedback {...formItemLayout} style={{ display: 'none' }}>
          {getFieldDecorator('confId', {
            initialValue: item.confId || currentConfId,
          })(<Input />)}
        </FormItem>
        <FormItem label="会场编号" hasFeedback {...formItemLayout}>
          {getFieldDecorator('checkinType', {
            initialValue: item.checkinType !== undefined ? `${item.checkinType}` : null,
          })(<Select>
            {checkinType.map(x => <Option key={x.code}>{x.value}</Option>)}
          </Select>)}
        </FormItem>
        <FormItem label="开始时间" hasFeedback {...formItemLayout}>
          {getFieldDecorator('startTime', {
            initialValue: item.startTime !== undefined ? moment(item.startTime) : null,
            rules: [{ required: true, message: '会议编号必须选择' }],
          })(<DatePicker style={{ width: '100%' }} format="YYYY-MM-DD HH:mm" showTime={{ format: 'HH:mm' }} />)}
        </FormItem>
        <FormItem label="结束时间" hasFeedback {...formItemLayout}>
          {getFieldDecorator('endTime', {
            initialValue: item.endTime !== undefined ? moment(item.endTime) : null,
            rules: [{ required: true, message: '会议编号必须选择' }],
          })(<DatePicker style={{ width: '100%' }} format="YYYY-MM-DD HH:mm" showTime={{ format: 'HH:mm' }} />)}
        </FormItem>
        <FormItem label="主持人" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confHost', {
            initialValue: item.confHost,
          })(<Input />)}
        </FormItem>
        <FormItem label="会议主题" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confTopic', {
            initialValue: item.confTopic,
          })(<Input />)}
        </FormItem>
        <FormItem label="会议描述" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confDescription', {
            initialValue: item.confDescription,
          })(<Input />)}
        </FormItem>
        <FormItem label="会议地点" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confPlace', {
            initialValue: item.confPlace,
          })(<Input />)}
        </FormItem>
      </Form>
    </Modal>
  )
}

modal.propTypes = {
  form: PropTypes.object.isRequired,
  type: PropTypes.string,
  item: PropTypes.object,
  onOk: PropTypes.func,
  changeConfType: PropTypes.func,
  checkinType: PropTypes.array,
  personType: PropTypes.array,
  currentConfNo: PropTypes.string,
  currentConfId: PropTypes.string,
  confNoDataSource: PropTypes.array,
  handlerSearch: PropTypes.func,
}

export default Form.create()(modal)
