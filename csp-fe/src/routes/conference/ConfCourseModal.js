import React from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { Form, Input, Modal, Checkbox, DatePicker, Switch, Select, Upload, Button, Icon } from 'antd'
import config from 'utils/config'

const FormItem = Form.Item
const CheckboxGroup = Checkbox.Group
const { Option } = Select
const { RangePicker } = DatePicker

const { upload } = config.api.common

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
  checkinTypeVisible,
  currentConfRooms,
  placesRooms,
  personType,
  placeDataSource,
  changeCheckinVisible,
  handlerSearch,
  item,
  form: {
    getFieldDecorator,
    validateFields,
    getFieldsValue,
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
      if (data.courseTime) {
        data.startTime = data.courseTime[0].format('YYYY-MM-DD HH:mm')
        data.endTime = data.courseTime[1].format('YYYY-MM-DD HH:mm')
      }
      if (data.isCheckin || data.checkinType) {
        data.isCheckin = 1
      } else {
        data.isCheckin = 0
      }
      if (data.file) {
        data.file = data.file.map(x => `${x.name},${x.response.result}`).join(';')
      }
      data.confId = item.confId
      data.id = item.id // 签到备注里面存放的是课程 ID
      onOk(data)
    })
  }

  const modalOpts = {
    ...modalProps,
    onOk: handleOk,
  }

  const checkTime = (rule, value, callback) => {
    // const filedName = rule.field
    const { startTime, endTime } = getFieldsValue()
    if (startTime && endTime && startTime > endTime) {
      callback('开始时间不能大于结束时间')
    } else {
      callback()
    }
  }

  const formatFile = (e) => {
    if (Array.isArray(e)) {
      return e
    }
    return e && e.fileList
  }

  return (
    <Modal {...modalOpts}>
      <Form layout="horizontal">
        <FormItem label="课程名称" hasFeedback {...formItemLayout}>
          {getFieldDecorator('name', {
            initialValue: item.name,
          })(<Input />)}
        </FormItem>
        <FormItem label="会场选择" hasFeedback {...formItemLayout}>
          {getFieldDecorator('roomId', {
            initialValue: item.roomId !== undefined ? item.roomId : null,
            rules: [
              {
                required: true,
                message: '会场不能为空!',
              },
            ],
          })(<Select placeholder="请选择会场">
            {currentConfRooms.map(x => <Option value={x.id} key={x.id}>{x.roomName}</Option>)}
          </Select>)}
        </FormItem>
        <FormItem label="是否需要签到" hasFeedback {...formItemLayout}>
          {getFieldDecorator('isCheckin', {
          })(<Switch defaultChecked={item.isCheckin === 1} onChange={changeCheckinVisible} />)}
        </FormItem>
        { checkinTypeVisible && <FormItem label="签到人员选择" hasFeedback {...formItemLayout}>
          {getFieldDecorator('checkinType', {
            initialValue: item.checkinType != null ? item.checkinType.split(',') : null,
            rules: [{ required: true, message: '签到人员必须选择' }],
          })(<CheckboxGroup options={personType.map((x) => { const obj = { label: x.value, value: x.code }; return obj })} />)}
        </FormItem>}
        <FormItem label="讲师" hasFeedback {...formItemLayout}>
          {getFieldDecorator('lecturerName', {
            initialValue: item.lecturerName,
            rules: [
              {
                required: true,
                message: '讲师名称不能为空!',
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="附件" hasFeedback {...formItemLayout}>
          {getFieldDecorator('file', {
            valuePropName: 'fileList',
            getValueFromEvent: formatFile,
          })(<Upload name="file"
            action={upload}
            listType="picture"
            accept="image/*"
            multiple
          >
            <Button>
              <Icon type="upload" /> 点击上传
            </Button>
          </Upload>)}
        </FormItem>
        <FormItem label="上课时间" hasFeedback {...formItemLayout}>
          {getFieldDecorator('courseTime', {
            initialValue: [item.startTime !== undefined ? moment(item.startTime) : null,
              item.endTime !== undefined ? moment(item.endTime) : null],
            rules: [
              {
                required: true,
                message: '上课时间不能为空!',
              },
            ],
          })(<RangePicker style={{ width: '100%' }} format="YYYY-MM-DD HH:mm" showTime={{ format: 'HH:mm', minuteStep: 10 }} />)}
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
  personType: PropTypes.array,
  placesRooms: PropTypes.array,
  changeCheckinVisible: PropTypes.func,
  checkinTypeVisible: PropTypes.bool,
  currentConfRooms: PropTypes.array,
}

export default Form.create()(modal)
