import React from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { Form, Input, Modal, Select, DatePicker, AutoComplete } from 'antd'

const FormItem = Form.Item
const { Option } = Select

const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
}

const modal = ({
  idType,
  personType,
  currentConfNo,
  currentConfId,
  confNoDataSource,
  handlerSearch,
  onOk,
  changeConfType,
  personAutoCompleteList,
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
      if (data.birthday) {
        data.birthday = data.birthday.format('YYYY-MM-DD')
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
            initialValue: item.confNo || currentConfNo,
          })(<Input disabled />)}
        </FormItem>
        <FormItem label="会议ID" hasFeedback {...formItemLayout} style={{ display: 'none' }}>
          {getFieldDecorator('confId', {
            initialValue: item.confId || currentConfId,
          })(<Input />)}
        </FormItem>
        <FormItem label="人员类型" hasFeedback {...formItemLayout}>
          {getFieldDecorator('type', {
            initialValue: item.type !== undefined ? `${item.type}` : null,
            rules: [{ required: true, message: '人员类型必须选择' }],
          })(<Select>
            {personType.map(x => <Option key={x.code}>{x.value}</Option>)}
          </Select>)}
        </FormItem>
        <FormItem label="名字" hasFeedback {...formItemLayout}>
          {getFieldDecorator('name', {
            initialValue: item.name,
            rules: [{ required: true, message: '名字必须填写' }],
          })(<AutoComplete
            style={{ width: '100%' }}
            dataSource={personAutoCompleteList}
            onSearch={handlerSearch}
            onChange={handlerChange}
            allowClear
          />)}
        </FormItem>
        <FormItem label="证件编号" hasFeedback {...formItemLayout}>
          {getFieldDecorator('idNumber', {
            initialValue: item.idNumber,
            rules: [{ required: true, message: '证件编号必须填写' }],
          })(<AutoComplete
            style={{ width: '100%' }}
            dataSource={personAutoCompleteList}
            onSearch={handlerSearch}
            onChange={handlerChange}
            allowClear
          />)}
        </FormItem>
        <FormItem label="证件类型" hasFeedback {...formItemLayout}>
          {getFieldDecorator('idType', {
            initialValue: item.idType !== undefined ? `${item.idType}` : null,
            rules: [{ required: true, message: '证件类型必须选择' }],
          })(<Select>
            {idType.map(x => <Option key={x.code}>{x.value}</Option>)}
          </Select>)}
        </FormItem>
        <FormItem label="性别" hasFeedback {...formItemLayout}>
          {getFieldDecorator('gender', {
            initialValue: item.gender !== undefined ? `${item.type}` : null,
          })(<Select>
            <Option key="1">男</Option>
            <Option key="2">女</Option>
            <Option key="3">未知</Option>
          </Select>)}
        </FormItem>
        <FormItem label="生日" hasFeedback {...formItemLayout}>
          {getFieldDecorator('birthday', {
            initialValue: item.birthday ? moment(item.birthday) : null,
          })(<DatePicker style={{ width: '100%' }} />)}
        </FormItem>
        <FormItem label="手机号" hasFeedback {...formItemLayout}>
          {getFieldDecorator('tel', {
            initialValue: item.tel,
            rules: [{ pattern: /^1[3|4|5|7|8][0-9]{9}$/, message: '手机号格式不正确' },
            { required: true, message: '手机号必须填写' }],
          })(<Input />)}
        </FormItem>
        <FormItem label="邮箱" hasFeedback {...formItemLayout}>
          {getFieldDecorator('email', {
            initialValue: item.email,
            rules: [{ type: 'email', message: '邮箱格式不正确' },
            { required: true, message: '邮箱必须填写' }],
          })(<Input />)}
        </FormItem>
        <FormItem label="单位" hasFeedback {...formItemLayout}>
          {getFieldDecorator('workplace', {
            initialValue: item.workplace,
          })(<Input />)}
        </FormItem>
        <FormItem label="部门" hasFeedback {...formItemLayout}>
          {getFieldDecorator('department', {
            initialValue: item.department,
          })(<Input />)}
        </FormItem>
        <FormItem label="专业" hasFeedback {...formItemLayout}>
          {getFieldDecorator('major', {
            initialValue: item.major,
          })(<Input />)}
        </FormItem>
        <FormItem label="学历" hasFeedback {...formItemLayout}>
          {getFieldDecorator('degree', {
            initialValue: item.degree,
          })(<Input />)}
        </FormItem>
        <FormItem label="职务" hasFeedback {...formItemLayout}>
          {getFieldDecorator('duty', {
            initialValue: item.duty,
          })(<Input />)}
        </FormItem>
        <FormItem label="省" hasFeedback {...formItemLayout}>
          {getFieldDecorator('province', {
            initialValue: item.province,
          })(<Input />)}
        </FormItem>
        <FormItem label="市" hasFeedback {...formItemLayout}>
          {getFieldDecorator('city', {
            initialValue: item.city,
          })(<Input />)}
        </FormItem>
        <FormItem label="县" hasFeedback {...formItemLayout}>
          {getFieldDecorator('county', {
            initialValue: item.county,
          })(<Input />)}
        </FormItem>
        <FormItem label="地址" hasFeedback {...formItemLayout}>
          {getFieldDecorator('address', {
            initialValue: item.address,
          })(<Input />)}
        </FormItem>
        <FormItem label="二维码" hasFeedback {...formItemLayout}>
          {getFieldDecorator('sameQrcode', {
            initialValue: item.sameQrcode,
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
  idType: PropTypes.array,
  personType: PropTypes.array,
  currentConfNo: PropTypes.string,
  currentConfId: PropTypes.string,
  confNoDataSource: PropTypes.array,
  handlerSearch: PropTypes.func,
}

export default Form.create()(modal)
