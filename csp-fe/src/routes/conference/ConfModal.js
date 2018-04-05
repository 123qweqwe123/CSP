import React from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { Form, Input, Modal, Select, DatePicker, InputNumber, AutoComplete } from 'antd'

const FormItem = Form.Item
const { Option } = Select
const { RangePicker } = DatePicker

const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
}

const modal = ({
  onOk,
  changeConfType,
  placeDataSource,
  handlerSearch,
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
    const data = {
      ...getFieldsValue(),
    }
    if (data.place && data.place.length !== 32) {
      setFieldsValue({ place: undefined })
    }
    validateFields((errors) => {
      if (errors) {
        return
      }
      if (data.confDate) {
        data.startTime = data.confDate[0].format('YYYY-MM-DD')
        data.endTime = data.confDate[1].format('YYYY-MM-DD')
      }
      onOk(data)
    })
  }

  const modalOpts = {
    ...modalProps,
    onOk: handleOk,
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
            initialValue: item.confNo,
          })(<Input disabled />)}
        </FormItem>
        <FormItem label="会议类型" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confType', {
            initialValue: item.confType !== undefined ? `${item.confType}` : null,
            rules: [
              {
                required: true,
                message: '会议类型不能为空!',
                whitespace: true,
              },
            ],
          })(<Select onChange={changeConfType}>
            <Option value="1">学术会议</Option>
            <Option value="2">培训</Option>
          </Select>)}
        </FormItem>
        <FormItem label="会议名称" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confName', {
            initialValue: item.confName,
            rules: [
              {
                required: true,
                message: '会议名称不能为空!',
                whitespace: true,
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="会议地点" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confPlace', {
            initialValue: item.confPlace,
            rules: [
              {
                required: true,
                message: '会议地点不能为空!',
                whitespace: true,
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="会议会场" hasFeedback {...formItemLayout}>
          {getFieldDecorator('place', {
            initialValue: item.place,
            rules: [
              {
                required: true,
                message: '会议会场只能从已有地点选择!',
                whitespace: true,
              },
            ],
          })(<AutoComplete
            style={{ width: '100%' }}
            dataSource={placeDataSource}
            onSearch={handlerSearch}
            allowClear
          />)}
        </FormItem>
        <FormItem label="预期人数" hasFeedback {...formItemLayout}>
          {getFieldDecorator('expectPerson', {
            initialValue: item.expectPerson,
            rules: [
              {
                required: true,
                message: '预期人数不能为空!',
              },
            ],
          })(<InputNumber style={{ width: '100%' }} />)}
        </FormItem>
        <FormItem label="主持人" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confHost', {
            initialValue: item.confHost,
          })(<Input />)}
        </FormItem>
        <FormItem label="主办单位" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confOrganiser', {
            initialValue: item.confOrganiser,
          })(<Input />)}
        </FormItem>
        <FormItem label="协办单位" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confCoOrganiser', {
            initialValue: item.confCoOrganiser,
          })(<Input />)}
        </FormItem>
        <FormItem label="会议主题" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confTopic', {
            initialValue: item.confTopic,
          })(<Input />)}
        </FormItem>
        <FormItem label="会议日期" hasFeedback {...formItemLayout}>
          {getFieldDecorator('confDate', {
            initialValue: [item.startTime !== undefined ? moment(item.startTime) : null,
              item.endTime !== undefined ? moment(item.endTime) : null],
            rules: [
              {
                required: true,
                message: '会议日期不能为空!',
              }
            ],
          })(<RangePicker style={{ width: '100%' }} />)}
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
  handlerSearch: PropTypes.func,
  handlerChange: PropTypes.func,
  placeDataSource: PropTypes.array,
}

export default Form.create()(modal)
