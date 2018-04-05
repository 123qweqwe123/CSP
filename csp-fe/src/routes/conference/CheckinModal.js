import React from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { Form, Input, Modal, Select, DatePicker, AutoComplete, Checkbox } from 'antd'

const FormItem = Form.Item
const { Option } = Select
const { RangePicker } = DatePicker
const CheckboxGroup = Checkbox.Group

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
      if (data.checkinTime) {
        data.startTime = data.checkinTime[0].format('YYYY-MM-DD HH:mm')
        data.endTime = data.checkinTime[1].format('YYYY-MM-DD HH:mm')
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
        <FormItem label="签到类型" hasFeedback {...formItemLayout}>
          {getFieldDecorator('checkinType', {
            initialValue: item.checkinType !== undefined ? `${item.checkinType}` : null,
          })(<Select>
            {checkinType.map(x => <Option key={x.code}>{x.value}</Option>)}
          </Select>)}
        </FormItem>
        <FormItem label="签到名称" hasFeedback {...formItemLayout}>
          {getFieldDecorator('checkinName', {
            initialValue: item.checkinName,
          })(<Input />)}
        </FormItem>
        <FormItem label="签到时间" hasFeedback {...formItemLayout}>
          {getFieldDecorator('checkinTime', {
            initialValue: [item.startTime !== undefined ? moment(item.startTime) : null,
              item.endTime !== undefined ? moment(item.endTime) : null],
            rules: [{ required: true, message: '签到时间必须选择' }],
          })(<RangePicker style={{ width: '100%' }} format="YYYY-MM-DD HH:mm" showTime={{ format: 'HH:mm', minuteStep: 10 }} />)}
        </FormItem>
        <FormItem label="签到人员选择" hasFeedback {...formItemLayout}>
          {getFieldDecorator('personType', {
            initialValue: item.personType != null ? item.personType.split(',') : ((item.checkinPersonType != null && item.checkinPersonType != '') ? item.checkinPersonType.split(',') : null),
            rules: [{ required: true, message: '签到人员必须选择' }],
          })(<CheckboxGroup options={personType.map((x) => { const obj = { label: x.value, value: x.code }; return obj })} />)}
        </FormItem>
        <FormItem label="备注" hasFeedback {...formItemLayout}>
          {getFieldDecorator('remark', {
            initialValue: item.remark,
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
