import React from 'react'
import PropTypes from 'prop-types'
import { Form, Input, Modal } from 'antd'

const FormItem = Form.Item

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
  item = {},
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
      data.id = item.id
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
        <FormItem label="会场编号" hasFeedback {...formItemLayout}>
          {getFieldDecorator('placeNo', {
            initialValue: item.placeNo,
          })(<Input disabled={!!item.reamrk} />)}
        </FormItem>
        <FormItem label="会场名称" hasFeedback {...formItemLayout}>
          {getFieldDecorator('placeName', {
            initialValue: item.placeName,
            rules: [
              {
                required: true,
                message: '会场名称不能为空!',
                whitespace: true,
              },
            ],
          })(<Input />)}
        </FormItem>
        <FormItem label="会场地址" hasFeedback {...formItemLayout}>
          {getFieldDecorator('placeAddress', {
            initialValue: item.placeAddress,
          })(<Input />)}
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
  roles: PropTypes.array,
  loginUser: PropTypes.object,
}

export default Form.create()(modal)
